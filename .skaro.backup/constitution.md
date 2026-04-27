# Constitution: SafePlant

## Stack
- Language: Kotlin 1.9+
- Framework: Android SDK (min API 29 / Android 10+), Jetpack Compose (рекомендовано) или XML Views
- Database: Room 2.8+ (SQLite)
- Infrastructure: отсутствует (fully offline‑first, без серверной части, без Docker/K8s/cloud)

## Coding Standards
- Linter: Detekt с конфигурацией по умолчанию + правила для Android (конфиг будет уточнён позднее)
- Formatter: ktlint 1.8+
- Naming: Классы PascalCase, функции/свойства camelCase, константы UPPER_SNAKE_CASE, XML id snake_case, пакеты строчные без подчёркиваний
- Max function length: 40 строк (без пустых строк и комментариев)
- Max nesting depth: 4 уровня
- Каждый экран / вьюмодель — MVVM или MVI

## Testing
- Minimum coverage: 70% для бизнес-логики (ViewModel, use cases, репозитории)
- Required: unit tests для расчёта срока допуска, логики квиза (подсчёт баллов, случайная выборка), парсинга QR-кода
- Required: integration tests для Room (миграции, запросы) с inMemoryDatabaseBuilder
- Framework: JUnit5 (с плагином de.mannodermaus.android-junit5), MockK 1.14+, Robolectric (для Android-зависимых тестов), Espresso (для UI-тестов критических путей)
- Дополнительно: бизнес-логика выносится в platform-agnostic use cases (чистый Kotlin) для упрощения тестирования

## Constraints
- Приложение НЕ выполняет сетевых запросов (запрещены Retrofit, OkHttp с доступом в интернет)
- Обновление данных – только через полную замену APK (Google Play или side-load)
- Общий размер установленных данных (карты, видео, база вопросов) – не более 2 ГБ
- Расчёт маршрутов – только по предустановленным явным путям (без алгоритмов A* / Dijkstra)
- Приложение работает в режиме «в самолёте» (Airplane mode) без сбоев
- Видео воспроизводятся через ExoPlayer в offline-режиме (без стриминга)

## Security
- Authorization: отсутствует (приложение не требует входа; цифровой допуск – локальный флаг с датой истечения)
- Input validation: проверка формата QR-кода (ожидается `номер_объекта|название`), защита от некорректных символов
- Secrets: нет сетевых ключей/токенов. Для шифрования локального хранилища – Android Keystore
- Защита от подделки допуска: хранить данные в зашифрованной Room БД (с использованием Android Keystore) или EncryptedSharedPreferences для даты истечения
- Root detection: опционально (по требованию заказчика), не блокирует запуск, только предупреждает

## LLM Rules
- Do not leave stubs without explicit TODO with justification (каждый `TODO` содержит причину и предполагаемое решение)
- Do not duplicate code: prefer reuse and clear abstractions (выносить повторяющиеся UI-компоненты, утилиты, валидаторы)
- Do not make hidden assumptions — if unsure, ask (все неоднозначности из ТЗ выносятся в `ASSUMPTIONS.md`)
- Always generate `AI_NOTES.md` per template (шаблон в корне проекта)
- Follow the coding style described above
- Запрещено использование любых сетевых библиотек без явного разрешения в изменении конституции
- Комментарии к сложным офлайн-алгоритмам (маршруты, квиз, шифрование) обязательны