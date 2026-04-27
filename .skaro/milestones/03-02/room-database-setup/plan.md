# План реализации: room-database-setup

## Stage 1: Создание сущностей и DAO
**Цель:** Реализовать основные сущности базы данных и интерфейсы DAO согласно требованиям
**Зависит от:** отсутствуют
**Входные данные:** Требования к базе данных, существующие файлы в core-database
**Выходные файлы:**
- `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt` (обновлён)
**Критерии завершения:**
- [ ] Все сущности созданы с необходимыми полями и аннотациями
- [ ] Все DAO реализованы с требуемыми методами
- [ ] AppDatabase обновлён для включения новых сущностей и миграций
- [ ] Версия базы данных установлена на 3
**Риски:** Несоответствие структуры сущностей требованиям, ошибки в аннотациях Room

## Stage 2: Интеграция шифрования с Room
**Цель:** Настроить шифрование базы данных через SQLCipher с использованием DatabaseKeyManager
**Зависит от:** Stage 1
**Входные данные:** DatabaseKeyManager, AppDatabase
**Выходные файлы:**
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt` (обновлён)
- `core-database/src/main/java/com/safeplant/core/database/EncryptedDatabaseFactory.kt` (новый)
**Критерии завершения:**
- [ ] DatabaseKeyManager интегрирован с Room через SupportFactory
- [ ] Реализована обработка потери ключа шифрования
- [ ] База данных создаётся с шифрованием при первом запуске
- [ ] Повторные запуски используют сохранённый ключ
**Риски:** Проблемы с совместимостью SQLCipher и Room, ошибки при генерации ключа

## Stage 3: Обработка бизнес-логики
**Цель:** Реализовать бизнес-логику для работы с допусами и результатами квиза
**Зависит от:** Stage 1, Stage 2
**Входные данные:** Сущности, DAO, AppDatabase
**Выходные файлы:**
- `core-database/src/main/java/com/safeplant/core/database/repository/AccessPassRepository.kt`
- `core-database/src/main/java/com/safeplant/core/database/repository/QuizResultRepository.kt`
- `core-database/src/main/java/com/safeplant/core/database/util/VersionResetHelper.kt`
- `core-database/src/main/java/com/safeplant/core/database/util/QuizValidator.kt`
**Критерии завершения:**
- [ ] Реализована логика обновления допуска при повторном прохождении квиза
- [ ] Реализована проверка срока действия допуска
- [ ] Реализована логика сброса допусков при обновлении версии приложения
- [ ] Реализована валидация QR-кодов и проверка objectId
**Риски:** Логические ошибки в бизнес-правилах, некорректная обработка крайних случаев

## Stage 4: Обновление миграций
**Цель:** Обновить существующие миграции и добавить новую для сброса допусков
**Зависит от:** Stage 1
**Входные данные:** Текущие миграции, требования к миграциям
**Выходные файлы:**
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration2To3.kt` (обновлён)
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration3To4.kt` (новый)
**Критерии завершения:**
- [ ] Migration2To3 обновлён для создания правильных таблиц без quiz_questions
- [ ] Migration3To4 добавлен для сброса допусков при изменении версии приложения
- [ ] Все миграции корректно обрабатывают данные
- [ ] При миграциях допуски аннулируются
**Риски:** Ошибки в SQL-запросах миграций, потеря данных при миграции

## Stage 5: Тестирование и верификация
**Цель:** Написать тесты для проверки работы базы данных и бизнес-логики
**Зависит от:** Stage 1, Stage 2, Stage 3, Stage 4
**Входные данные:** Реализованные компоненты базы данных
**Выходные файлы:**
- `core-database/src/test/java/com/safeplant/core/database/dao/AccessPassDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/dao/QuizResultDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/dao/QrCodeDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/repository/AccessPassRepositoryTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/repository/QuizResultRepositoryTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/util/VersionResetHelperTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/EncryptedDatabaseTest.kt`
**Критерии завершения:**
- [ ] Написаны unit-тесты для всех DAO
- [ ] Написаны unit-тесты для репозиториев
- [ ] Написаны интеграционные тесты для Room с inMemoryDatabaseBuilder
- [ ] Покрытие бизнес-логики не менее 70%
- [ ] Все тесты проходят успешно
**Риски:** Недостаточное покрытие тестами, ошибки в тестах

---

## Verify
```yaml
- name: Unit-тесты для DAO
  command: ./gradlew :core-database:test --tests "*DaoTest"
