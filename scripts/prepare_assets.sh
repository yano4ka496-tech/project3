#!/bin/bash
# Скрипт для копирования подготовленных ресурсов в директорию assets

ASSETS_DIR="app/src/main/assets"

# Очистка и создание директории assets
rm -rf $ASSETS_DIR
mkdir -p $ASSETS_DIR/geojson
mkdir -p $ASSETS_DIR/csv
mkdir -p $ASSETS_DIR/videos

# Копирование MBTiles (если есть)
if [ -f "map.mbtiles" ]; then
    cp map.mbtiles $ASSETS_DIR/
    echo "Карта скопирована"
fi

# Копирование GeoJSON файлов
cp geojson/*.geojson $ASSETS_DIR/geojson/ 2>/dev/null || echo "GeoJSON файлы не найдены"

# Копирование CSV файлов
cp csv/*.csv $ASSETS_DIR/csv/ 2>/dev/null || echo "CSV файлы не найдены"

# Копирование видео
cp videos/*.mp4 $ASSETS_DIR/videos/ 2>/dev/null || echo "Видео файлы не найдены"

echo "Ресурсы готовы для сборки APK"
