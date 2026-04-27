# AI_NOTES — Stage 4: Интеграция и навигация

## Что было сделано
- Реализован навигационный хост AppNavigation с использованием Jetpack Navigation Compose
- Созданы константы для навигации NavigationDestinations
- Настроен класс SafePlantApplication для инициализации Hilt
- Реализованы модули Hilt: AppModule и RepositoryModule для внедрения зависимостей
- Обновлена MainActivity для использования навигации и MaterialTheme
- Реализован VersionChecker для проверки версии приложения и сброса цифрового пропуска
- Добавлен task в app/build.gradle.kts для копирования assets в APK

## Почему такой подход
- Использование Jetpack Navigation Compose обеспечивает декларативный и типобезопасный подход к навигации
- Hilt обеспечивает централизованное управление зависимостями и упрощает тестирование
- VersionChecker использует EncryptedSharedPreferences для безопасного хранения версии приложения
- Task для копирования assets гарантирует, что все ресурсы будут доступны в runtime

## Файлы создано / изменено
| Файл | Действие | Описание |
|---|---|---|
| `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt` | создан | Навигационный хост приложения с маршрутами между экранами |
| `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt` | создан | Константы для навигации между экранами |
| `app/src/main/java/com/safeplant/SafePlantApplication.kt` | создан | Класс приложения для инициализации Hilt |
| `app/src/main/java/com/safeplant/di/AppModule.kt` | создан | Модуль Hilt для предоставления зависимостей уровня приложения |
| `app/src/main/java/com/safeplant/di/RepositoryModule.kt` | создан | Модуль Hilt для предоставления репозиториев |
| `app/src/main/java/com/safeplant/MainActivity.kt` | изменен | Обновлена для использования навигации и MaterialTheme |
| `app/src/main/java/com/safeplant/VersionChecker.kt` | создан | Класс для проверки версии приложения и сброса цифрового пропуска |
| `app/build.gradle.kts` | изменен | Добавлен task для копирования assets в APK |

## Риски и ограничения
- Deep links из QR-сканера могут не корректно работать на некоторых устройствах из-за особенностей реализации
- Копирование assets может занимать дополнительное время при сборке APK
- VersionChecker может не срабатывать при установке приложения через side-load из-за особенностей PackageManager

## Соответствие инвариантам
- [ ] Навигация между экранами через Jetpack Navigation — соблюдено
- [ ] Внедрение зависимостей через Hilt — соблюдено
- [ ] Проверка версии приложения при запуске — соблюдено
- [ ] Сброс цифрового пропуска при изменении версии — соблюдено
- [ ] Копирование assets в APK — соблюдено

## Как проверить
1. Проверить сборку проекта: `./gradlew build`
2. Проверить навигацию между экранами: запустить приложение и проверить переходы между экранами
3. Проверить работу VersionChecker: изменить версию приложения и проверить, что цифровой пропуск сбрасывается
4. Проверить копирование assets: убедиться, что все ресурсы доступны в runtime