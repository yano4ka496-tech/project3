# AI_NOTES — Stage 2: Реализация MapView и загрузка MBTiles

## What was done
- Реализован MapRenderer для загрузки MBTiles из assets и отображения карты
- Создан MapView как Composable компонент для встраивания карты в UI
- Реализован MapController для управления камерой и масштабом с ограничениями
- Обновлен MapScreen для отображения карты и управления масштабом
- Реализована копирование MBTiles из assets во внутреннее хранилище

## Why this approach
- Использование MapLibre GL Native для отображения векторных карт
- Копирование MBTiles из assets во внутреннее хранилище для работы с MapLibre
- Реализация контроллера камеры с ограничениями масштаба (10 м - 10 км)
- Использование AndroidView для встраивания нативного MapView в Compose

## Files created / modified
| File | Action | Description |
|---|---|---|
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapRenderer.kt` | created | Реализация рендерера карты с загрузкой MBTiles |
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapView.kt` | created | Composable компонент для отображения карты |
| `core-mapping/src/main/java/com/safeplant/core/mapping/MapController.kt` | created | Контроллер управления камерой и масштабом |
| `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` | modified | Обновлен экран карты с использованием MapView и MapController |

## Risks and limitations
- Проблемы с производительностью на слабых устройствах при отрисовке карты
- Возможные ошибки при копировании MBTiles из assets во внутреннее хранилище
- Ограничения масштаба могут не точно соответствовать требуемым (10 м - 10 км) из-за особенностей проекции

## Invariant compliance
- [ ] Offline-first — карта работает без интернета (реализовано)
- [ ] Безопасность — нет сетевых запросов (реализовано)
- [ ] Производительность — 60 FPS (не проверено, требует тестирования)
- [ ] Ограничения масштаба — 10 м - 10 км (реализовано)

## How to verify
1. Запустить приложение и проверить отображение карты
2. Проверить работу кнопок масштабирования (+/-)
3. Убедиться, что масштаб не выходит за пределы 10 м - 10 км
4. Проверить работу карты в режиме "в самолете"