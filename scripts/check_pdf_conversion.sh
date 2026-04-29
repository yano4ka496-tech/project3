#!/bin/bash

# Скрипт для проверки конвертации PDF в MBTiles

# Проверяем наличие скрипта конвертации
if [ -f "scripts/convert_pdf_to_mbtiles.py" ]; then
    echo "Проверка конвертации PDF в MBTiles..."
    
    # Проверяем наличие зависимостей
    if command -v ogr2ogr >/dev/null 2>&1; then
        echo "✒ ogr2ogr доступен"
    else
        echo "✗ ogr2ogr не найден, установка..."
        sudo apt-get update
        sudo apt-get install -y gdal-bin
    fi
    
    if command -v tippecanoe >/dev/null 2>&1; then
        echo "✒ tippecanoe доступен"
    else
        echo "✗ tippecanoe не найден, установка..."
        sudo apt-get update
        sudo apt-get install -y tippecanoe
    fi
    
    if command -v sqlite3 >/dev/null 2>&1; then
        echo "✒ sqlite3 доступен"
    else
        echo "✗ sqlite3 не найден, установка..."
        sudo apt-get update
        sudo apt-get install -y sqlite3
    fi
    
    # Проверяем наличие тестового PDF
    if [ -f "tests/data/pdf/test.pdf" ]; then
        echo "✒ Тестовый PDF найден: tests/data/pdf/test.pdf"
    else
        echo "✗ Тестовый PDF не найден: tests/data/pdf/test.pdf"
        exit 1
    fi
    
    # Создаем временный MBTiles файл
    TEMP_MBTILES=$(mktemp).mbtiles
    
    # Запускаем конвертацию тестового PDF
    if python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf "$TEMP_MBTILES"; then
        echo "✓ Конвертация PDF в MBTiles прошла успешно"
        
        # Проверяем, что файл MBTiles создан
        if [ -f "$TEMP_MBTILES" ]; then
            echo "✒ Файл MBTiles создан: $TEMP_MBTILES"
            
            # Проверяем размер файла (<200 МБ)
            size=$(stat -c%s "$TEMP_MBTILES")
            if [ $size -lt 209715200 ]; then # 200 МБ
                echo "✒ Размер MBTiles: $size байт (< 200 МБ) - OK"
            else
                echo "✗ Размер MBTiles: $size байт (>= 200 МБ) - FAILED"
                exit 1
            fi
            
            # Проверяем содержимое файла (минимальная проверка)
            if file "$TEMP_MBTILES" | grep -q "SQLite"; then
                echo "✒ Файл MBTiles является корректным SQLite файлом"
                
                # Проверяем наличие таблицы tiles
                if sqlite3 "$TEMP_MBTILES" "SELECT name FROM sqlite_master WHERE type='table' AND name='tiles';" | grep -q "tiles"; then
                    echo "✒ MBTiles содержит таблицу tiles - OK"
                else
                    echo "✗ MBTiles не содержит таблицу tiles - FAILED"
                    exit 1
                fi
                
                # Проверяем наличие таблицы metadata
                if sqlite3 "$TEMP_MBTILES" "SELECT name FROM sqlite_master WHERE type='table' AND name='metadata';" | grep -q "metadata"; then
                    echo "✒ MBTiles содержит таблицу metadata - OK"
                else
                    echo "✗ MBTiles не содержит таблицу metadata - FAILED"
                    exit 1
                fi
                
                # Проверяем уровни масштабирования
                echo "Проверка уровней масштабирования:"
                sqlite3 "$TEMP_MBTILES" "SELECT DISTINCT zoom_level FROM tiles ORDER BY zoom_level" | while read zoom; do
                    echo "  Уровень $zoom: $(sqlite3 "$TEMP_MBTILES" "SELECT COUNT(*) FROM tiles WHERE zoom_level = $zoom") тайлов"
                done
                
                # Проверяем наличие данных на всех уровнях от 0 до 18
                echo "Проверка наличия данных на всех уровнях масштабирования:"
                all_levels_have_data=true
                for zoom in $(seq 0 18); do
                    count=$(sqlite3 "$TEMP_MBTILES" "SELECT COUNT(*) FROM tiles WHERE zoom_level = $zoom")
                    if [ $count -eq 0 ]; then
                        echo "  ✗ Уровень $zoom: отсутствуют данные"
                        all_levels_have_data=false
                    else
                        echo "  ✓ Уровень $zoom: $count тайлов"
                    fi
                done
                
                if [ "$all_levels_have_data" = true ]; then
                    echo "✓ Все уровни масштабирования (0-18) имеют данные"
                else
                    echo "✗ Некоторые уровни масштабирования не имеют данных"
                    exit 1
                fi
            else
                echo "✗ Файл MBTiles не является корректным SQLite файлом"
                exit 1
            fi
        else
            echo "✗ Файл MBTiles не создан"
            exit 1
        fi
    else
        echo "✗ Конвертация PDF в MBtiles не удалась"
        exit 1
    fi
    
    # Очистка
    rm -f "$TEMP_MBTILES"
    
    echo "✓ Проверка конвертации PDF в MBTiles прошла успешно"
else
    echo "✗ Скрипт convert_pdf_to_mbtiles.py не найден"
    exit 1
fi

exit 0