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
    
    # Создаем временный MBTiles файл
    TEMP_MBTILES=$(mktemp).mbtiles
    
    # Запускаем конвертацию тестового PDF
    if python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf "$TEMP_MBTILES"; then
        echo "✓ Конвертация PDF в MBTiles прошла успешно"
        
        # Проверяем, что файл MBTiles создан
        if [ -f "$TEMP_MBTILES" ]; then
            echo "✒ Файл MBTiles создан: $TEMP_MBTILES"
            
            # Проверяем содержимое файла (минимальная проверка)
            if file "$TEMP_MBTILES" | grep -q "SQLite"; then
                echo "✒ Файл MBTiles является корректным SQLite файлом"
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