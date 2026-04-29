# План реализации проекта SafePlant

## Stage 1: Структура проекта и базовая настройка
**Цель:** Создать модульную структуру проекта согласно архитектуре и настроить базовые конфигурации
**Зависимости:** нет
**Входные данные:** Архитектура проекта, Конституция
**Выходные файлы:**
- `core-navigation/build.gradle.kts`
- `core-database/build.gradle.kts`
- `core-storage/build.gradle.kts`
- `core-security/build.gradle.kts`
- `core-mapping/build.gradle.kts`
- `feature-quiz/build.gradle.kts`
- `feature-map/build.gradle.kts`
- `feature-training/build.gradle.kts`
- `feature-qr/build.gradle.kts`
- `feature-profile/build.gradle.kts`
- `settings.gradle.kts` (обновлённый)
- `app/build.gradle.kts` (обновлённый)
- `core-navigation/src/main/AndroidManifest.xml`
- `core-database/src/main/AndroidManifest.xml`
- `core-storage/src/main/AndroidManifest.xml`
- `core-security/src/main/AndroidManifest.xml`
- `core-mapping/src/main/AndroidManifest.xml`
- `feature-quiz/src/main/AndroidManifest.xml`
- `feature-map/src/main/AndroidManifest.xml`
- `feature-training/src/main/AndroidManifest.xml`
- `feature-qr/src/main/AndroidManifest.xml`
- `feature-profile/src/main/AndroidManifest.xml`
**Критерии завершения:**
- [ ] Структура модулей соответствует архитектуре
- [ ] Все модули подключены в settings.gradle.kts
- [ ] Базовые зависимости настроены для каждого модуля
- [ ] AndroidManifest.xml для каждого модуля
**Риски:** Конфликты зависимостей между модулями

## Stage 2: Настройка ядра проекта (база данных, безопасность, хранилище)
**Цель:** Реализовать базовые модули: базу данных с шифрованием, безопасность и управление ресурсами
**Зависимости:** Stage 1
**Входные данные:** Структура модулей
**Выходные файлы:**
- `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/AccessPassDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/MapObjectDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/QuizResultDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/dao/TrainingVideoDao.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/AccessPass.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/MapObject.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/QuizResult.kt`
- `core-database/src/main/java/com/safeplant/core/database/entity/TrainingVideo.kt`
- `core-database/src/main/java/com/safeplant/core/database/migration/Migration1To2.kt`
- `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt`
- `core-storage/src/main/java/com/safeplant/core/storage/AssetManager.kt`
- `core-storage/src/main/java/com/safeplant/core/storage/VideoCopier.kt`
- `core-database/src/main/java/com/safeplant/core/database/di/DatabaseModule.kt`
**Критерии завершения:**
- [ ] База данных с шифрованием через Android Keystore
- [ ] Сущности и DAO для всех таблиц
- [ ] Миграции базы данных
- [ ] Детектор root-доступа
- [ ] Валидатор QR-кодов
- [ ] Менеджер ресурсов для копирования assets
**Риски:** Проблемы с шифрованием базы данных на разных устройствах

## Stage 3: Реализация функциональных модулей
**Цель:** Создать модули для викторины, карты, обучения, сканирования QR и профиля
**Зависимости:** Stage 2
**Входные данные:** Настроенные модули ядра
**Выходные файлы:**
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt`
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt`
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt`
- `feature-quiz/src/main/java/com/safeplant/feature/quiz/di/QuizModule.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/MapRepository.kt`
- `feature-map/src/main/java/com/safeplant/feature/map/di/MapModule.kt`
- `feature-training/src/main/java/com/safeplant/feature/training/TrainingViewModel.kt`
- `feature-training/src/main/java/com/safeplant/feature/training/TrainingScreen.kt`
- `feature-training/src/main/java/com/safeplant/feature/training/TrainingRepository.kt`
- `feature-training/src/main/java/com/safeplant/feature/training/di/TrainingModule.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerRepository.kt`
- `feature-qr/src/main/java/com/safeplant/feature/qr/di/QRModule.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt`
- `feature-profile/src/main/java/com/safeplant/feature/profile/di/ProfileModule.kt`
**Критерии завершения:**
- [ ] Логика викторины с повторением до 8/10 баллов
- [ ] Отображение карты с предустановленными маршрутами
- [ ] Воспроизведение обучающих видео
- [ ] Сканирование QR-кодов с обработкой ошибок
- [ ] Профиль с цифровым пропуском
**Риски:** Сложности с интеграцией MapLibre GL Native

## Stage 4: Интеграция и навигация
**Цель:** Настроить навигацию, внедрить зависимости Hilt и реализовать проверку версии
**Зависимости:** Stage 1, Stage 2, Stage 3
**Входные данные:** Реализованные модули
**Выходные файлы:**
- `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- `app/src/main/java/com/safeplant/SafePlantApplication.kt`
- `app/src/main/java/com/safeplant/di/AppModule.kt`
- `app/src/main/java/com/safeplant/di/RepositoryModule.kt`
- `app/src/main/java/com/safeplant/MainActivity.kt` (обновлённый)
- `app/src/main/java/com/safeplant/VersionChecker.kt`
- `app/build.gradle.kts` (добавлен task для копирования assets)
**Критерии завершения:**
- [ ] Навигация между экранами через Jetpack Navigation
- [ ] Внедрение зависимостей через Hilt
- [ ] Проверка версии приложения при запуске
- [ ] Сброс цифрового пропуска при изменении версии
- [ ] Копирование assets в APK
**Риски:** Проблемы с deep links из QR-сканера

## Stage 5: Тестирование и CI/CD
**Цель:** Настроить тесты, линтеры и CI/CD pipeline
**Зависимости:** Stage 1, Stage 2, Stage 3, Stage 4
**Входные данные:** Полностью собранный проект
**Выходные файлы:**
- `core-database/src/test/java/com/safeplant/core/database/DatabaseTest.kt`
- `core-database/src/androidTest/java/com/safeplant/core/database/DatabaseMigrationTest.kt`
- `feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizViewModelTest.kt`
- `feature-qr/src/test/java/com/safeplant/feature/qr/QRValidatorTest.kt`
- `app/src/test/java/com/safeplant/VersionCheckerTest.kt`
- `detekt.yml`
- `ktlint_config.xml`
- `.github/workflows/ci.yml`
**Критерии завершения:**
- [ ] Unit-тесты для бизнес-логики (70% покрытие)
- [ ] Интеграционные тесты для Room
- [ ] Настроенные Detekt и ktlint
- [ ] GitHub Actions CI/CD pipeline
**Риски:** Низкое покрытие тестов для сложной бизнес-логики

---

## Verify
```yaml
- command: Проверка сборки проекта
  command: ./gradlew build
- command: Запуск unit-тестов
  command: ./gradlew test
- command: Проверка покрытия тестами
  command: ./gradlew jacocoTestReport
- command: Запуск линтеров
  command: ./gradlew ktlintCheck detekt
- command: Проверка копирования assets
  command: ls -la app/src/main/assets/
- command: Проверка работы приложения
  command: adb shell am start -n com.safeplant/.MainActivity
```