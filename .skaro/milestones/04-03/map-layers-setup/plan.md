# План реализации: Слои карты

## Stage 1: Основная инфраструктура слоев карты
**Цель:** Создать базовую инфраструктуру для управления слоями карты и их стилей
**Зависит от:** отсутствует
**Входные данные:** Архитектура проекта, спецификация слоев
**Выходные файлы:**
- `core-mapping/src/main/java/com/safeplant/core/mapping/LayerManager.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/LayerType.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/LayerStyle.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapLayer.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/LayerFactory.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/BaseLayer.kt`

**Критерии завершения:**
- [ ] Создана система типов слоев (LayerType)
- [ ] Определены стили для каждого слоя (LayerStyle)
- [ ] Реализован базовый класс MapLayer
- [ ] Создан LayerManager для управления слоями
- [ ] Реализован LayerFactory для создания слоев
- [ ] Все классы имеют документацию на русском языке

**Риски:**
- Несоответствие стилей требованиям спецификации
- Проблемы с производительностью при большом количестве слоев

## Stage 2: Реализация конкретных слоев
**Цель:** Реализовать конкретные типы слоев с их специфической логикой и стилями
**Зависит от:** Stage 1
**Входные данные:** Базовая инфраструктура слоев, GeoJSON-файлы из assets
**Выходные файлы:**
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/HazardsLayer.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/RoutesLayer.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/EvacuationLayer.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/PPEPointsLayer.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/layers/PopupInfo.kt`

**Критерии завершения:**
- [ ] Реализован слой опасных зон (красные полигоны)
- [ ] Реализован слой маршрутов (синие линии)
- [ ] Реализован слой эвакуации (зеленые линии)
- [ ] Реализован слой точек СИЗ (желтые иконки)
- [ ] Добавлена обработка всплывающих подсказок
- [ ] Реализована проверка доступа для слоя эвакуации
- [ ] Добавлена логика видимости в зависимости от масштаба

**Риски:**
- Проблемы с парсингом GeoJSON
- Некорректная обработка всплывающих окон

## Stage 3: Интеграция с экраном карты и обработка ошибок
**Цель:** Интегрировать слои в экран карты и реализовать обработку ошибок
**Зависит от:** Stage 2
**Входные данные:** Реализованные слои, существующий MapScreen и MapViewModel
**Выходные файлы:**
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` (обновлено)
- `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt` (обновлено)
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt` (обновлено)
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapController.kt` (обновлено)
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` (обновлено)

**Критерии завершения:**
- [ ] Слои интегрированы в MapScreen
- [ ] Реализована кнопка переключения слоя эвакуации
- [ ] Добавлена обработка ошибок при загрузке GeoJSON
- [ ] Реализована проверка доступа для слоя эвакуации
- [ ] Добавлены уведомления об ошибках
- [ ] Реализована логика видимости слоев в зависимости от масштаба
- [ ] Обновлены тесты для MapScreen

**Риски:**
- Конфликты с существующим кодом
- Проблемы с производительностью при отображении слоев

---

## Verify
```yaml
- name: Unit тесты для слоев карты
  command: ./gradlew :feature-map:testDebugUnitTest --tests "com.safeplant.feature.map.*"
- name: Интеграционные тесты для MapScreen
  command: ./gradlew :feature-map:connectedAndroidTest --tests "com.safeplant.feature.map.MapScreenIntegrationTest"
- name: Проверка покрытия кода
  command: ./gradlew :feature-map:testDebugUnitTest --tests "com.safeplant.feature.map.*" --jacoco
- name: Проверка стиля кода
  command: ./gradlew :feature-map:detekt
- name: Проверка сборки
  command: ./gradlew :feature-map:assembleDebug
```

---

# Задачи: Реализация слоев карты

## Stage 1: Основная инфраструктура слоев карты
- [ ] Создать перечисление типов слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/LayerType.kt`
- [ ] Определить стили для каждого слоя → `core-mapping/src/main/java/com/safeplant/core/mapping/LayerStyle.kt`
- [ ] Реализовать базовый класс слоя → `core-mapping/src/main/java/com/safeplant/core/mapping/MapLayer.kt`
- [ ] Создать менеджер слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/LayerManager.kt`
- [ ] Реализовать фабрику слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/LayerFactory.kt`
- [ ] Создать базовый класс для конкретных слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/BaseLayer.kt`

## Stage 2: Реализация конкретных слоев
- [ ] Реализовать слой опасных зон → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/HazardsLayer.kt`
- [ ] Реализовать слой маршрутов → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/RoutesLayer.kt`
- [ ] Реализовать слой эвакуации → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/EvacuationLayer.kt`
- [ ] Реализовать слой точек СИЗ → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/PPEPointsLayer.kt`
- [ ] Создать класс для информации всплывающих окон → `core-mapping/src/main/java/com/safeplant/core/mapping/layers/PopupInfo.kt`

## Stage 3: Интеграция с экраном карты и обработка ошибок
- [ ] Обновить MapScreen для отображения слоев → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- [ ] Обновить MapViewModel для управления слоями → `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt`
- [ ] Обновить MapView для рендеринга слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt`
- [ ] Обновить MapController для управления слоями → `core-mapping/src/main/java/com/safeplant/core/mapping/MapController.kt`
- [ ] Обновить MapRenderer для отрисовки слоев → `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt`
- [ ] Обновить тесты для MapScreen → `feature-map/src/test/java/com/safeplant/feature/map/MapScreenTest.kt`
- [ ] Обновить интеграционные тесты → `feature-map/src/androidTest/java/com/safeplant/feature/map/MapScreenIntegrationTest.kt`