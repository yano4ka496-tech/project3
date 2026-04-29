#!/bin/bash

# Скрипт для запуска всех проверок Stage 8

echo "Запуск всех проверок Stage 8..."

# Проверка зависимостей
echo "Проверка зависимостей..."
if ! command -v adb >/dev/null 2>&1; then
    echo "Ошибка: adb не найден. Установите Android SDK."
    exit 1
fi

if ! command -v python3 >/dev/null 2>&1; then
    echo "Ошибка: python3 не найден."
    exit 1
fi

# Запуск unit-тестов
echo "Запуск unit-тестов..."
./gradlew test --continue

# Запуск интеграционных тестов
echo "Запуск интеграционных тестов..."
./gradlew connectedAndroidTest --continue

# Проверка конвертации PDF
echo "Проверка конвертации PDF..."
bash scripts/check_pdf_conversion.sh

# Валидация MBTiles
echo "Валидация MBTiles..."
python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles

# Проверка линтинга
echo "Проверка линтинга..."
./gradlew ktlintCheck

# Проверка детекта
echo "Проверка детекта..."
./gradlew detekt

# Проверка покрытия тестами
echo "Проверка покрытия тестами..."
./gradlew test --continue --info

# Проверка безопасности
echo "Проверка безопасности..."
./gradlew test --tests *RootDetectionTest --continue
./gradlew test --tests *DatabaseEncryptionTest --continue

# Проверка производительности
echo "Проверка производительности..."
./gradlew connectedAndroidTest --tests *PerformanceTest --continue

# Проверка доступности
echo "Проверка доступности..."
./gradlew lint --continue

# Проверка документации
echo "Проверка документации..."
if [ -f "AI_NOTES.md" ]; then
    echo "✓ Файл AI_NOTES.md существует"
else
    echo "Предупреждение: файл AI_NOTES.md отсутствует"
fi

# Проверка зависимостей
echo "Проверка зависимостей..."
./gradlew dependencies --continue

echo "Все проверки Stage 8 завершены."