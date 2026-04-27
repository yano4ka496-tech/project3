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