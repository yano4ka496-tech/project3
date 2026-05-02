#!/bin/bash
# Скрипт для подготовки GeoJSON файлов

ASSETS_DIR="app/src/main/assets"

# Очистка и создание директории assets
rm -rf $ASSETS_DIR
mkdir -p $ASSETS_DIR/geojson
mkdir -p $ASSETS_DIR/csv
mkdir -p $ASSETS_DIR/videos

# Копирование GeoJSON файлов
cp geojson/*.geojson $ASSETS_DIR/geojson/ 2>/dev/null || echo "GeoJSON файлы не найдены"

echo "GeoJSON файлы готовы для сборки APK"