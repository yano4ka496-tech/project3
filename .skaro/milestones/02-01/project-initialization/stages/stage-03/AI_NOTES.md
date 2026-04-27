# AI_NOTES — Stage 3: Реализация функциональных модулей

## Что было сделано
- Реализован модуль викторины (feature-quiz) с логикой подсчёта баллов и возможностью повторения до достижения проходного балла (8/10)
- Реализован модуль карты (feature-map) с отображением предустановленных маршрутов, опасных зон и зон эвакуации
- Реализован модуль обучения (feature-training) с воспроизведением обучающих видео
- Реализован модуль QR-сканера (feature-qr) с обработкой некорректных QR-кодов и отображением ошибок
- Реализован модуль профиля (feature-profile) с отображением цифрового пропуска и проверкой версии приложения

## Почему такой подход
- Модуль викторины реализован с использованием MVVM паттерна, что обеспечивает разделение ответственности и упрощает тестирование
- Модуль карты использует MapLibre GL Native для отображения векторных карт с предустановленными слоями
- Модуль обучения реализован с возможностью воспроизведения видео через ExoPlayer
- Модуль QR-сканера обрабатывает ошибки и предоставляет пользователю возможность повторить сканирование
- Модуль профиля проверяет версию приложения при запуске и сбрасывает цифровой пропуск при изменении версии

## Файлы создано / изменено
| Файл | Действие | Описание |
|---|---|---|
| `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt` | создан | ViewModel для викторины с логикой подсчёта баллов |
| `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt` | создан | Экран викторины с отображением вопросов и результатов |
| `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt` | создан | Репозиторий для работы с данными викторины |
| `feature-quiz/src/main/java/com/safeplant/feature/quiz/di/QuizModule.kt` | создан | Модуль Hilt для внедрения зависимостей в викторину |
| `feature-map/src/main/java/com/safeplant/feature/map/MapViewModel.kt` | создан | ViewModel для карты с управлением слоями и объектами |
| `feature-map/src/main/java/com/safeplant/feature/map/MapScreen.kt` | создан | Экран карты с отображением слоёв и объектов |
| `feature-map/src/main/java/com/safeplant/feature/map/MapRepository.kt` | создан | Репозиторий для работы с данными карты |
| `feature-map/src/main/java/com/safeplant/feature/map/di/MapModule.kt` | создан | Модуль Hilt для внедрения зависимостей в карту |
| `feature-training/src/main/java/com/safeplant/feature/training/TrainingViewModel.kt` | создан | ViewModel для обучающего центра |
| `feature-training/src/main/java/com/safeplant/feature/training/TrainingScreen.kt` | создан | Экран обучающего центра с воспроизведением видео |
| `feature-training/src/main/java/com/safeplant/feature/training/TrainingRepository.kt` | создан | Репозиторий для работы с обучающими видео |
| `feature-training/src/main/java/com/safeplant/feature/training/di/TrainingModule.kt` | создан | Модуль Hilt для внедрения зависимостей в обучение |
| `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerViewModel.kt` | создан | ViewModel для QR-сканера с обработкой ошибок |
| `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerScreen.kt` | создан | Экран QR-сканера с камерой и обработкой результатов |
| `feature-qr/src/main/java/com/safeplant/feature/qr/QRScannerRepository.kt` | создан | Репозиторий для работы с QR-кодами |
| `feature-qr/src/main/java/com/safeplant/feature/qr/di/QRModule.kt` | создан | Модуль Hilt для внедрения зависимостей в QR-сканер |
| `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileViewModel.kt` | создан | ViewModel для профиля с проверкой версии приложения |
| `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileScreen.kt` | создан | Экран профиля с отображением цифрового пропуска |
| `feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt` | создан | Репозиторий для работы с цифровым пропуском |
| `feature-profile/src/main/java/com/safeplant/feature/profile/di/ProfileModule.kt` | создан | Модуль Hilt для внедрения зависимостей в профиль |

## Риски и ограничения
- Интеграция MapLibre GL Native может вызвать проблемы на некоторых устройствах из-за требований к OpenGL
- Воспроизведение видео через ExoPlayer может требовать дополнительных настроек для работы в офлайн-режиме
- QR-сканер может не корректно работать на устройствах с ограниченными возможностями камеры
- Проверка версии приложения может не срабатывать при установке через side-load

## Соответствие инвариантам
- [ ] Логика викторины с повторением до 8/10 баллов — соблюдено
- [ ] Отображение карты с предустановленными маршрутами — соблюдено
- [ ] Воспроизведение обучающих видео — соблюдено
- [ ] Сканирование QR-кодов с обработкой ошибок — соблюдено
- [ ] Профиль с цифровым пропуском — соблюдено

## Как проверить
1. Проверить сборку проекта: `./gradlew build`
2. Проверить работу викторины: пройти викторину и убедиться, что можно повторить до 8/10 баллов
3. Проверить отображение карты: убедиться, что слои отображаются корректно
4. Проверить воспроизведение видео: выбрать видео и убедиться, что оно воспроизводится
5. Проверить QR-сканер: отсканировать корректный и некорректный QR-код
6. Проверить профиль: убедиться, что цифровой пропуск отображается корректно и сбрасывается при изменении версии