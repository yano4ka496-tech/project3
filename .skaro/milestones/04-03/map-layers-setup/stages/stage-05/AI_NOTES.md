# AI_NOTES — Stage 3: Интеграция с экраном карты и обработка ошибок

## What was done
- Обновлен экран карты (MapScreen) для отображения слоев и управления ими
- Реализована ViewModel (MapViewModel) для управления состоянием карты и слоев
- Интегрирован компонент карты (MapView) с MapLibre GL Native
- Обновлен контроллер карты (MapController) для управления видимостью слоев
- Реализован рендерер карты (MapRenderer) для отрисовки слоев и обработки ошибок
- Добавлены unit-тесты для экрана карты (MapScreenTest)
- Реализована кнопка переключения слоя эвакуации
- Добавлена обработка ошибок при загрузке GeoJSON
- Реализована проверка доступа для слоя эвакуации
- Добавлены уведомления об ошибках
- Реализована логика видимости слоев в зависимости от масштаба

## Why this approach
- Использование MVVM паттерна для разделения ответственности между UI и логикой
- Интеграция с MapLibre GL Native для отображения векторных карт
- Реализация LayerManager для централизованного управления слоями
- Использование StateFlow для реактивного обновления UI
- Добавление comprehensive тестирования для проверки функциональности

## Files created / modified
| File | Action | Description |
|---|---|---|
| `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` | modified | Обновлен экран карты для отображения слоев и управления ими |
| `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt` | modified | Реализована ViewModel для управления состоянием карты и слоев |
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt` | modified | Интегрирован компонент карты с MapLibre GL Native |
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapController.kt` | modified | Обновлен контроллер карты для управления видимостью слоев |
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` | modified | Реализован рендерер карты для отрисовки слоев и обработки ошибок |
| `feature-map/src/test/java/com/safeplant/feature/map/MapScreenTest.kt` | modified | Добавлены unit-тесты для экрана карты |

## Risks and limitations
- Интеграция с MapLibre GL Native может потребовать дополнительных настроек для работы в офлайн-режиме
- Обработка больших GeoJSON файлов может повлиять на производительность
- Сложность тестирования интеграции с реальной картой в unit-тестах

## Invariant compliance
- [ ] Offline-first — приложение не выполняет сетевых запросов, все данные загружаются из assets
- [ ] Security — проверка root-доступа и действующего допуска перед отображением слоя эвакуации
- [ ] Performance — оптимизация отрисовки слоев в зависимости от масштаба карты
- [ ] Error handling — обработка ошибок загрузки GeoJSON и отображение уведомлений

## How to verify
1. Запустить unit тесты: `./gradlew :feature-map:testDebugUnitTest --tests "com.safeplant.feature.map.*"`
2. Запустить интеграционные тесты: `./gradlew :feature-map:connectedAndroidTest --tests "com.safeplant.feature.map.MapScreenIntegrationTest"`
3. Проверить сборку: `./gradlew :feature-map:assembleDebug`
4. Проверить стиль кода: `./gradlew :feature-map:detekt`
5. Проверить покрытие кода: `./gradlew :feature-map:testDebugUnitTest --tests "com.safeplant.feature.map.*" --jacoco`