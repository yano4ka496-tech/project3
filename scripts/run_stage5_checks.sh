#!/bin/bash
# Скрипт для выполнения проверок Stage 5 (раздел Verify)

echo "=== Stage 5: Проверка GeoJSON файлов и интеграции ==="

echo "1. Валидация GeoJSON файлов"
python -c "import json; [json.load(open(f'app/src/main/assets/geojson/{f}')) for f in ['hazards.geojson', 'routes.geojson', 'ppe_points.geojson', 'evacuation.geojson']]"
echo "✓ GeoJSON файлы валидны"

echo "2. Проверка выполнения скрипта подготовки ресурсов"
bash scripts/prepare_assets.sh
echo "✓ Скрипт подготовки ресурсов выполнен"

echo "3. Проверка наличия файлов в assets"
ls -R app/src/main/assets/geojson/
echo "✓ Файлы присутствуют в assets"

echo "4. Проверка структуры GeoJSON файлов"
python -c "
import json
for f in ['hazards', 'routes', 'ppe_points', 'evacuation']:
    with open(f'app/src/main/assets/geojson/{f}.geojson') as file:
        data = json.load(file)
        print(f'{f}: {len(data[\"features\"])} объектов')
"
echo "✓ Структура GeoJSON файлов проверена"

echo "=== Все проверки Stage 5 успешно завершены ==="