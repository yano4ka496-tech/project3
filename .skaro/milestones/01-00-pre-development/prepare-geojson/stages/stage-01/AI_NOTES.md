# AI_NOTES — Stage 1: Настройка структуры каталогов и базовых файлов

## What was done
- Создана структура каталогов для GeoJSON файлов в `app/src/main/assets/geojson/`
- Созданы четыре базовых GeoJSON файла с минимально валидной структурой:
  - `hazards.geojson` - полигоны опасных зон
  - `routes.geojson` - линии предустановленных маршрутов
  - `ppe_points.geojson` - точки СИЗ
  - `evacuation.geojson` - пути эвакуации
- Создан скрипт `scripts/prepare_geojson.sh` для копирования GeoJSON файлов в директорию assets

## Why this approach
- Следование архитектурному плану и требованиям к структуре проекта
- Использование минимально валидной структуры GeoJSON для соответствия формату
- Создание скрипта автоматизации для подготовки ресурсов перед сборкой APK
- Соответствие требованиям к координатной системе WGS84 (EPSG:4326)

## Files created / modified
| File | Action | Description |
|---|---|---|
| `app/src/main/assets/geojson/hazards.geojson` | created | Базовый файл с полигонами опасных зон |
| `app/src/main/assets/geojson/routes.geojson` | created | Базовый файл с линиями маршрутов |
| `app/src/main/assets/geojson/ppe_points.geojson` | created | Базовый файл с точками СИЗ |
| `app/src/main/assets/geojson/evacuation.geojson` | created | Базовый файл с путями эвакуации |
| `scripts/prepare_geojson.sh` | created | Скрипт для копирования GeoJSON файлов в assets |

## Risks and limitations
- Файлы содержат тестовые данные, которые будут заменены на реальные в следующей стадии
- Нет валидации данных на соответствие реальным объектам
- Нет проверки на перекрытие слоев

## Invariant compliance
- [ ] Структура каталогов соответствует архитектуре проекта — соблюдено
- [ ] GeoJSON файлы соответствуют формату GeoJSON — соблюдено
- [ ] Скрипт подготовки ресурсов создан — соблюдено
- [ ] Каталог assets создан — соблюдено

## How to verify
1. Проверить наличие каталога `app/src/main/assets/geojson/`
2. Проверить наличие всех четырех GeoJSON файлов
3. Запустить скрипт `bash scripts/prepare_geojson.sh` для копирования файлов
4. Проверить валидность GeoJSON файлов через онлайн-валидатор (geojson.io)