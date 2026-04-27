# AI_NOTES — Stage 1: Настройка навигации и граф навигации

## Что было сделано
- Обновлен MainActivity для использования NavHost с графом навигации
- Реализован AppNavigation с NavHost, содержащим все экраны приложения
- Добавлены экраны ошибок (ZoneNotFound, AccessDenied) в NavigationDestinations
- Создан NavigationActions для управления навигацией между экранами
- Реализован ErrorHandler для отображения экранов ошибок
- Создан NavGraph с определением всех маршрутов навигации

## Почему такой подход
- Использование Jetpack Navigation Compose обеспечивает стандартный и предсказуемый способ навигации
- Разделение логики навигации на отдельные классы улучшает читаемость и поддержку
- Обработка ошибок вынесена в отдельный ErrorHandler для централизованного управления
- NavigationActions предоставляет единый интерфейс для навигации из разных частей приложения
- NavGraph инкапсулирует все маршруты и их конфигурацию

## Файлы созданы / изменены
| Файл | Действие | Описание |
|---|---|---|
| `app/src/main/java/com/safeplant/MainActivity.kt` | изменен | Добавлен NavHost с графом навигации |
| `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt` | изменен | Реализован NavHost с экранами приложения |
| `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt` | изменен | Добавлены константы для экранов ошибок |
| `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationActions.kt` | создан | Класс для управления навигацией между экранами |
| `core-navigation/src/main/java/com/safeplant/core/navigation/ErrorHandler.kt` | создан | Обработчик ошибок навигации |
| `core-navigation/src/main/java/com/safeplant/core/navigation/NavGraph.kt` | создан | Граф навигации с определением всех маршрутов |

## Риски и ограничения
- В текущей реализации ErrorHandler использует NavController, который не доступен в статическом контексте - требуется передача NavController в ErrorHandler
- Deep link safeplant://map?zoneId={zoneId} реализован, но не проверена валидность zoneId
- Нет обработки ошибок при навигации с некорректными параметрами
- Нет проверки действующего допуска при навигации на карту

## Соответствие инвариантам
- [ ] Модульная структура - соблюдено (навигация вынесена в отдельный модуль)
- [ ] Отсутствие сетевых зависимостей - соблюдено (все операции офлайн)
- [ ] Использование Jetpack Compose - соблюдено
- [ ] Стандарты именования - соблюдено (PascalCase для классов, camelCase для функций)
- [ ] Максимальная длина функции - соблюдено (все функции короче 40 строк)

## Как проверить
1. Проверить сборку приложения: `./gradlew assembleDebug`
2. Проверить навигацию между экранами вручную
3. Проверить deep link: `adb shell am start -W -a android.intent.action.VIEW -d "safeplant://map?zoneId=123" com.safeplant`
4. Проверить линтинг: `./gradlew ktlintCheck`
5. Проверить детект: `./gradlew detekt`