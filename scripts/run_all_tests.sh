#!/bin/bash

# Скрипт для запуска всех тестов проекта SafePlant

echo "=== Запуск всех тестов проекта SafePlant ==="

# Запуск unit-тестов
echo "1. Запуск unit-тестов..."
if ./gradlew test; then
    echo "✓ Unit-тесты пройдены"
else
    echo "✗ Unit-тесты не пройдены"
    exit 1
fi

# Запуск интеграционных тестов
echo "2. Запуск интеграционных тестов..."
if ./gradlew connectedAndroidTest; then
    echo "✓ Интеграционные тесты пройдены"
else
    echo "✗ Интеграционные тесты не пройдены"
    exit 1
fi

# Проверка покрытия тестами
echo "3. Проверка покрытия тестами..."
if ./gradlew jacocoTestReport; then
    echo "✓ Отчет о покрытии тестами сгенерирован"
else
    echo "✗ Генерация отчета о покрытии тестами не удалась"
    exit 1
fi

# Проверка линтинга
echo "4. Проверка линтинга..."
if ./gradlew ktlintCheck; then
    echo "✓ Проверка линтинга пройдена"
else
    echo "✗ Проверка линтинга не пройдена"
    exit 1
fi

# Проверка детекта
echo "5. Проверка детекта..."
if ./gradlew detekt; then
    echo "✓ Проверка детекта пройдена"
else
    echo "✗ Проверка детекта не пройдена"
    exit 1
fi

echo "=== Все тесты успешно пройдены! ==="
exit 0