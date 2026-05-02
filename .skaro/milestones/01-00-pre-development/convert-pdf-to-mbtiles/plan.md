# План реализации: Конвертация PDF в MBTiles и интеграция с приложением SafePlant

## Stage 1: Настройка структуры проекта и базовых компонентов
**Цель:** Создать структуру проекта и базовые компоненты согласно архитектуре
**Зависит от:** отсутствуют
**Входные данные:** Архитектурный документ, Конституция
**Выходные данные:** 
- `app/src/main/java/com/safeplant/MainActivity.kt`
- `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- `core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- `scripts/convert_pdf_to_mbtiles.py`
- `scripts/validate_mbtiles.py`
- `scripts/check_pdf_conversion.sh`
- `scripts/run_all_tests.sh`
- `scripts/run_final_checks.sh`
- `AI_NOTES.md`
- `tests/integration/MainActivityTest.kt`

**Критерии завершения:**
- [ ] Структура каталогов соответствует архитектуре
- [ ] Точка входа MainActivity создана
- [ ] Навигационный граф настроен
- [ ] Базовые компоненты безопасности и базы данных созданы
- [ ] Скрипты конвертации PDF в MBTiles готовы
- [ ] Скрипты тестирования и проверки готовы

**Риски:** Отсутствуют

## Stage 2: Реализация навигации и интеграция QR-сканера
**Цель:** Реализовать навигацию между экранами и интегрировать QR-сканер
**Зависит от:** Stage 1
**Входные данные:** Базовая структура проекта
**Выходные данные:**
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt`
- `feature-training/src/main/java/com/safeplant/feature/training/TrainingScreen.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt`

**Критерии завершения:**
- [ ] Навигация между всеми экранами работает корректно
- [ ] QR-сканер интегрирован с навигацией
- [ ] Карта отображает QR-позиционирование
- [ ] Обработка ошибок QR-сканирования реализована
- [ ] Проверка root-доступа интегрирована

**Риски:** Проблемы с интеграцией MapLibre GL с Compose

## Stage 3: Реализация квиза и системы допусков
**Цель:** Реализовать систему квиза и цифровых допусков
**Зависит от:** Stage 1, Stage 2
**Входные данные:** База данных, навигация
**Выходные данные:**
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- `app/src/main/assets/quiz/questions.json`

**Критерии завершения:**
- [ ] Система квиза работает корректно
- [ ] Цифровые допуски сохраняются в зашифрованной базе
- [ ] Проверка срока действия допуска реализована
- [ ] Сброс допуска при обновлении версии работает
- [ ] Вопросы для квиза загружены из assets

**Риски:** Проблемы с шифрованием базы данных

## Stage 4: Тестирование и валидация
**Цель:** Реализовать тесты и валидацию функциональности
**Зависит от:** Stage 1, Stage 2, Stage 3
**Входные данные:** Все компоненты приложения
**Выходные данные:**
- `tests/database/test_qr_positioning.kt`
- `tests/integration/test_full_flow.kt`
- `tests/data/pdf/test.pdf`
- `tests/data/qr/valid_qr.txt`
- `tests/data/qr/invalid_qr.txt`

**Критерии завершения:**
- [ ] Unit-тесты для бизнес-логики готовы
- [ ] Интеграционные тесты для основных потоков готовы
- [ ] Тестовые данные для QR и PDF готовы
- [ ] Скрипты проверки работают корректно

**Риски:** Проблемы с запуском тестов на CI

## Stage 5: Финальная интеграция и документация
**Цель:** Завершить интеграцию компонентов и создать документацию
**Зависит от:** Stage 1, Stage 2, Stage 3, Stage 4
**Входные данные:** Все компоненты приложения, тесты
**Выходные данные:**
- `AI_NOTES.md` (обновлено)
- `plan.md` (финальная версия)
- `tasks.md` (финальная версия)

**Критерии завершения:**
- [ ] Все компоненты интегрированы и работают вместе
- [ ] Документация обновлена
- [ ] Скрипты автоматизации готовы
- [ ] Проверка соответствия требованиям завершена

**Риски:** Проблемы с совместимостью компонентов

---

## Verify
- name: Проверка конвертации PDF в MBTiles
  command: python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf tests/data/mbtiles/test.mbtiles
- name: Валидация MBTiles файла
  command: python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles
- name: Проверка зависимостей для конвертации
  command: bash scripts/check_pdf_conversion.sh
- name: Unit-тесты для QR-позиционирования
  command: ./gradlew test --tests *QrCodeDaoTest
- name: Тесты шифрования базы данных
  command: ./gradlew test --tests *DatabaseEncryptionTest
- name: Интеграционные тесты
  command: ./gradlew connectedAndroidTest
- name: Тесты сканирования QR-кодов
  command: ./gradlew test --tests *QrCodeScanTest
- name: Тесты обновления приложения
  command: ./gradlew test --tests *AppUpdateTest
- name: Тесты обнаружения root-доступа
  command: ./gradlew test --tests *RootDetectionTest
- name: Тесты доступа к квизу
  command: ./gradlew test --tests *QuizAccessTest
- name: Проверка линтинга
  command: ./gradlew ktlintCheck
- name: Проверка детекта
  command: ./gradlew detekt
- name: Запуск всех тестов
  command: bash scripts/run_all_tests.sh
- name: Финальная проверка
  command: bash scripts/run_final_checks.sh

---

# Задачи: Конвертация PDF в MBTiles и интеграция с приложением SafePlant

## Stage 1: Настройка структуры проекта и базовых компонентов
- [ ] Создать структуру каталогов → `app/`, `core-navigation/`, `core-database/`, `core-security/`, `core-mapping/`, `feature-qr/`, `feature-map/`, `feature-quiz/`, `feature-training/`, `feature-profile/`, `tests/`
- [ ] Создать точку входа → `app/src/main/java/com/safeplant/MainActivity.kt`
- [ ] Реализовать навигационный граф → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- [ ] Определить навигационные назначения → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- [ ] Реализовать детектор root → `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- [ ] Создать базу данных → `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- [ ] Реализовать менеджер ключей базы данных → `core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt`
- [ ] Создать DAO для QR-кодов → `core-database/src/main/java/com/safeplant/core/database/dao/QrCodeDao.kt`
- [ ] Создать сущность QR-сопоставления → `core-database/src/main/java/com/safeplant/core/database/entity/QrCodeMapping.kt`
- [ ] Реализовать валидатор QR-кодов → `feature-qr/src/main/java/com/safeplant/feature/qr/QRValidator.kt`
- [ ] Создать скрипт конвертации PDF в MBTiles → `scripts/convert_pdf_to_mbtiles.py`
- [ ] Создать скрипт валидации MBTiles → `scripts/validate_mbtiles.py`
- [ ] Создать скрипт проверки конвертации PDF → `scripts/check_pdf_conversion.sh`
- [ ] Создать скрипт запуска всех тестов → `scripts/run_all_tests.sh`
- [ ] Создать скрипт финальной проверки → `scripts/run_final_checks.sh`
- [ ] Создать AI_NOTES.md → `AI_NOTES.md`
- [ ] Создать интеграционный тест для MainActivity → `tests/integration/MainActivityTest.kt`

## Stage 2: Реализация навигации и интеграция QR-сканера
- [ ] Реализовать экран QR-сканера → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- [ ] Реализовать экран карты → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- [ ] Реализовать экран квиза → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt`
- [ ] Реализовать экран обучения → `feature-training/src/main/java/com/safeplant/feature/training/TrainingScreen.kt`
- [ ] Реализовать экран профиля → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- [ ] Реализовать рендерер карты → `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt`

## Stage 3: Реализация квиза и системы допусков
- [ ] Создать DAO для допусков → `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- [ ] Создать сущность допуска → `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- [ ] Реализовать ViewModel квиза → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt`
- [ ] Реализовать ViewModel профиля → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Создать файл с вопросами квиза → `app/src/main/assets/quiz/questions.json`

## Stage 4: Тестирование и валидация
- [ ] Создать тест для QR-позиционирования → `tests/database/test_qr_positioning.kt`
- [ ] Создать интеграционный тест для полного потока → `tests/integration/test_full_flow.kt`
- [ ] Создать тестовый PDF файл → `tests/data/pdf/test.pdf`
- [ ] Создать валидный QR-код → `tests/data/qr/valid_qr.txt`
- [ ] Создать невалидный QR-код → `tests/data/qr/invalid_qr.txt`

## Stage 5: Финальная интеграция и документация
- [ ] Обновить AI_NOTES.md → `AI_NOTES.md`
- [ ] Создать финальный план → `plan.md`
- [ ] Создать финальный список задач → `tasks.md`