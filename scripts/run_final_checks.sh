#!/bin/bash

# Скрипт для выполнения всех проверок Stage 3: Сборка релиза и проверка покрытия

echo "=== Запуск всех проверок Stage 3 ==="

# 1. Сборка release APK/AAB
echo "1. Сборка release APK/AAB..."
if ./gradlew assembleRelease; then
    echo "✓ Сборка APK/AAB прошла успешно"
else
    echo "✗ Сборка APK/AAB не удалась"
    exit 1
fi

# 2. Проверка размера APK
echo "2. Проверка размера APK..."
if ./scripts/check_apk_size.sh; then
    echo "✓ Проверка размера APK пройдена"
else
    echo "✗ Проверка размера APK не пройдена"
    exit 1
fi

# 3. Генерация отчета о покрытии
echo "3. Генерация отчета о покрытии..."
if ./gradlew jacocoTestReport; then
    echo "✓ Отчет о покрытии сгенерирован"
    if [ -f "app/build/reports/jacoco/test/html/index.html" ]; then
        echo "Отчет доступен: app/build/reports/jacoco/test/html/index.html"
    else
        echo "Предупреждение: отчет не найден по ожидаемому пути"
    fi
else
    echo "✗ Генерация отчета о покрытии не удалась"
    exit 1
fi

# 4. Проверка отсутствия сетевых запросов
echo "4. Проверка отсутствия сетевых запросов..."
if ./scripts/check_network_requests.sh; then
    echo "✓ Проверка отсутствия сетевых запросов пройдена"
else
    echo "✗ Проверка отсутствия сетевых запросов не пройдена"
    exit 1
fi

# 5. Проверка линтинга
echo "5. Проверка линтинга..."
if ./gradlew ktlintCheck; then
    echo "✓ Проверка линтинга пройдена"
else
    echo "✗ Проверка линтинга не пройдена"
    exit 1
fi

# 6. Проверка детекта
echo "6. Проверка детекта..."
if ./gradlew detekt; then
    echo "✓ Проверка детекта пройдена"
else
    echo "✗ Проверка детекта не пройдена"
    exit 1
fi

# 7. Подготовка релиза
echo "7. Подготовка релиза..."
if ./scripts/prepare_release.sh; then
    echo "✓ Релиз подготовлен"
else
    echo "✗ Подготовка релиза не удалась"
    exit 1
fi

echo "=== Все проверки Stage 3 успешно пройдены! ==="
exit 0