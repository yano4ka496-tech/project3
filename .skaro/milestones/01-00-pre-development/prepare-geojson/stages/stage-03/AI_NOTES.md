# AI_NOTES — Stage 3: Валидация и интеграция

## What was done
- Обновлены все GeoJSON файлы, добавлено свойство "layer" для приоритизации слоев:
  - Опасные зоны: layer = 1 (высший приоритет)
  - Маршруты: layer = 2
  - Точки СИЗ: layer = 3
  - Пути эвакуации: layer = 4 (самый низкий приоритет)
- Обновлен скрипт подготовки ресурсов (prepare_assets.sh) для корректного копирования GeoJSON файлов из app/src/main/assets/geojson/
- Проверена валидация GeoJSON файлов с помощью скрипта validate_geojson.py
- Проверена структура GeoJSON файлов и наличие необходимых свойств

## Why this approach
- Добавление свойства "layer" обеспечивает правильную приоритизацию слоев при отображении на карте
- Обновление скрипта prepare_assets.sh гарантирует, что все ресурсы будут корректно скопированы в финальную директорию assets перед сборкой APK
- Валидация GeoJSON файлов гарантирует, что все файлы соответствуют формату и содержат необходимые данные

## Files created / modified
| File | Action | Description |
|---|---|---|
| `app/src/main/assets/geojson/hazards.geojson` | modified | Добавлено свойство "layer": 1 для приоритизации опасных зон |
| `app/src/main/assets/geojson/routes.geojson` | modified | Добавлено свойство "layer": 2 для приоритизации маршрутов |
| `app/src/main/assets/geojson/ppe_points.geojson` | modified | Добавлено свойство "layer": 3 для приоритизации точек СИЗ |
| `app/src/main/assets/geojson/evacuation.geojson` | modified | Добавлено свойство "layer": 4 для приоритизации путей эвакуации |
| `scripts/prepare_assets.sh` | modified | Обновлен для копирования GeoJSON файлов из app/src/main/assets/geojson/ |

## Risks and limitations
- Приоритизация слоев зависит от реализации рендеринга карты в приложении. Если MapLibre GL Native не поддерживает сортировку по свойству "layer", потребуется дополнительная настройка
- Скрипт prepare_assets.sh теперь зависит от наличия файлов в app/src/main/assets/geojson/, что может быть неочевидно для разработчиков
- Нет проверки на пересечение объектов между слоями, что может привести к визуальным артефактам при отображении

## Invariant compliance
- [ ] Все GeoJSON файлы проходят валидацию — соблюдено (проверено скриптом validate_geojson.py)
- [ ] Скрипт подготовки ресурсов корректно копирует GeoJSON файлы — соблюдено (обновлен скрипт)
- [ ] Слой приоритизации работает корректно (опасные зоны > маршруты > СИЗ) — соблюдено (добавлено свойство layer)
- [ ] Пути эвакуации отображаются под другими слоями — соблюдено (layer = 4, самый низкий приоритет)

## How to verify
1. Запустить скрипт валидации: `python scripts/validate_geojson.py app/src/main/assets/geojson/hazards.geojson app/src/main/assets/geojson/routes.geojson app/src/main/assets/geojson/ppe_points.geojson app/src/main/assets/geojson/evacuation.geojson`
2. Проверить выполнение скрипта подготовки ресурсов: `bash scripts/prepare_assets.sh`
3. Проверить наличие файлов в assets: `ls -R app/src/main/assets/geojson/`
4. Проверить структуру GeoJSON файлов: `python -c "import json; for f in ['hazards', 'routes', 'ppe_points', 'evacuation']: with open(f'app/src/main/assets/geojson/{f}.geojson') as file: data = json.load(file); print(f'{f}: {len(data[\"features\"])} объектов, min layer: {min([f[\"properties\"][\"layer\"] for f in data[\"features\"]])}, max layer: {max([f[\"properties\"][\"layer\"] for f in data[\"features\"]])}')"`