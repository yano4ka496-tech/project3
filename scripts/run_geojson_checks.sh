#!/bin/bash

echo "=== Запуск проверок GeoJSON файлов ==="

# Проверка 1: Валидация GeoJSON файлов
echo "1. Валидация GeoJSON файлов..."
python scripts/validate_geojson.py app/src/main/assets/geojson/hazards.geojson app/src/main/assets/geojson/routes.geojson app/src/main/assets/geojson/ppe_points.geojson app/src/main/assets/geojson/evacuation.geojson
if [ $? -ne 0 ]; then
    echo "Ошибка: валидация GeoJSON файлов не пройдена"
    exit 1
fi

# Проверка 2: Проверка выполнения скрипта подготовки ресурсов
echo "2. Проверка выполнения скрипта подготовки ресурсов..."
bash scripts/prepare_assets.sh
if [ $? -ne 0 ]; then
    echo "Ошибка: скрипт подготовки ресурсов завершился с ошибкой"
    exit 1
fi

# Проверка 3: Проверка наличия файлов в assets
echo "3. Проверка наличия файлов в assets..."
if [ ! -d "app/src/main/assets/geojson" ]; then
    echo "Ошибка: директория app/src/main/assets/geojson не существует"
    exit 1
fi

files=("hazards.geojson" "routes.geojson" "ppe_points.geojson" "evacuation.geojson")
for file in "${files[@]}"; do
    if [ ! -f "app/src/main/assets/geojson/$file" ]; then
        echo "Ошибка: файл app/src/main/assets/geojson/$file не найден"
        exit 1
    fi
done
echo "Все файлы присутствуют"

# Проверка 4: Проверка структуры GeoJSON файлов
echo "4. Проверка структуры GeoJSON файлов..."
python -c "
import json
for f in ['hazards', 'routes', 'ppe_points', 'evacuation']:
    with open(f'app/src/main/assets/geojson/{f}.geojson') as file:
        data = json.load(file)
        print(f'{f}: {len(data[\"features\"])} объектов')
"
if [ $? -ne 0 ]; then
    echo "Ошибка: проверка структуры GeoJSON файлов не пройдена"
    exit 1
fi

echo "=== Все проверки успешно пройдены ==="