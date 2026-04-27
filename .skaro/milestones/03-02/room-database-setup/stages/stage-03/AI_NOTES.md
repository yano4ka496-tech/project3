# AI_NOTES — Stage 3: Обработка бизнес-логики

## Что было сделано
- Реализован репозиторий AccessPassRepository для управления цифровыми допусками
- Реализован репозиторий QuizResultRepository для работы с результатами квиза
- Создан VersionResetHelper для сброса допусков при обновлении версии приложения
- Разработан QuizValidator для валидации данных квиза и QR-кодов

## Почему такой подход
- Использованы репозитории для инкапсуляции бизнес-логики и отделения ее от DAO
- VersionResetHelper интегрирован с системными API для проверки версии приложения
- QuizValidator обеспечивает централизованную валидацию данных с проверкой целостности файлов
- Все операции выполняются в suspend-функциях для корректной работы с корутинами

## Файлы созданы / изменены
| Файл | Действие | Описание |
|---|---|---|
| `core-database/src/main/java/com/safeplant/core/database/repository/AccessPassRepository.kt` | создан | Репозиторий для работы с цифровыми допусками |
| `core-database/src/main/java/com/safeplant/core/database/repository/QuizResultRepository.kt` | создан | Репозиторий для работы с результатами квиза |
| `core-database/src/main/java/com/safeplant/core/database/util/VersionResetHelper.kt` | создан | Утилита для сброса допусков при обновлении версии |
| `core-database/src/main/java/com/safeplant/core/database/util/QuizValidator.kt` | создан | Утилита для валидации данных квиза и QR-кодов |

## Риски и ограничения
- В VersionResetHelper используется простое SharedPreferences вместо EncryptedSharedPreferences для хранения версии приложения
- В QuizValidator проверка наличия маршрута для objectId является заглушкой
- Нет обработки ошибок при работе с файлами в QuizValidator
- В AccessPassRepository нет проверки на максимальное количество допусков для пользователя

## Соответствие инвариантам
- [ ] Максимальная длина функции — соблюдена (все функции короче 40 строк)
- [ ] Максимальная глубина вложенности — соблюдена (максимальная глубина 2 уровня)
- [ ] Стандарт именования — соблюден (классы PascalCase, функции camelCase)
- [ ] Отсутствие дублирования кода — соблюдено (используются общие DAO)
- [ ] Каждый экран/вьюмодель — MVVM/MVI — не применимо (репозитории не являются экранами)

## Как проверить
1. Проверить сборку проекта: `./gradlew :core-database:build`
2. Запустить unit-тесты: `./gradlew :core-database:test`
3. Проверить покрытие тестами: `./gradlew :core-database:jacocoTestReport`
4. Проверить линтер: `./gradlew :core-database:detekt`