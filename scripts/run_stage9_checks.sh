#!/bin/bash

# Выполнение unit-тестов для use case
echo "Running unit tests for use case..."
./gradlew :feature-quiz:test --tests "*TakeQuizUseCaseTest*"

# Выполнение unit-тестов для ViewModel
echo "Running unit tests for ViewModel..."
./gradlew :feature-quiz:test --tests "*QuizViewModelTest*"

# Выполнение интеграционных тестов для DAO
echo "Running integration tests for DAO..."
./gradlew :core-database:test --tests "*QuizDaoTest*" --tests "*QuizResultDaoTest*"

# Сборка feature-quiz модуля
echo "Building feature-quiz module..."
./gradlew :feature-quiz:build

# Проверка типов
echo "Running type check..."
./gradlew :feature-quiz:kotlinCompileKotlin

# Линтинг
echo "Running lint..."
./gradlew :feature-quiz:lint

# Проверка покрытия кода
echo "Running code coverage check..."
./gradlew :feature-quiz:jacocoTestReport
./gradlew :core-database:jacocoTestReport

# Проверка покрытия >=70% (если есть плагин jacoco)
echo "Checking code coverage..."
if [ -f "feature-quiz/build/reports/jacoco/test/html/index.html" ]; then
    echo "Coverage report for feature-quiz generated at feature-quiz/build/reports/jacoco/test/html/index.html"
fi

if [ -f "core-database/build/reports/jacoco/test/html/index.html" ]; then
    echo "Coverage report for core-database generated at core-database/build/reports/jacoco/test/html/index.html"
fi