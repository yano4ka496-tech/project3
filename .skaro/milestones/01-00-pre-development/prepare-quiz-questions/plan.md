## plan.md

## Stage 1: Доработка обработки ошибок загрузки вопросов и выборки при недостаточном количестве
**Goal:** Реализовать обработку отсутствия файла questions.json и выборку всех доступных вопросов, если их меньше 10.
**Depends on:** none (использует текущую реализацию)
**Inputs:** 
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt
**Outputs:**
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt (обновленный)
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt (обновленный)
**DoD:**
  - [ ] В QuizRepository при отсутствии файла questions.json генерируется исключение.
  - [ ] В QuizViewModel обрабатывается исключение и устанавливается состояние ошибки.
  - [ ] В QuizViewModel при загрузке вопросов, если их меньше 10, выбираются все доступные.
  - [ ] Если вопросов нет (пустой список), показывается ошибка.
**Risks:** 
  - При ошибке загрузки приложение может не корректно обработать состояние. Нужно убедиться, что состояние ошибки передается в UI.

## Stage 2: Доработка QuizScreen для отображения ошибки загрузки и навигации
**Goal:** Реализовать отображение ошибки загрузки и возможность закрыть квиз.
**Depends on:** Stage 1
**Inputs:** 
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizViewModel.kt (из Stage 1)
**Outputs:**
  - feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizScreen.kt (обновленный)
**DoD:**
  - [ ] В QuizScreen добавлено состояние для отображения ошибки загрузки.
  - [ ] При состоянии ошибки отображается сообщение и кнопка для закрытия квиза.
  - [ ] Кнопка закрытия квиза вызывает навигацию обратно (например, на главный экран).
**Risks:** 
  - Навигация может быть не настроена. Нужно проверить, что есть возможность вернуться на предыдущий экран.

## Stage 3: Проверка доступности квиза без проверки пропуска
**Goal:** Убедиться, что квиз доступен без проверки действующего пропуска.
**Depends on:** none (но может требовать проверки навигационного графика)
**Inputs:** 
  - core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt
  - core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt
**Outputs:** 
  - (возможно, обновленные файлы навигации, если потребуется)
**DoD:**
  - [ ] Проверить, что навигация на экран квиза не требует проверки пропуска.
  - [ ] При необходимости, обновить навигационный граф, чтобы квиз был доступен без проверки.
  - [ ] Убедиться, что при запуске квиза не выполняется проверка действующего пропуска.
**Risks:** 
  - Если навигация настроена через проверку пропуска, может потребоваться изменение нескольких файлов. Нужно проверить все точки входа в квиз.

## Verify
- command: Проверка сборки проекта
  command: ./gradlew build
- command: Запуск unit-тестов для feature-quiz
  command: ./gradlew :feature-quiz:test
- command: Проверка навигации на квиз
  command: (проверка вручную или через UI-тесты, если есть)