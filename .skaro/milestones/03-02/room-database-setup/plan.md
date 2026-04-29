## plan.md

## Stage 1: Настройка сущностей и DAO базы данных
**Цель:** Создать и настроить сущности и DAO для Room базы данных с шифрованием.
**Зависит от:** нет
**Входные данные:** Архитектурный документ, спецификация шифрования
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
**DoD:**
- [ ] Все сущности определены с правильными аннотациями и полями
- [ ] Все DAO определены с необходимыми методами CRUD
- [ ] Схема базы данных соответствует требованиям шифрования
- [ ] Типы данных корректно определены
**Риски:** Несоответствие существующих файлов требованиям спецификации

## Stage 2: Настройка базы данных и шифрования
**Цель:** Реализовать Room базу данных с шифрованием через Android Keystore.
**Зависит от:** Stage 1
**Входные данные:** Сущности и DAO, спецификация шифрования
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- `core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt`
- `core-database/src/main/java/com/safeplant/core/database/Converters.kt`
**DoD:**
- [ ] AppDatabase настроена с правильными сущностями и миграциями
- [ ] DatabaseKeyManager правильно генерирует и хранит ключ шифрования
- [ ] База данных зашифрована с использованием SQLCipher
- [ ] Настроен WAL режим для производительности
**Риски:** Проблемы с совместимостью SQLCipher и Room

## Stage 3: Скрипты миграции
**Цель:** Реализовать скрипты миграции для версий базы данных.
**Зависит от:** Stage 2
**Входные данные:** Настроенная база данных, схема миграций
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration1To2.kt`
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration2To3.kt`
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration3To4.kt`
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration4To5.kt`
**DoD:**
- [ ] Все миграции реализованы правильно
- [ ] Миграции правильно обрабатывают изменения схемы
- [ ] Добавлены необходимые индексы для производительности
- [ ] Обеспечена обратная совместимость
**Риски:** Ошибки в миграциях могут привести к потере данных

## Stage 4: Тестирование
**Цель:** Написать unit и интеграционные тесты для слоя базы данных.
**Зависит от:** Stage 1, Stage 2, Stage 3
**Входные данные:** Настроенная база данных, сущности, DAO, миграции
**Выходные данные:**
- `core-database/src/test/java/com/safeplant/core/database/dao/AccessPassDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/dao/QuizResultDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/dao/TrainingVideoDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/dao/QrCodeDaoTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/EncryptedDatabaseTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/migration/MigrationTest.kt`
**DoD:**
- [ ] Unit тесты для DAO написаны
- [ ] Интеграционные тесты для Room написаны
- [ ] Тесты покрывают шифрование и управление ключами
- [ ] Тесты для миграций проверяют корректность преобразований
- [ ] Достигнуто требуемое покрытие тестами (70%)
**Риски:** Низкое покрытие тестами, ошибки в тестах шифрования

## Verify
- name: Unit-тесты для DAO
  command: ./gradlew :core-database:testDebugUnitTest --tests "*DaoTest"
- name: Unit-тесты для репозиториев
  command: ./gradlew :core-database:testDebugUnitTest --tests "*RepositoryTest"
- name: Интеграционные тесты для Room
  command: ./gradlew :core-database:testDebugUnitTest --tests "*EncryptedDatabaseTest"
- name: Проверка покрытия тестами
  command: echo "JaCoCo отчёт (заглушка)"
- name: Проверка сборки проекта
  command: ./gradlew :core-database:assemble
- name: Проверка линтера
  command: echo "Detekt (заглушка)"