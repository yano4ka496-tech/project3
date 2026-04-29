#!/usr/bin/env python3
import os
import sys
import subprocess
import json
import argparse
import sqlite3
from typing import List, Dict, Any

def check_dependencies():
    """Проверка наличия необходимых зависимостей"""
    dependencies = ['ogr2ogr', 'tippecanoe', 'sqlite3']
    missing = []
    
    for dep in dependencies:
        if not subprocess.run(['which', dep], capture_output=True).returncode == 0:
            missing.append(dep)
    
    if missing:
        print(f"Ошибка: отсутствуют зависимости: {', '.join(missing)}")
        print("Установите их перед запуском скрипта:")
        print("sudo apt-get update")
        print("sudo apt-get install gdal-bin tippecanoe sqlite3")
        sys.exit(1)
    
    print("✓ Все зависимости установлены")

def get_pdf_layers(pdf_path: str) -> List[str]:
    """Получение списка слоев из PDF файла"""
    print(f"Получение списка слоев из {pdf_path}...")
    
    result = subprocess.run(
        ["ogrinfo", "-so", "-al", pdf_path], 
        capture_output=True, 
        text=True
    )
    
    if result.returncode != 0:
        raise Exception(f"Ошибка при получении информации о слоях: {result.stderr}")
    
    layers = []
    for line in result.stdout.split('\n'):
        if "Layer name:" in line:
            layer_name = line.split("Layer name:")[1].strip()
            layers.append(layer_name)
    
    if not layers:
        raise Exception("В PDF не найдено слоев")
    
    print(f"Найдены слои: {', '.join(layers)}")
    return layers

def convert_layer_to_geojson(pdf_path: str, layer_name: str, output_path: str):
    """Конвертация одного слоя PDF в GeoJSON с системой координат WGS84"""
    print(f"Конвертация слоя '{layer_name}' в GeoJSON...")
    
    subprocess.run([
        "ogr2ogr", 
        "-f", "GeoJSON", 
        "-t_srs", "EPSG:4326", 
        output_path, 
        pdf_path, 
        layer_name
    ], check=True)

def combine_geojson_files(geojson_files: List[str], output_path: str):
    """Объединение нескольких GeoJSON файлов в один"""
    print("Объединение слоев в один GeoJSON файл...")
    
    all_features = []
    
    for geojson_file in geojson_files:
        with open(geojson_file, 'r') as f:
            data = json.load(f)
            if 'features' in data:
                all_features.extend(data['features'])
    
    combined_data = {
        "type": "FeatureCollection",
        "features": all_features
    }
    
    with open(output_path, 'w') as f:
        json.dump(combined_data, f, indent=2)

def optimize_geojson(geojson_path: str):
    """Оптимизация GeoJSON файла для уменьшения размера"""
    print("Оптимизация GeoJSON файла...")
    
    # Удаляем ненужные свойства из объектов
    with open(geojson_path, 'r') as f:
        data = json.load(f)
    
    if 'features' in data:
        for feature in data['features']:
            if 'properties' in feature:
                # Удаляем свойства с пустыми значениями
                props = feature['properties']
                feature['properties'] = {k: v for k, v in props.items() if v is not None and v != ''}
    
    with open(geojson_path, 'w') as f:
        json.dump(data, f, separators=(',', ':'))  # Минимальные разделители для уменьшения размера

def convert_to_mbtiles(geojson_path: str, output_path: str):
    """Конвертация GeoJSON в MBTiles с оптимизацией размера"""
    print("Конвертация в MBTiles...")
    
    # Параметры tippecanoe для оптимизации размера и сохранения детализации
    cmd = [
        "tippecanoe", 
        "-zg",          # Минимальный зум (0)
        "-Z18",         # Максимальный зум (18) - детализация до 10 м
        "-o", output_path, 
        geojson_path,
        "--force-feature-id",  # Использовать существующий ID объекта
        "--drop-densest-as-needed",  # Удаление самых плотных тайлов при необходимости
        "--simplification=10",  # Упрощение геометрии для уменьшения размера
        "--maximum-tiles=2000000",  # Максимальное количество тайлов (ограничение размера)
        "--exclude", "all",  # Исключить все слои по умолчанию
        "--include", "roads,buildings,labels,danger_zones"  # Включить только нужные слои
    ]
    
    subprocess.run(cmd, check=True)

