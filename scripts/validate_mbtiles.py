#!/usr/bin/env python3
import os
import sys
import sqlite3
import argparse
from typing import Dict, Any, List

def validate_mbtiles(mbtiles_path: str) -> Dict[str, Any]:
    """
    Валидация MBTiles файла
    
    Args:
        mbtiles_path: Путь к MBTiles файлу
        
    Returns:
        Словарь с результатами валидации
    """
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
    
    # Проверка наличия таблицы metadata
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' AND name='metadata'")
    if not cursor.fetchone():
        conn.close()
        raise Exception("MBTiles не содержит таблицу metadata")
    
    # Проверка метаданных
    cursor.execute("SELECT name, value FROM metadata")
    metadata = dict(cursor.fetchall())
    
    print("Метаданные:")
    for key, value in metadata.items():
        print(f"  {key}: {value}")
    
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
    print("Количество тайлов на ключевых уровнях:")
    for zoom in key_zooms:
        cursor.execute("SELECT COUNT(*) FROM tiles WHERE zoom_level = ?", (zoom,))
        count = cursor.fetchone()[0]
        print(f"  Уровень {zoom}: {count} тайлов")
    
    # Проверка экстентов
    cursor.execute("SELECT MIN(tile_column), MAX(tile_column), MIN(tile_row), MAX(tile_row) FROM tiles")
    min_col, max_col, min_row, max_row = cursor.fetchone()
    print(f"Экстент тайлов: колонки {min_col}-{max_col}, строки {min_row}-{max_row}")
    
    # Проверка наличия данных на всех уровнях масштабирования
    print("Проверка наличия данных на всех уровнях масштабирования:")
    all_levels_have_data = True
    for zoom in range(0, 19):
        cursor.execute("SELECT COUNT(*) FROM tiles WHERE zoom_level = ?", (zoom,))
        count = cursor.fetchone()[0]
        if count == 0:
            print(f"  Уровень {zoom}: отсутствуют данные")
            all_levels_have_data = False
        else:
            print(f"  Уровень {zoom}: {count} тайлов")
    
    conn.close()
    
    return {
        "size": size,
        "size_mb": size_mb,
        "tile_count": tile_count,
        "zoom_levels": zoom_levels,
        "missing_zooms": list(missing_zooms),
        "metadata": metadata,
        "extent": {
            "min_column": min_col,
            "max_column": max_col,
            "min_row": min_row,
            "max_row": max_row
        },
        "all_levels_have_data": all_levels_have_data
    }

def main():
    parser = argparse.ArgumentParser(description='Валидация MBTiles файла')
    parser.add_argument('mbtiles_path', help='Путь к MBTiles файлу')
    
    args = parser.parse_args()
    
    try:
        result = validate_mbtiles(args.mbtiles_path)
        
        # Итоговая проверка
        if result['size_mb'] >= 200:
            print("✗ Валидация не пройдена: размер файла превышает 200 МБ")
            sys.exit(1)
        
        if not result['all_levels_have_data']:
            print("✗ Валидация не пройдена: отсутствуют данные на некоторых уровнях масштабирования")
            sys.exit(1)
        
        if result['missing_zooms']:
            print("⚠ Валидация пройдена с предупреждениями: отсутствуют некоторые уровни масштабирования")
        else:
            print("✓ Валидация пройдена успешно")
            
    except Exception as e:
        print(f"✗ Валидация не пройдена: {str(e)}")
        sys.exit(1)

if __name__ == "__main__":
    main()