- name: Unit-тесты для репозиториев
  command: ./gradlew :core-database:test --tests "*RepositoryTest"
- name: Интеграционные тесты для Room
  command: ./gradlew :core-database:test --tests "*EncryptedDatabaseTest"
- name: Проверка покрытия тестами
  command: ./gradlew :core-database:jacocoTestReport
- name: Проверка сборки проекта
  command: ./gradlew :core-database:build
- name: Проверка линтера
  command: ./gradlew :core-database:detekt
```

---

# Задачи: room-database-setup

## Stage 1: Создание сущностей и DAO
- [ ] Создать сущность AccessPass → `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- [ ] Создать сущность QuizResult → `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt`
- [ ] Создать сущность TrainingVideo → `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt`
- [ ] Создать сущность QrCodeMapping → `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- [ ] Создать интерфейс AccessPassDao → `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- [ ] Создать интерфейс QuizResultDao → `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt`
- [ ] Создать интерфейс TrainingVideoDao → `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt`
- [ ] Создать интерфейс QrCodeDao → `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
- [ ] Обновить AppDatabase для включения новых сущностей → `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`

## Stage 2: Интеграция шифрования с Room
- [ ] Создать EncryptedDatabaseFactory → `core-database/src/main/java/com/safeplant/core/database/EncryptedDatabaseFactory.kt`
- [ ] Обновить AppDatabase для использования DatabaseKeyManager → `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- [ ] Реализовать обработку потери ключа шифрования → `core-database/src/main/java/com/safeplant/core/database/EncryptedDatabaseFactory.kt`

## Stage 3: Обработка бизнес-логики
- [ ] Создать AccessPassRepository → `core-database/src/main/java/com/safeplant/core/database/repository/AccessPassRepository.kt`
- [ ] Создать QuizResultRepository → `core-database/src/main/java/com/safeplant/core/database/repository/QuizResultRepository.kt`
- [ ] Создать VersionResetHelper → `core-database/src/main/java/com/safeplant/core/database/util/VersionResetHelper.kt`
- [ ] Создать QuizValidator → `core-database/src/main/java/com/safeplant/core/database/util/QuizValidator.kt`
- [ ] Реализовать логику обновления допуска при повторном прохождении квиза → `AccessPassRepository.kt`
- [ ] Реализовать проверку срока действия допуска → `AccessPassRepository.kt`
- [ ] Реализовать сброс допусков при обновлении версии → `VersionResetHelper.kt`
- [ ] Реализовать валидацию QR-кодов → `QuizValidator.kt`

## Stage 4: Обновление миграций
- [ ] Обновить Migration2To3 для создания правильных таблиц → `core-database/src/main/java/com/safeplant/core/database/migration/Migration2To3.kt`
- [ ] Создать Migration3To4 для сброса допусков → `core-database/src/main/java/com/safeplant/core/database/migration/Migration3To4.kt`

## Stage 5: Тестирование и верификация
- [ ] Создать AccessPassDaoTest → `core-database/src/test/java/com/safeplant/core/database/dao/AccessPassDaoTest.kt`
- [ ] Создать QuizResultDaoTest → `core-database/src/test/java/com/safeplant/core/database/dao/QuizResultDaoTest.kt`
- [ ] Создать QrCodeDaoTest → `core-database/src/test/java/com/safeplant/core/database/dao/QrCodeDaoTest.kt`
- [ ] Создать AccessPassRepositoryTest → `core-database/src/test/java/com/safeplant/core/database/repository/AccessPassRepositoryTest.kt`
- [ ] Создать QuizResultRepositoryTest → `core-database/src/test/java/com/safeplant/core/database/repository/QuizResultRepositoryTest.kt`
- [ ] Создать VersionResetHelperTest → `core-database/src/test/java/com/safeplant/core/database/util/VersionResetHelperTest.kt`
- [ ] Создать EncryptedDatabaseTest → `core-database/src/test/java/com/safeplant/core/database/EncryptedDatabaseTest.kt`