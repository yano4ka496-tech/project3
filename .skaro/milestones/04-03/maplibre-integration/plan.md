## plan.md

## Stage 1: Настройка проекта и базовая структура
**Goal:** Создать базовую структуру проекта и необходимые конфигурации для работы с MapLibre.
**Depends on:** none
**Inputs:** Architecture document, Constitution
**Outputs:**
- `app/build.gradle.kts` - добавить зависимость MapLibre
- `feature-map/build.gradle.kts` - обновить зависимости (MapLibre, Hilt)
- `core-mapping/build.gradle.kts` - обновить зависимости (MapLibre, Hilt)
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` - базовая реализация
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt` - базовый MapView для Compose
- `app/src/main/assets/map.mbtiles` - добавить тестовый MBTiles (заглушка)
**DoD:**
- [ ] Зависимости MapLibre добавлены в соответствующие модули
- [ ] Базовые классы MapRenderer и MapView созданы
- [ ] Тестовый MBTiles файл добавлен в assets
**Risks:** Проблемы с совместимостью версий MapLibre

## Stage 2: Реализация MapView и загрузка MBTiles
**Goal:** Реализовать отображение карты, загрузку MBTiles и управление камерой.
**Depends on:** Stage 1
**Inputs:** Stage 1, тестовый MBTiles
**Outputs:**
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` - реализация загрузки MBTiles и отрисовки
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt` - реализация MapView для Compose
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapController.kt` - управление камерой и масштабом
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` - обновление экрана карты
**DoD:**
- [ ] Карта отображается с MBTiles
- [ ] Управление камерой (перемещение, масштаб) работает
- [ ] Блокировка масштабирования на границах (10 м - 10 км) реализована
**Risks:** Проблемы с производительностью при отрисовке карты

## Stage 3: Интеграция с системой допусков и безопасность
**Goal:** Реализовать проверку допуска, отображение предупреждений и обработку root.
**Depends on:** Stage 2
**Inputs:** Stage 2, AccessPassDao, RootDetector
**Outputs:**
- `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt` - ViewModel для карты
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` - обновление с проверкой допуска
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt` - реализация проверки root
- `core-security/src/main/java/com/safeplant/core/security/RootDetectorImpl.kt` - реализация с RootBeer
**DoD:**
- [ ] Проверка наличия действующего допуска реализована
- [ ] Отображение предупреждения о скором истечении срока (менее 5 минут) реализовано
- [ ] Обработка root-доступа (предупреждение) реализована
- [ ] Обработка отсутствия допуска (заглушка с предложением пройти квиз) реализована
**Risks:** Проблемы с производительностью при частой проверке допуска

## Stage 4: Обработка ошибок и оптимизация
**Goal:** Реализовать обработку ошибок (поврежденный MBTiles) и оптимизацию производительности.
**Depends on:** Stage 3
**Inputs:** Stage 3
**Outputs:**
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` - добавление обработки ошибок
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` - отображение ошибок
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapPerformanceOptimizer.kt` - оптимизация
**DoD:**
- [ ] Обработка поврежденного или отсутствующего файла MBTiles реализована
- [ ] Оптимизация производительности (60 FPS) реализована
- [ ] Тестирование в режиме "в самолете" подтверждено
**Risks:** Сложность достижения 60 FPS на слабых устройствах

## Stage 5: Интеграция с экраном карты
**Goal:** Интегрировать все компоненты в экран карты (MapScreen) и навигацию.
**Depends on:** Stage 4
**Inputs:** Stage 4, существующая навигация
**Outputs:**
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` - финальная версия
- `feature-map/src/test/java/com/safeplant/feature/map/MapScreenTest.kt` - UI тесты
- `feature-map/src/androidTest/java/com/safeplant/feature/map/MapScreenIntegrationTest.kt` - интеграционные тесты
**DoD:**
- [ ] Экран карты полностью интегрирован
- [ ] Навигация с других экранов на карту работает
- [ ] UI тесты пройдены
- [ ] Интеграционные тесты пройдены
**Risks:** Проблемы с навигацией между экранами

## Verify
- name: Unit tests
  command: ./gradlew test --tests "*MapRendererTest*"
- name: Unit tests
  command: ./gradlew test --tests "*MapViewModelTest*"
- name: Интеграционные тесты
  command: ./gradlew connectedAndroidTest --tests "*MapScreen*"
- name: Проверка производительности
  command: ./gradlew connectedAndroidTest --tests "*MapPerformanceTest*"
- name: Lint
  command: ./gradlew lint
- name: Detekt
  command: ./gradlew detekt