## plan.md

## Stage 1: Настройка CI/CD Workflow
**Goal:** Создать GitHub Actions workflow для автоматизации сборки, тестирования и проверки качества кода.
**Depends on:** none
**Inputs:** Конституция проекта, архитектура, существующие build.gradle.kts файлы
**Outputs:** 
- `.github/workflows/ci.yml`
- `scripts/check_pdf_conversion.sh`
- `scripts/validate_qr_format.sh`
- `scripts/check_root_detection.sh`
- `scripts/check_version_reset.sh`
- `scripts/check_quiz_selection.sh`
- `scripts/check_database_encryption.sh`
**DoD:**
- [ ] Workflow создан с шагами сборки, тестирования, линтинга и проверки покрытия
- [ ] Реализованы все проверки из спецификации (QR, root, версия, квиз, шифрование)
- [ ] Добавлен запуск скрипта конвертации PDF→MBTiles при изменении PDF
- [ ] Настроен отчет о покрытии тестами (HTML и XML форматы)
**Risks:** Проблемы с совместимостью версий Gradle, ошибки в скриптах проверки

## Stage 2: Реализация тестов для CI валидации
**Goal:** Реализовать unit-тесты для проверки механизмов, валидируемых в CI.
**Depends on:** Stage 1
**Inputs:** Существующие репозитории и сущности базы данных
**Outputs:**
- `feature-qr/src/test/java/com/safeplant/feature/qr/QRValidatorTest.kt`
- `core-security/src/test/java/com/safeplant/core/security/RootDetectorTest.kt`
- `feature-profile/src/test/java/com/safeplant/feature/profile/VersionResetTest.kt`
- `feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizSelectionTest.kt`
- `core-database/src/test/java/com/safeplant/core/database/DatabaseEncryptionTest.kt`
**DoD:**
- [ ] Реализован тест валидации QR-кодов формата 'номер_объекта|название'
- [ ] Реализован тест проверки механизма обнаружения root-доступа
- [ ] Реализован тест сброса допуска при обновлении версии
- [ ] Реализован тест выбора 10 случайных уникальных вопросов
- [ ] Реализован тест шифрования Room БД с SQLCipher и Android Keystore
**Risks:** Низкое покрытие тестами, сложность мокирования зависимостей

## Stage 3: Интеграция и валидация CI
**Goal:** Полная интеграция всех компонентов CI и валидация работы.
**Depends on:** Stage 1, Stage 2
**Inputs:** Все созданные файлы и скрипты
**Outputs:** 
- Обновленный `.github/workflows/ci.yml` с интеграцией всех проверок
- Тестовые данные для валидации (примеры QR-кодов, PDF-файлы)
**DoD:**
- [ ] Workflow успешно проходит все этапы на тестовых данных
- [ ] Отчеты о покрытии доступны в артефактах (HTML и XML)
- [ ] Все тесты проходят в CI
- [ ] Скрипт конвертации запускается при изменении PDF
- [ ] Проверена валидация QR-кодов формата 'номер_объекта|название'
- [ ] Проверен механизм обнаружения root-доступа
- [ ] Проверен механизм сброса допуска при обновлении версии
- [ ] Проверен алгоритм выбора 10 случайных уникальных вопросов
- [ ] Проверен механизм шифрования Room БД
**Risks:** Проблемы с таймингами в CI, ошибки в интеграции компонентов

---

## Verify
```yaml
- command: Проверка сборки APK
  command: ./gradlew assembleDebug
- command: Запуск unit-тестов
  command: ./gradlew testDebugUnitTest --tests "*Test"
- command: Проверка покрытия тестами
  command: ./gradlew jacocoTestReport
- command: Запуск Detekt
  command: ./gradlew detekt
- command: Запуск ktlint
  command: ./gradlew ktlintCheck
- command: Проверка QR валидации
  command: ./scripts/validate_qr_format.sh
- command: Проверка root-детекции
  command: ./scripts/check_root_detection.sh
- command: Проверка сброса версии
  command: ./scripts/check_version_reset.sh
- command: Проверка выбора вопросов
  command: ./scripts/check_quiz_selection.sh
- command: Проверка шифрования БД
  command: ./scripts/check_database_encryption.sh
- command: Проверка конвертации PDF
  command: ./scripts/check_pdf_conversion.sh
```

---

## tasks.md

# Задачи: Настройка CI/CD

## Stage 1: Настройка CI/CD Workflow
- [ ] Создать директорию `.github/workflows/`
- [ ] Создать файл `.github/workflows/ci.yml` с базовой структурой workflow
- [ ] Реализовать шаг сборки APK/AAB
- [ ] Реализовать запуск unit-тестов и интеграционных тестов
- [ ] Реализовать запуск Detekt и ktlint
- [ ] Реализовать генерацию отчета о покрытии тестами
- [ ] Реализовать проверку изменений PDF и запуск конвертации
- [ ] Реализовать все проверки из спецификации (QR, root, версия, квиз, шифрование)
- [ ] Настроить артефакты отчетов (HTML и XML)

## Stage 2: Реализация тестов для CI валидации
- [ ] Реализовать `QRValidatorTest.kt` для проверки формата 'номер_объекта|название'
- [ ] Реализовать `RootDetectorTest.kt` для проверки механизма обнаружения root
- [ ] Реализовать `VersionResetTest.kt` для проверки сброса допуска при обновлении
- [ ] Реализовать `QuizSelectionTest.kt` для проверки выбора 10 случайных уникальных вопросов
- [ ] Реализовать `DatabaseEncryptionTest.kt` для проверки шифрования Room БД
- [ ] Создать тестовые данные для всех проверок
- [ ] Обеспечить покрытие бизнес-логики не менее 70%

## Stage 3: Интеграция и валидация CI
- [ ] Интегрировать все проверки в основной workflow
- [ ] Создать тестовые PDF-файлы для проверки конвертации
- [ ] Настроить триггеры для workflow (push, pull request)
- [ ] Проверить работу всех скриптов проверки
- [ ] Проверить генерацию отчетов о покрытии
- [ ] Проверить работу всех тестов в CI окружении
- [ ] Документировать процесс CI/CD для команды разработки