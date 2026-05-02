## plan.md

## Stage 1: Настройка структуры каталогов и базовых файлов
**Цель:** Создать структуру каталогов для GeoJSON файлов и подготовить базовые конфигурационные файлы
**Зависит от:** нет
**Входные данные:** Архитектурный документ, Конституция
**Выходные данные:**
- `app/src/main/assets/geojson/`
- `app/src/main/assets/geojson/hazards.geojson`
- `app/src/main/assets/geojson/routes.geojson`
- `app/src/main/assets/geojson/ppe_points.geojson`
- `app/src/main/assets/geojson/evacuation.geojson`
- `scripts/prepare_geojson.sh`
**Критерии завершения:**
- [ ] Каталог `app/src/main/assets/geojson/` создан
- [ ] Все четыре GeoJSON файла созданы с минимально валидной структурой
- [ ] Скрипт подготовки ресурсов обновлен для копирования GeoJSON файлов
- [ ] Файлы соответствуют формату GeoJSON
**Риски:** 
- Некорректная структура каталогов может привести к ошибкам при сборке
- Проблемы с правами доступа к каталогам

## Stage 2: Реализация GeoJSON файлов с данными
**Цель:** Заполнить GeoJSON файлы реальными данными с соблюдением требований к координатам и структуре
**Зависит от:** Stage 1
**Входные данные:** Пустые GeoJSON файлы из Stage 1
**Выходные данные:**
- `app/src/main/assets/geojson/hazards.geojson` (полигоны опасных зон)
- `app/src/main/assets/geojson/routes.geojson` (линии маршрутов)
- `app/src/main/assets/geojson/ppe_points.geojson` (точки СИЗ)
- `app/src/main/assets/geojson/evacuation.geojson` (пути эвакуации)
**Критерии завершения:**
- [ ] Все файлы содержат валидные данные в формате GeoJSON
- [ ] Координаты соответствуют системе WGS84 (EPSG:4326)
- [ ] Опасные зоны представлены как полигоны
- [ ] Маршруты и пути эвакуации представлены как LineString
- [ ] Точки СИЗ представлены как Point
- [ ] Каждый файл содержит свойства для стилизации (цвет, название)
**Риски:**
- Некорректные координаты могут привести к неправильному отображению на карте
- Отсутствие необходимых свойств может нарушить стилизацию слоев

## Stage 3: Валидация и интеграция
**Цель:** Проверить валидность GeoJSON файлов и обеспечить их корректную интеграцию в приложение
**Зависит от:** Stage 2
**Входные данные:** Заполненные GeoJSON файлы
**Выходные данные:**
- `scripts/validate_geojson.py`
- `scripts/prepare_assets.sh` (обновленный)
**Критерии завершения:**
- [ ] Все GeoJSON файлы проходят валидацию
- [ ] Скрипт подготовки ресурсов корректно копирует GeoJSON файлы
- [ ] Слой приоритизации работает корректно (опасные зоны > маршруты > СИЗ)
- [ ] Пути эвакуации отображаются под другими слоями
**Риски:**
- Проблемы с отображением слоев при перекрытии
- Ошибки в скрипте подготовки ресурсов

## Verify
- name: Валидация GeoJSON файлов
  command: python -c "import json; [json.load(open(f'app/src/main/assets/geojson/{f}')) for f in ['hazards.geojson', 'routes.geojson', 'ppe_points.geojson', 'evacuation.geojson']]"
- name: Проверка выполнения скрипта подготовки ресурсов
  command: bash scripts/prepare_assets.sh
- name: Проверка наличия файлов в assets
  command: ls -R app/src/main/assets/geojson/
- name: Проверка структуры GeoJSON файлов
  command: python -c "
import json
for f in ['hazards', 'routes', 'ppe_points', 'evacuation']:
    with open(f'app/src/main/assets/geojson/{f}.geojson') as file:
        data = json.load(file)
        print(f'{f}: {len(data[\"features\"])} объектов')
"

---

## tasks.md

# Задачи: Подготовка GeoJSON-файлов

## Stage 1: Настройка структуры каталогов и базовых файлов
- [ ] Создать структуру каталогов → `app/src/main/assets/geojson/`
- [ ] Создать базовый файл опасных зон → `app/src/main/assets/geojson/hazards.geojson`
- [ ] Создать базовый файл маршрутов → `app/src/main/assets/geojson/routes.geojson`
- [ ] Создать базовый файл точек СИЗ → `app/src/main/assets/geojson/ppe_points.geojson`
- [ ] Создать базовый файл путей эвакуации → `app/src/main/assets/geojson/evacuation.geojson`
- [ ] Обновить скрипт подготовки ресурсов → `scripts/prepare_assets.sh`

## Stage 2: Реализация GeoJSON файлов с данными
- [ ] Заполнить файл опасных зон полигонами → `app/src/main/assets/geojson/hazards.geojson`
- [ ] Заполнить файл маршрутов линиями → `app/src/main/assets/geojson/routes.geojson`
- [ ] Заполнить файл точек СИЗ точками → `app/src/main/assets/geojson/ppe_points.geojson`
- [ ] Заполнить файл путей эвакуации линиями → `app/src/main/assets/geojson/evacuation.geojson`
- [ ] Добавить свойства для стилизации (цвет, название) во все файлы

## Stage 3: Валидация и интеграция
- [ ] Создать скрипт валидации GeoJSON → `scripts/validate_geojson.py`
- [ ] Обновить скрипт подготовки ресурсов для GeoJSON → `scripts/prepare_assets.sh`
- [ ] Проверить приоритизацию слоев при отображении
- [ ] Убедиться в корректности интеграции с приложением