## plan.md

## Stage 1: Улучшение валидации QR-кодов
**Цель:** Реализовать строгую валидацию QR-кодов с санитизацией входных данных и обработкой ошибок.
**Зависит от:** Нет
**Входные данные:** 
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
**Выходные данные:**
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt` (обновлён)
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt` (обновлён)
**Критерии завершения:**
- [ ] QRValidator валидирует формат с использованием регулярных выражений и санитизирует входные данные
- [ ] QRScannerScreen показывает сообщение об ошибке и автоматически возвращается к сканеру при некорректном QR
- [ ] После ошибки приложение автоматически возвращается к экрану сканера без дополнительных действий пользователя
**Риски:** 
- Санитизация может повредить корректные QR-коды при неправильной реализации. Необходимы тесты с валидными и невалидными QR.

## Stage 2: Обнаружение root и ограничение доступа
**Цель:** Реализовать обнаружение root-доступа с предупреждением пользователя и ограничением доступа к опасным зонам.
**Зависит от:** Stage 1
**Входные данные:**
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
**Выходные данные:**
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt` (обновлён)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt` (обновлён)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` (обновлён)
**Критерии завершения:**
- [ ] RootDetector обнаруживает root и показывает предупреждающий диалог
- [ ] При обнаружении root доступ к опасным зонам ограничен (только безопасные зоны доступны)
- [ ] Предупреждающий диалог содержит информацию о рисках и предлагает продолжить работу с ограниченным функционалом
**Риски:** 
- Ложные срабатывания или пропуски root-доступа. Тестирование на различных устройствах с разными версиями Android.

## Stage 3: Управление доступом и контроль допусков
**Цель:** Реализовать EncryptedSharedPreferences для хранения данных допусков и немедленное ограничение доступа при истечении срока.
**Зависит от:** Stage 2
**Входные данные:**
- `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
**Выходные данные:**
- `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt` (обновлён)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` (обновлён)
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` (обновлён)
**Критерии завершения:**
- [ ] EncryptedSharedPreferences хранит дату истечения допуска и версию приложения
- [ ] При запуске приложения проверяется версия и при необходимости сбрасывается доступ
- [ ] При истечении срока действия допуска доступ к опасным зонам немедленно ограничивается
- [ ] Если пользователь находится в опасной зоне при истечении срока, система заставляет его немедленно покинуть зону
**Риски:** 
- Сложная логика контроля доступа может привести к ошибкам в различных сценариях. Необходимы интеграционные тесты.

## Stage 4: Интеграция навигации и проверка состояний
**Цель:** Интегрировать все компоненты системы безопасности и обеспечить корректную навигацию между экранами.
**Зависит от:** Stage 1, 2, 3
**Входные данные:**
- `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- `app/src/main/java/com/safeplant/MainActivity.kt`
**Выходные данные:**
- `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt` (обновлён)
- `app/src/main/java/com/safeplant/MainActivity.kt` (обновлён)
**Критерии завершения:**
- [ ] Навигация начинается с корректного экрана (карта при отсутствии root и действующем допуске, профиль в противном случае)
- [ ] Все экраны учитывают ограничения root и доступа
- [ ] QR-сканер доступен из соответствующих экранов
- [ ] При обновлении версии приложения доступ сбрасывается корректно
**Риски:** 
- Сложная логика навигации с множественными состояниями может привести к ошибкам. Тестирование всех путей навигации.

## Verify
```yaml
- name: Unit-тесты валидации QR
  command: ./gradlew :feature-qr:test --tests "*QRValidatorTest*"
- name: Unit-тесты обнаружения root
  command: ./gradlew :core-security:test --tests "*RootDetectorTest*"
- name: Unit-тесты шифрованного хранилища
  command: ./gradlew :core-security:test --tests "*EncryptedStorageTest*"
- name: Интеграционные тесты контроля доступа
  command: ./gradlew :feature-profile:test --tests "*ProfileViewModelTest*"
- name: Сборка приложения
  command: ./gradlew assembleDebug
```

---

## tasks.md

# Задачи: Система безопасности

## Stage 1: Улучшение валидации QR-кодов
- [ ] Реализовать строгую валидацию формата QR → `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- [ ] Добавить санитизацию входных строк → `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- [ ] Обработку ошибок QR в интерфейсе сканера → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- [ ] Автоматический возврат к сканеру после ошибки → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`

## Stage 2: Обнаружение root и ограничение доступа
- [ ] Интеграция RootBeer с диалогом предупреждения → `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- [ ] Обработка состояния root в ViewModel профиля → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Отображение предупреждения в интерфейсе профиля → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- [ ] Ограничение доступа к опасным зонам при обнаружении root → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`

## Stage 3: Управление доступом и контроль допусков
- [ ] Реализация EncryptedSharedPreferences для допусков → `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt`
- [ ] Проверка версии приложения и сброс доступа → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Немедленное ограничение доступа при истечении срока → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- [ ] Принудительное покидание опасной зоны при истечении срока → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`

## Stage 4: Интеграция навигации и проверка состояний
- [ ] Обновление навигационного графа с учетом состояний → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- [ ] Настройка начального экрана на основе состояний → `app/src/main/java/com/safeplant/MainActivity.kt`
- [ ] Корректная обработка переходов между экранами → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- [ ] Тестирование всех путей навигации → `tests/integration/test_navigation_security.kt`