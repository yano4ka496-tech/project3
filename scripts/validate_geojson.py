#!/usr/bin/env python3
import json
import os
import sys
from typing import Dict, Any, List

def validate_geojson_file(file_path: str) -> Dict[str, Any]:
    """
    Валидация GeoJSON файла
    
    Args:
        file_path: Путь к GeoJSON файлу
        
    Returns:
        Словарь с результатами валидации
    """
    print(f"Валидация GeoJSON файла: {file_path}")
    
    if not os.path.exists(file_path):
        raise Exception(f"GeoJSON файл не найден: {file_path}")
    
    # Проверка размера
    size = os.path.getsize(file_path)
    size_kb = size / 1024
    print(f"Размер файла: {size} байт ({size_kb:.2f} КБ)")
    
    if size_kb > 1024:  # 1 МБ
        raise Exception(f"Размер файла превышает 1 МБ: {size_kb:.2f} КБ")
    
    # Проверка формата JSON
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
    except json.JSONDecodeError as e:
        raise Exception(f"Ошибка формата JSON: {str(e)}")
    
    # Проверка структуры GeoJSON
    if data.get('type') != 'FeatureCollection':
        raise Exception("Неверный тип GeoJSON: ожидался FeatureCollection")
    
    if 'features' not in data:
        raise Exception("Отсутствует поле 'features' в GeoJSON")
    
    features = data['features']
    if not isinstance(features, list):
        raise Exception("Поле 'features' должно быть списком")
    
    # Проверка каждого объекта
    feature_types = []
    for i, feature in enumerate(features):
        if not isinstance(feature, dict):
            raise Exception(f"Объект {i} не является словарем")
        
        if feature.get('type') != 'Feature':
            raise Exception(f"Объект {i} не является Feature")
        
        if 'geometry' not in feature:
            raise Exception(f"Объект {i} отсутствует поле 'geometry'")
        
        geometry = feature['geometry']
        if not isinstance(geometry, dict):
            raise Exception(f"Геометрия объекта {i} не является словарем")
        
        geom_type = geometry.get('type')
        if geom_type not in ['Point', 'LineString', 'Polygon']:
            raise Exception(f"Неверный тип геометрии объекта {i}: {geom_type}")
        
        feature_types.append(geom_type)
        
        # Проверка координат
        coordinates = geometry.get('coordinates')
        if coordinates is None:
            raise Exception(f"Объект {i} отсутствуют координаты")
        
        if geom_type == 'Point':
            if not isinstance(coordinates, list) or len(coordinates) != 2:
                raise Exception(f"Неверный формат координат Point в объекте {i}")
        
        elif geom_type == 'LineString':
            if not isinstance(coordinates, list) or len(coordinates) < 2:
                raise Exception(f"Неверный формат координат LineString в объекте {i}")
            for coord in coordinates:
                if not isinstance(coord, list) or len(coord) != 2:
                    raise Exception(f"Неверный формат координат в LineString объекта {i}")
        
        elif geom_type == 'Polygon':
            if not isinstance(coordinates, list) or len(coordinates) < 1:
                raise Exception(f"Неверный формат координат Polygon в объекте {i}")
            for ring in coordinates:
                if not isinstance(ring, list) or len(ring) < 4:
                    raise Exception(f"Неверный формат координат в Polygon объекта {i}")
        
        # Проверка свойств
        if 'properties' not in feature:
            raise Exception(f"Объект {i} отсутствуют свойства")
        
        properties = feature['properties']
        if not isinstance(properties, dict):
            raise Exception(f"Свойства объекта {i} не являются словарем")
        
        # Проверка обязательных свойств
        required_props = ['name', 'color']
        for prop in required_props:
            if prop not in properties:
                raise Exception(f"Объект {i} отсутствует обязательное свойство: {prop}")
    
    # Статистика
    feature_count = len(features)
    type_counts = {}
    for ft in feature_types:
        type_counts[ft] = type_counts.get(ft, 0) + 1
    
    print(f"Валидация пройдена успешно")
    print(f"  Объектов: {feature_count}")
    print(f"  Типы геометрий: {type_counts}")
    
    return {
        "valid": True,
        "size": size,
        "size_kb": size_kb,
        "feature_count": feature_count,
        "geometry_types": type_counts
    }

def main():
    # Проверяем аргументы командной строки
    if len(sys.argv) < 2:
        print("Использование: python validate_geojson.py <путь_к_файлу1> [<путь_к_файлу2> ...]")
        sys.exit(1)
    
    files_to_validate = sys.argv[1:]
    all_valid = True
    
    for file_path in files_to_validate:
        try:
            result = validate_geojson_file(file_path)
            print(f"✓ {file_path}: валидация пройдена")
        except Exception as e:
            print(f"✗ {file_path}: {str(e)}")
            all_valid = False
    
    if all_valid:
        print("\n✓ Все файлы прошли валидацию")
        sys.exit(0)
    else:
        print("\n✗ Некоторые файлы не прошли валидацию")
        sys.exit(1)

if __name__ == "__main__":
    main()