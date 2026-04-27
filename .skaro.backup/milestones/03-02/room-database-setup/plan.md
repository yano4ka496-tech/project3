## plan.md

## Stage 1: Настройка базы данных с шифрованием
**Цель:** Реализовать зашифрованную базу данных Room с использованием SQLCipher и Android Keystore.
**Зависит от:** нет
**Входные данные:** Существующие файлы AppDatabase.kt, DatabaseKeyManager.kt, и спецификация.
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt` (модифицированный)
- `core-database/src/main/java/com/safeplant/core/database/EncryptedDatabaseFactory.kt` (новый)
- `core-database/build.gradle.kts` (модифицированный для включения SQLCipher)
**DoD:**
- [ ] База данных зашифрована и может быть открыта с помощью DatabaseKeyManager
- [ ] В базе данных включены необходимые сущности и DAO
- [ ] build.gradle.kts включает зависимости SQLCipher
**Риски:** Проблемы с совместимостью SQLCipher с Room, проблемы с производительностью

## Stage 2: Загрузка данных из assets
**Цель:** Реализовать загрузку данных из CSV/JSON файлов в assets в базу данных.
**Зависит от:** Stage 1
**Входные данные:** AssetManager.kt, questions.json, и любые CSV файлы для сопоставлений QR.
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/DatabaseInitializer.kt` (новый)
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt` (модифицированный для включения инициализатора)
- Метод для загрузки данных из assets
**DoD:**
- [ ] Данные из assets загружены в базу данных
- [ ] Приложение может обрабатывать отсутствующие или поврежденные файлы assets, пропуская их
**Риски:** Ошибки парсинга данных, большие файлы могут замедлить запуск

## Stage 3: Обработка QR-кодов, управление разрешениями и логика квиза
**Цель:** Реализовать валидацию QR-кодов, управление разрешениями и логику квиза.
**Зависит от:** Stage 1, Stage 2
**Входные данные:** QRValidator.kt, QRScannerViewModel.kt, PermissionDao.kt, QuizDao.kt, и спецификация.
**Выходные данные:**
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt` (модифицированный)
- `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt` (модифицированный)
- `core-database/src/main/java/com/safeplant/core/database/dao/PermissionDao.kt` (модифицированный)
- `core-database/src/main/java/com/safeplant/core/database/dao/QuizDao.kt` (модифицированный)
- Логика перенаправления на экран квиза при отсутствии разрешения
**DoD:**
- [ ] При сканировании QR-кода, который не найден, отображается сообщение об ошибке и предложение повторить сканирование
- [ ] Разрешения сбрасываются при обновлении приложения (изменение версии)
- [ ] Истекшие разрешения помечаются как недействительные
- [ ] Реализован алгоритм Фишера-Йетса для выбора 10 случайных вопросов без повторений
- [ ] При попытке доступа к квизу без действующего разрешения пользователь перенаправляется на экран квиза
**Риски:** Сложность в управлении состоянием разрешений, ошибки в алгоритме Фишера-Йетса

## Stage 4: Тестирование
**Цель:** Написать тесты и проверить реализацию.
**Зависит от:** Stage 1, Stage 2, Stage 3
**Входные данные:** Весь реализованный код.
**Выходные данные:**
- `core-database/src/test/java/com/safeplant/core/database/PermissionDaoTest.kt` (модифицированный/новый)
- `core-database/src/test/java/com/safeplant/core/database/QRValidatorTest.kt` (модифицированный/новый)
- `core-database/src/test/java/com/safeplant/core/database/QuizDaoTest.kt` (модифицированный/новый)
- `core-database/src/test/java/com/safeplant/core/database/DatabaseInitializerTest.kt` (новый)
- Интеграционные тесты для Room с использованием inMemoryDatabaseBuilder
**DoD:**
- [ ] Unit-тесты покрывают бизнес-логику (DAO, валидация QR, алгоритм Фишера-Йетса)
- [ ] Интеграционные тесты покрывают миграции и запросы Room
- [ ] Тесты проходят с покрытием не менее 70% для бизнес-логики
**Риски:** Низкое покрытие тестами, сложность тестирования шифрования

## Verify
- name: Unit tests
  command: ./gradlew test --tests "*DaoTest" --tests "*QRValidatorTest" --tests "*QuizDaoTest" --tests "*DatabaseInitializerTest"
- name: Integration tests
  command: ./gradlew connectedAndroidTest
- name: Build
  command: ./gradlew assembleDebug
- name: Detekt
  command: ./gradlew detekt
- name: Ktlint
  command: ./gradlew ktlintCheck