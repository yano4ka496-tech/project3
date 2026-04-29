# Tasks: Настройка Room базы данных с шифрованием

## Stage 1: Настройка сущностей и DAO базы данных
- [ ] Создать/обновить сущность AccessPass → `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- [ ] Создать/обновить сущность QuizResult → `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt`
- [ ] Создать/обновить сущность TrainingVideo → `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt`
- [ ] Создать/обновить сущность QrCodeMapping → `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- [ ] Создать/обновить DAO AccessPassDao → `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- [ ] Создать/обновить DAO QuizResultDao → `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt`
- [ ] Создать/обновить DAO TrainingVideoDao → `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt`
- [ ] Создать/обновить DAO QrCodeDao → `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`

## Stage 2: Настройка базы данных и шифрования
- [ ] Обновить AppDatabase → `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- [ ] Обновить DatabaseKeyManager → `core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt`
- [ ] Обновить Converters → `core-database/src/main/java/com/safeplant/core/database/Converters.kt`

## Stage 3: Скрипты миграции
- [ ] Обновить Migration1To2 → `core-database/src/main/java/com/safeplant/core/database/migration/Migration1To2.kt`
- [ ] Обновить Migration2To3 → `core-database/src/main/java/com/safeplant/core/database/migration/Migration2To3.kt`
- [ ] Обновить Migration3To4 → `core-database/src/main/java/com/safeplant/core/database/migration/Migration3To4.kt`
- [ ] Обновить Migration4To5 → `core-database/src/main/java/com/safeplant/core/database/migration/Migration4To5.kt`

## Stage 4: Тестирование
- [ ] Написать unit тесты для DAO → `core-database/src/test/java/com/safeplant/core/database/dao/AccessPassDaoTest.kt`
- [ ] Написать unit тесты для DAO → `core-database/src/test/java/com/safeplant/core/database/dao/QuizResultDaoTest.kt`
- [ ] Написать unit тесты для DAO → `core-database/src/test/java/com/safeplant/core/database/dao/TrainingVideoDaoTest.kt`
- [ ] Написать unit тесты для DAO → `core-database/src/test/java/com/safeplant/core/database/dao/QrCodeDaoTest.kt`
- [ ] Написать интеграционные тесты для Room → `core-database/src/test/java/com/safeplant/core/database/EncryptedDatabaseTest.kt`
- [ ] Написать тесты для миграций → `core-database/src/test/java/com/safeplant/core/database/migration/MigrationTest.kt`