# Tasks: Интеграция MapLibre

## Stage 1: Настройка проекта и базовая структура
- [ ] Добавить зависимость MapLibre в `app/build.gradle.kts`
- [ ] Обновить зависимости в `feature-map/build.gradle.kts` (MapLibre, Hilt)
- [ ] Обновить зависимости в `core-mapping/build.gradle.kts` (MapLibre, Hilt)
- [ ] Создать базовый класс `MapRenderer` в `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt`
- [ ] Создать базовый класс `MapView` в `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt`
- [ ] Добавить тестовый MBTiles файл в `app/src/main/assets/map.mbtiles`

## Stage 2: Реализация MapView и загрузка MBTiles
- [ ] Реализовать загрузку MBTiles в `MapRenderer`
- [ ] Реализовать отрисовку карты в `MapRenderer`
- [ ] Реализовать управление камерой в `MapController`
- [ ] Реализовать блокировку масштабирования в `MapController`
- [ ] Обновить `MapScreen` для использования `MapView`

## Stage 3: Интеграция с системой допусков и безопасность
- [ ] Создать `MapViewModel` для проверки допуска
- [ ] Реализовать проверку допуска в `MapViewModel`
- [ ] Реализовать отображение предупреждения о скором истечении срока
- [ ] Реализовать проверку root в `RootDetector`
- [ ] Реализовать отображение предупреждения о root
- [ ] Реализовать заглушку при отсутствии допуска

## Stage 4: Обработка ошибок и оптимизация
- [ ] Реализовать обработку ошибок загрузки MBTiles
- [ ] Реализовать отображение ошибки при поврежденном MBTiles
- [ ] Реализовать оптимизацию производительности (60 FPS)
- [ ] Тестирование в режиме "в самолете"

## Stage 5: Интеграция с экраном карты
- [ ] Финальная интеграция всех компонентов в `MapScreen`
- [ ] Реализовать навигацию с других экранов на карту
- [ ] Написать UI тесты для `MapScreen`
- [ ] Написать интеграционные тесты для `MapScreen`