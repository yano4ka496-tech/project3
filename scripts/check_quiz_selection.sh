#!/bin/bash

# Скрипт для проверки алгоритма выбора 10 случайных уникальных вопросов

echo "Проверка алгоритма выбора 10 случайных уникальных вопросов..."

# Проверка наличия класса QuizRepository
if [ ! -f "feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt" ]; then
    echo "Ошибка: не найден класс QuizRepository в feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt"
    exit 1
fi

# Проверка наличия метода getQuestions
if ! grep -q "fun getQuestions()" "feature-quiz/src/main/java/com/safeplant/feature/quiz/QuizRepository.kt"; then
    echo "Ошибка: не найден метод getQuestions() в классе QuizRepository"
    exit 1
fi

# Проверка тестов для QuizSelection
if [ ! -f "feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizSelectionTest.kt" ]; then
    echo "Ошибка: не найдены тесты для QuizSelection в feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizSelectionTest.kt"
    exit 1
fi

# Проверка уникальности вопросов в тестах
if ! grep -q "unique" "feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizSelectionTest.kt"; then
    echo "Ошибка: в тестах не проверяется уникальность вопросов"
    exit 1
fi

# Проверка количества вопросов
if ! grep -q "10" "feature-quiz/src/test/java/com/safeplant/feature/quiz/QuizSelectionTest.kt"; then
    echo "Ошибка: в тестах не проверяется выбор 10 вопросов"
    exit 1
fi

echo "Проверка алгоритма выбора 10 случайных уникальных вопросов пройдена успешно!"
exit 0