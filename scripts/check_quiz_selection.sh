#!/bin/bash
echo "Проверка выбора случайных вопросов"
./gradlew :feature-quiz:test --tests "*.QuizSelectionTest"