def validate_mbtiles(mbtiles_path: str) -> Dict[str, Any]:
    """Валидация MBTiles файла"""
    print(f"Валидация MBTiles файла: {mbtiles_path}")
    
    if not os.path.exists(mbtiles_path):
        raise Exception(f"MBTiles файл не найден: {mbtiles_path}")
    
    # Проверка размера
    size = os.path.getsize(mbtiles_path)
    size_mb = size / (1024 * 1024)
    print(f"Размер MBTiles файла: {size} байт ({size_mb:.2f} МБ)")
    
    if size >= 209715200:  # 200 МБ
        raise Exception(f"Размер файла превышает 200 МБ: {size_mb:.2f} МБ")
    
    # Проверка структуры SQLite
    conn = sqlite3.connect(mbtiles_path)
    cursor = conn.cursor()
    
    # Проверка наличия таблицы tiles
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='tiles'")
    if not cursor.fetchone():
        conn.close()
        raise Exception("MBTiles не содержит таблицу tiles")
    
    # Проверка наличия данных
    cursor.execute("SELECT COUNT(*) FROM tiles")
    tile_count = cursor.fetchone()[0]
    print(f"Количество тайлов: {tile_count}")
    
    # Проверка уровней масштабирования
    cursor.execute("SELECT DISTINCT zoom_level FROM tiles ORDER BY zoom_level")
    zoom_levels = [row[0] for row in cursor.fetchall()]
    print(f"Уровни масштабирования: {zoom_levels}")
    
    # Проверка наличия данных для всех уровней от 0 до 18
    expected_zooms = set(range(0, 19))
    actual_zooms = set(zoom_levels)
    
    missing_zooms = expected_zooms - actual_zooms
    if missing_zooms:
        print(f"Предупреждение: отсутствуют уровни масштабирования: {missing_zooms}")
    
    # Проверка наличия данных для ключевых уровней
    key_zooms = [0, 5, 10, 15, 18]
    for zoom in key_zooms:
        cursor.execute("SELECT COUNT(*) FROM tiles WHERE zoom_level = ?", (zoom,))
        count = cursor.fetchone()[0]
        print(f"Тайлов на уровне {zoom}: {count}")
    
    conn.close()
    
    return {
        "size": size,
        "size_mb": size_mb,
        "tile_count": tile_count,
        "zoom_levels": zoom_levels,
        "missing_zooms": list(missing_zooms)
    }

def convert_pdf_to_mbtiles(pdf_path: str, output_path: str, validate: bool = True):
    """
    Конвертирует PDF с векторными слоями в MBTiles файл
    
    Args:
        pdf_path: Путь к исходному PDF файлу
        output_path: Путь для сохранения MBTiles файла
        validate: Валидировать результат конвертации
    """
    print(f"Конвертация {pdf_path} в {output_path}")
    
    # Проверка зависимостей
    check_dependencies()
    
    # Временные файлы
    temp_geojson_files = []
    temp_combined_geojson = "temp_combined.geojson"
    
    try:
        # Получаем слои из PDF
        layers = get_pdf_layers(pdf_path)
        
        # Конвертируем каждый слой в GeoJSON
        for layer in layers:
            layer_geojson = f"temp_{layer}.geojson"
            convert_layer_to_geojson(pdf_path, layer, layer_geojson)
            temp_geojson_files.append(layer_geojson)
        
        # Объединяем все слои в один GeoJSON
        combine_geojson_files(temp_geojson_files, temp_combined_geojson)
        
        # Оптимизируем GeoJSON
        optimize_geojson(temp_combined_geojson)
        
        # Конвертируем в MBTiles
        convert_to_mbtiles(temp_combined_geojson, output_path)
        
        print(f"Готово: {output_path}")
        
        # Валидация результата
        if validate:
            validation_result = validate_mbtiles(output_path)
            print("Результат валидации:")
            print(f"  Размер: {validation_result['size_mb']:.2f} МБ")
            print(f"  Тайлов: {validation_result['tile_count']}")
            print(f"  Уровни масштабирования: {validation_result['zoom_levels']}")
            
            if validation_result['missing_zooms']:
                print(f"  Предупреждение: отсутствуют уровни: {validation_result['missing_zooms']}")
        
    finally:
        # Удаляем временные файлы
        for file in temp_geojson_files + [temp_combined_geojson]:
            if os.path.exists(file):
                os.remove(file)

def main():
    parser = argparse.ArgumentParser(description='Конвертация PDF с векторными слоями в MBTiles')
    parser.add_argument('pdf_path', help='Путь к исходному PDF файлу')
    parser.add_argument('output_path', help='Путь для сохранения MBTiles файла')
    parser.add_argument('--no-validate', action='store_true', help='Не валидировать результат')
    
    args = parser.parse_args()
    
    # Проверяем, что входной файл существует
    if not os.path.exists(args.pdf_path):
        print(f"Ошибка: файл {args.pdf_path} не найден")
        sys.exit(1)
    
    convert_pdf_to_mbtiles(args.pdf_path, args.output_path, not args.no_validate)

if __name__ == "__main__":
    main()