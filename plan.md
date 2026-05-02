# План реализации: Система безопасности

## Этап 1: Обновление валидатора QR-кодов и интеграция с базой
**Цель:** Реализовать строгую валидацию QR-кодов согласно новым требованиям и интегрировать с базой данных.
**Зависит от:** нет
**Входы:** существующие файлы в feature-qr и core-database
**Выходы:**
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt` (обновлен)
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt` (обновлен для обработки ошибок и повторного сканирования)
- `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt` (уже есть, но нужно проверить)
- `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt` (уже есть, но нужно проверить)

**DoD:**
- [ ] QRValidator.validate() использует регулярные выражения для проверки формата
- [ ] QRValidator.extractObjectId() и extractName() работают с валидными данными
- [ ] QRScannerScreen показывает сообщение об ошибке при некорректном QR и предоставляет возможность повторного сканирования
- [ ] QRScannerScreen использует базу данных для поиска objectId

**Риски:** 
- Регулярные выражения могут быть неэффективны, но для коротких строк это не проблема.
- В текущей реализации QRScannerScreen имитирует обнаружение QR-кода, нужно заменить на реальную библиотеку.

## Этап 2: Обновление системы обнаружения root и управление доступом
**Цель:** Обновить RootDetector для соответствия требованиям и добавить управление доступом на основе root.
**Зависит от:** Этап 1
**Входы:** существующий RootDetector.kt, feature-profile
**Выходы:**
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt` (обновлен)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt` (добавлен диалог предупреждения при root)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` (добавлена логика ограничения доступа)

**DoD:**
- [ ] RootDetector.isRooted() использует RootBeer и дополнительные проверки
- [ ] При обнаружении root показывается предупреждающий диалог
- [ ] При обнаружении root функциональность приложения ограничена (например, нельзя сканировать QR или доступ к опасным зонам)
- [ ] ProfileViewModel содержит логику проверки root и ограничения доступа

**Риски:**
- Обнаружение root не на 100% надежно, но это компромисс.
- Ограничение функциональности должно быть хорошо задокументировано.

## Этап 3: Управление сроком действия пропуска и EncryptedSharedPreferences
**Цель:** Реализовать хранение даты истечения пропуска и версии приложения в EncryptedSharedPreferences и проверку доступа.
**Зависит от:** Этап 1, Этап 2
**Входы:** существующие AccessPass, AccessPassDao, feature-profile
**Выходы:**
- `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt` (новый класс для работы с EncryptedSharedPreferences)
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt` (обновлен для работы с EncryptedSharedPreferences)
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` (обновлен для проверки срока действия пропуска)
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` (добавлена проверка доступа при входе в опасную зону)

**DoD:**
- [ ] EncryptedStorage хранит дату истечения пропуска и версию приложения
- [ ] При запуске приложения проверяется версия и при обновлении сбрасывается допуск
- [ ] При истечении срока действия пропуска доступ к опасным зонам немедленно ограничивается
- [ ] При попытке входа в опасную зону проверяется срок действия пропуска

**Риски:**
- EncryptedSharedPreferences может быть медленным, но для хранения нескольких значений это приемлемо.
- Проверка версии при запуске должна быть надежной.

## Этап 4: Тестирование и проверка
**Цель:** Написать тесты для всех новых компонентов и проверить их работу.
**Зависит от:** Этап 1, Этап 2, Этап 3
**Входы:** все созданные и обновленные файлы
**Выходы:**
- `tests/unit/test_qr_validator.kt` (тесты для QRValidator)
- `tests/unit/test_root_detector.kt` (тесты для RootDetector)
- `tests/unit/test_encrypted_storage.kt` (тесты для EncryptedStorage)
- `tests/integration/test_access_control.kt` (интеграционные тесты для управления доступом)

**DoD:**
- [ ] Все тесты проходят успешно
- [ ] Покрытие кода не менее 70% для бизнес-логики
- [ ] Интеграционные тесты проверяют полный сценарий: сканирование QR, проверка root, проверка срока действия пропуска

**Риски:**
- Тестирование на реальном устройстве может быть сложным, но для unit и интеграционных тестов это не требуется.

---

## Verify

```bash
# Unit tests QR Validator
./gradlew :feature-qr:test --tests "*.QRValidatorTest"

# Unit tests Root Detector
./gradlew :core-security:test --tests "*.RootDetectorTest"

# Unit tests Encrypted Storage
./gradlew :core-security:test --tests "*.EncryptedStorageTest"

# Integration tests Access Control
./gradlew :feature-profile:test --tests "*.AccessControlTest"

# Check database encryption
./scripts/check_database_encryption.sh

# Check root detection
./scripts/check_root_detection.sh

# Check QR format validation
./scripts/validate_qr_format.sh

# Check version reset
./scripts/check_version_reset.sh
```

---

## Задачи: Система безопасности

## Этап 1: Обновление валидатора QR-кодов и интеграция с базой
- [ ] Обновить QRValidator для строгой валидации формата → `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- [ ] Обновить QRScannerScreen для обработки ошибок и повторного сканирования → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- [ ] Проверить и обновить QrCodeDao для работы с objectId → `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
- [ ] Проверить и обновить QrCodeMapping → `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`

## Этап 2: Обновление системы обнаружения root и управление доступом
- [ ] Обновить RootDetector для надежного обнаружения root → `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- [ ] Добавить диалог предупреждения при обнаружении root → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- [ ] Добавить логику ограничения доступа в ProfileViewModel → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`

## Этап 3: Управление сроком действия пропуска и EncryptedSharedPreferences
- [ ] Создать EncryptedStorage для работы с EncryptedSharedPreferences → `core-security/src/main/java/com/safeplant/core/security/EncryptedStorage.kt`
- [ ] Обновить AccessPassDao для работы с EncryptedSharedPreferences → `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- [ ] Обновить ProfileViewModel для проверки срока действия пропуска → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Добавить проверку доступа в MapScreen → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`

## Этап 4: Тестирование и проверка
- [ ] Написать тесты для QRValidator → `tests/unit/test_qr_validator.kt`
- [ ] Написать тесты для RootDetector → `tests/unit/test_root_detector.kt`
- [ ] Написать тесты для EncryptedStorage → `tests/unit/test_encrypted_storage.kt`
- [ ] Написать интеграционные тесты для управления доступом → `tests/integration/test_access_control.kt`