# Задачи: Инициализация проекта SafePlant

## Stage 1: Структура проекта и базовая настройка
- [ ] Создать директории для всех модулей → `core-navigation/`, `core-database/`, `core-storage/`, `core-security/`, `core-mapping/`, `feature-quiz/`, `feature-map/`, `feature-training/`, `feature-qr/`, `feature-profile/`
- [ ] Создать build.gradle.kts для каждого модуля → `core-navigation/build.gradle.kts`, `core-database/build.gradle.kts`, и т.д.
- [ ] Обновить settings.gradle.kts → добавить все модули
- [ ] Создать AndroidManifest.xml для каждого модуля → `core-navigation/src/main/AndroidManifest.xml`, и т.д.
- [ ] Обновить app/build.gradle.kts → добавить зависимости для всех модулей

## Stage 2: Настройка ядра проекта (база данных, безопасность, хранилище)
- [ ] Реализовать AppDatabase с шифрованием → `core-database/src/main/java/com/safeplant/core/database/AppDatabase.kt`
- [ ] Создать сущности базы данных → `AccessPass.kt`, `MapObject.kt`, `QuizResult.kt`, `TrainingVideo.kt`
- [ ] Реализовать DAO для каждой сущности → `AccessPassDao.kt`, `MapObjectDao.kt`, и т.д.
- [ ] Создать миграции базы данных → `Migration1To2.kt`
- [ ] Реализовать RootDetector → `core-security/src/main/java/com/safeplant/core/security/RootDetector.kt`
- [ ] Реализовать QRValidator → `core-security/src/main/java/com/safeplant/core/security/QRValidator.kt`
- [ ] Реализовать AssetManager → `core-storage/src/main/java/com/safeplant/core/storage/AssetManager.kt`
- [ ] Реализовать VideoCopier → `core-storage/src/main/java/com/safeplant/core/storage/VideoCopier.kt`
- [ ] Создать DatabaseModule → `core-database/src/main/java/com/safeplant/core/database/di/DatabaseModule.kt`

## Stage 3: Реализация функциональных модулей
- [ ] Реализовать QuizViewModel → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt`
- [ ] Создать QuizScreen → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt`
- [ ] Реализовать QuizRepository → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt`
- [ ] Создать QuizModule → `feature-quiz/src/main/java/com/safeplant/feature/quiz/di/QuizModule.kt`
- [ ] Реализовать MapViewModel → `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt`
- [ ] Создать MapScreen → `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt`
- [ ] Реализовать MapRepository → `feature-map/src/main/java/com/safeplant/feature/map/MapRepository.kt`
- [ ] Создать MapModule → `feature-map/src/main/java/com/safeplant/feature/map/di/MapModule.kt`
- [ ] Реализовать TrainingViewModel → `feature-training/src/main/java/com/safeplant/feature/training/TrainingViewModel.kt`
- [ ] Создать TrainingScreen → `feature-training/src/main/java/com/safeplant/feature/training/TrainingScreen.kt`
- [ ] Реализовать TrainingRepository → `feature-training/src/main/java/com/safeplant/feature/training/TrainingRepository.kt`
- [ ] Создать TrainingModule → `feature-training/src/main/java/com/safeplant/feature/training/di/TrainingModule.kt`
- [ ] Реализовать QRScannerViewModel → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt`
- [ ] Создать QRScannerScreen → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt`
- [ ] Реализовать QRScannerRepository → `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerRepository.kt`
- [ ] Создать QRModule → `feature-qr/src/main/java/com/safeplant/feature/qr/di/QRModule.kt`
- [ ] Реализовать ProfileViewModel → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt`
- [ ] Создать ProfileScreen → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt`
- [ ] Реализовать ProfileRepository → `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt`
- [ ] Создать ProfileModule → `feature-profile/src/main/java/com/safeplant/feature/profile/di/ProfileModule.kt`

## Stage 4: Интеграция и навигация
- [ ] Реализовать AppNavigation → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`
- [ ] Создать NavigationDestinations → `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`
- [ ] Реализовать SafePlantApplication → `app/src/main/java/com/safeplant/SafePlantApplication.kt`
- [ ] Создать AppModule → `app/src/main/java/com/safeplant/di/AppModule.kt`
- [ ] Создать RepositoryModule → `app/src/main/java/com/safeplant/di/RepositoryModule.kt`
- [ ] Обновить MainActivity → `app/src/main/java/com/safeplant/MainActivity.kt`
- [ ] Реализовать VersionChecker → `app/src/main/java/com/safeplant/VersionChecker.kt`
- [ ] Добавить task для копирования assets → `app/build.gradle.kts`

## Stage 5: Тестирование и CI/CD
- [ ] Создать DatabaseTest → `core-database/src/test/java/com/safeplant/core/database/DatabaseTest.kt`
- [ ] Создать DatabaseMigrationTest → `core-database/src/androidTest/java/com/safeplant/core/database/DatabaseMigrationTest.kt`
- [ ] Создать QuizViewModelTest → `feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizViewModelTest.kt`
- [ ] Создать QRValidatorTest → `feature-qr/src/test/java/com/safeplant/feature/qr/QRValidatorTest.kt`
- [ ] Создать VersionCheckerTest → `app/src/test/java/com/safeplant/VersionCheckerTest.kt`
- [ ] Создать detekt.yml → `detekt.yml`
- [ ] Создать ktlint_config.xml → `ktlint_config.xml`
- [ ] Создать CI/CD pipeline → `.github/workflows/ci.yml`
```