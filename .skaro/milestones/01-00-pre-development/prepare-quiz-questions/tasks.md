## tasks.md

# Tasks: Подготовка вопросов квиза

## Stage 1: Доработка обработки ошибок загрузки вопросов и выборки при недостаточном количестве
- [ ] Обновить QuizRepository для обработки отсутствия файла questions.json → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt`
- [ ] Обновить QuizViewModel для обработки ошибки загрузки и выбора всех доступных вопросов → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt`

## Stage 2: Доработка QuizScreen для отображения ошибки загрузки и навигации
- [ ] Обновить QuizScreen для отображения ошибки загрузки и кнопки закрытия → `feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt`

## Stage 3: Проверка доступности квиза без проверки пропуска
- [ ] Проверить и обновить навигацию, если требуется, чтобы квиз был доступен без проверки пропуска → `core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt`, `core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt`