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

# Копирование GeoJSON файлов из app/src/main/assets/geojson/
if [ -d "app/src/main/assets/geojson" ]; then
    cp app/src/main/assets/geojson/*.geojson $ASSETS_DIR/geojson/
    echo "GeoJSON файлы скопированы"
else
    echo "Директория с GeoJSON файлами не найдена"
fi

# Копирование CSV файлов
if [ -d "app/src/main/assets/csv" ]; then
    cp app/src/main/assets/csv/*.csv $ASSETS_DIR/csv/
    echo "CSV файлы скопированы"
else
    echo "Директория с CSV файлами не найдена"
fi

# Копирование видео
if [ -d "app/src/main/assets/videos" ]; then
    cp app/src/main/assets/videos/*.mp4 $ASSETS_DIR/videos/
    echo "Видео файлы скопированы"
else
    echo "Директория с видео файлами не найдена"
fi

echo "Ресурсы готовы для сборки APK"