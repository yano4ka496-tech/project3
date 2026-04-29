## plan.md

## Stage 1: Настройка навигации и граф навигации
**Goal:** Создать базовую структуру навигации с NavHost, реализовать deep links и добавить экраны ошибок
**Depends on:** none
**Inputs:** Существующие экраны (MapScreen, QRScannerScreen и др.), NavigationDestinations
**Outputs:**
- `app/src/main/java/com/safeplant/MainActivity.kt` (обновлён для NavHost)
- `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt` (реализация NavHost)
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt` (добавлены экраны ошибок)
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt` (новый файл с действиями навигации)
- `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt` (новый файл для обработки ошибок)
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavGraph.kt` (новый файл с графом навигации)
**DoD:**
- [ ] NavHost создан со всеми экранами (карта, квиз, обучение, профиль, QR-сканер)
- [ ] Реализован deep link: safeplant://map?zoneId={zoneId}
- [ ] Добавлены экраны ошибок (ZoneNotFound, AccessDenied)
- [ ] MainActivity обновлён для использования NavHost
- [ ] Созданы базовые действия навигации между экранами
**Risks:** Проблемы с парсингом deep link, конфликты в навигации

## Stage 2: Интеграция Hilt и логика навигации
**Goal:** Интегрировать Hilt с NavController, реализовать ViewModel для навигации и добавить проверку допусков
**Depends on:** Stage 1
**Inputs:** Существующие ViewModels, Stage 1 outputs, сущности базы данных (AccessPass, MapObject)
**Outputs:**
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt` (обновлён с Hilt)
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavGraph.kt` (добавлена логика проверки допусков)
- `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt` (обновлён для проверки допусков)
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt` (добавлен метод проверки)
- `core-database/src/main/java/com/safeplant/core/database/dao/MapObjectDao.kt` (добавлен метод поиска по ID)
**DoD:**
- [ ] Hilt интегрирован с NavController (использование hiltViewModel)
- [ ] ViewScreen обновлены для использования Hilt
- [ ] Реализована проверка действующего допуска при навигации на карту
- [ ] Добавлен диалог с предложением пройти квиз при отсутствии допуска
- [ ] Реализована логика поиска зоны по zoneId
**Risks:** Проблемы с инъекцией зависимостей, сложность логики проверки допусков

## Stage 3: Обработка ошибок и управление состоянием навигации
**Goal:** Реализовать детальную обработку ошибок навигации и управление состоянием при deep links
**Depends on:** Stage 2
**Inputs:** Stage 2 outputs, существующие репозитории
**Outputs:**
- `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt` (обновлён с детальной логикой)
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt` (добавлена обработка ошибок)
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt` (обновлён для навигации на карту)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` (добавлен сброс допуска)
- `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt` (обновлён для валидации zoneId)
**DoD:**
- [ ] Реализована обработка ошибок "Зона не найдена" для некорректного zoneId
- [ ] Реализована обработка ошибок "Доступ запрещен" при отсутствии допуска
- [ ] Добавлена логика немедленного перехода на карту при запуске через deep link
- [ ] Реализована обработка deep link без zoneId (карта с центром по умолчанию)
- [ ] Добавлен сброс допуска при обновлении версии приложения
**Risks:** Сложность управления состоянием при навигации, проблемы с обработкой edge cases

## Verify
- command: Проверка сборки приложения
  command: ./gradlew assembleDebug
- command: Проверка навигации между экранами
  command: ./gradlew connectedAndroidTest --tests "com.safeplant.MainActivityTest"
- command: Проверка deep links
  command: adb shell am start -W -a android.intent.action.VIEW -d "safeplant://map?zoneId=123" com.safeplant
- command: Проверка линтинга
  command: ./gradlew ktlintCheck
- command: Проверка детекта
  command: ./gradlew detekt

---

## tasks.md

# Задачи: Настройка навигации

## Stage 1: Настройка навигации и граф навигации
- [ ] Обновить MainActivity для использования NavHost → `app/src/main/java/com/safeplant/MainActivity.kt`
- [ ] Реализовать NavHost с экранами приложения → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- [ ] Добавить экраны ошибок в NavigationDestinations → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- [ ] Создать NavigationActions для управления навигацией → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt`
- [ ] Реализовать ErrorHandler для обработки ошибок → `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt`
- [ ] Создать NavGraph с определением маршрутов → `core-navigation/src/main/java/com/safeplant/core/navigation/NavGraph.kt`

## Stage 2: Интеграция Hilt и логика навигации
- [ ] Интегрировать Hilt с NavController в NavigationActions → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt`
- [ ] Добавить проверку допусков в NavGraph → `core-navigation/src/main/java/com/safeplant/core/navigation/NavGraph.kt`
- [ ] Обновить MapViewModel для проверки допусков → `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt`
- [ ] Добавить метод проверки допуска в AccessPassDao → `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- [ ] Добавить метод поиска зоны по ID в MapObjectDao → `core-database/src/main/java/com/safeplant/core/database/dao/MapObjectDao.kt`
- [ ] Обновить все ViewScreen для использования Hilt → `feature-*/src/main/java/com/safeplant/feature/*/Screen.kt`

## Stage 3: Обработка ошибок и управление состоянием навигации
- [ ] Реализовать обработку ошибок в ErrorHandler → `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt`
- [ ] Добавить логику deep link в NavigationActions → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt`
- [ ] Обновить QRScannerViewModel для навигации на карту → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt`
- [ ] Добавить сброс допуска в ProfileViewModel → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Обновить QRValidator для валидации zoneId → `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt`
- [ ] Реализовать диалог с предложением пройти квиз → `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt`