# AI_NOTES — Stage 2: Настройка ядра проекта (база данных, безопасность, хранилище)

## Что было сделано
- Реализована база данных с шифрованием через Android Keystore в модуле core-database
- Созданы все необходимые сущности (AccessPass, MapObject, QuizResult, TrainingVideo) и DAO интерфейсы
- Реализована миграция базы данных (Migration1To2)
- Добавлен DatabaseModule для интеграции с Hilt
- Реализован детектор root-доступа (RootDetector) с дополнительными проверками
- Создан валидатор QR-кодов (QRValidator) с проверкой формата и парсингом
- Реализован менеджер ресурсов (AssetManager) для работы с файлами assets
- Создан VideoCopier для копирования видео из assets во внутреннее хранилище

## Почему такой подход
- База данных шифруется через Android Keystore для обеспечения безопасности данных
- Использование Room с автоматическими миграциями упрощает обновление схемы
- DAO интерфейсы предоставляют удобный доступ к данным с поддержкой Flow для реактивности
- RootDetector использует несколько методов проверки для повышения надежности
- QRValidator строго проверяет формат QR-кода согласно требованиям
- AssetManager и VideoCopier обеспечивают работу с ресурсами в офлайн-режиме

## Файлы создано / изменено
| Файл | Действие | Описание |
|---|---|---|
| `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt` | создан | Основная база данных с шифрованием |
| `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt` | создан | DAO для работы с пропусками доступа |
| `core-database/src/main/java/com/safeplant/core/database/dao/MapObjectDao.kt` | создан | DAO для работы с объектами на карте |
| `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt` | создан | DAO для работы с результатами квиза |
| `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt` | создан | DAO для работы с обучающими видео |
| `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt` | создан | Сущность пропуска доступа |
| `core-database/src/main/java/com/safeplant/core/database/entity/MapObject.kt` | создан | Сущность объекта на карте |
| `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt` | создан | Сущность результата квиза |
| `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt` | создан | Сущность обучающего видео |
| `core-database/src/main/java/com/safeplant/core/database/migration/Migration1To2.kt` | создан | Миграция базы данных |
| `core-database/src/main/java/com/safeplant/core/database/Converters.kt` | создан | Конвертеры для Room |
| `core-database/src/main/java/com/safeplant/core/database/di/DatabaseModule.kt` | создан | Модуль Hilt для базы данных |
| `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt` | создан | Детектор root-доступа |
| `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt` | создан | Валидатор QR-кодов |
| `core-storage/src/main/java/com/safeplant/core/storage/AssetManager.kt` | создан | Менеджер для работы с assets |
| `core-storage/src/main/java/com/safeplant/core/storage/VideoCopier.kt` | создан | Копировщик видео из assets |

## Риски и ограничения
- Шифрование базы данных может вызывать проблемы на некоторых устройствах с устаревшей версией Android
- RootDetector может не обнаружить все методы получения root-доступа
- VideoCopier может не корректно работать на устройствах с ограниченным внутренним хранилищем
- Копирование больших файлов (например, видео) может блокировать основной поток

## Соответствие инвариантам
- [ ] База данных с шифрованием через Android Keystore — соблюдено
- [ ] Сущности и DAO для всех таблиц — соблюдено
- [ ] Миграции базы данных — соблюдено
- [ ] Детектор root-доступа — соблюдено
- [ ] Валидатор QR-кодов — соблюдено
- [ ] Менеджер ресурсов для копирования assets — соблюдено

## Как проверить
1. Проверить сборку проекта: `./gradlew build`
2. Проверить работу базы данных: создать тест, который проверяет создание и чтение записей
3. Проверить детектор root: запустить на устройстве с root и без
4. Проверить валидатор QR: передать корректные и некорректные QR-коды
5. Проверить копирование видео: проверить наличие файлов во внутреннем хранилище после копирования