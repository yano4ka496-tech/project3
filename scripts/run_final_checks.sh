#!/bin/bash

echo "=== Финальная проверка SafePlant ==="
echo "=== Запуск всех проверок SafePlant ==="

# 1. Проверка конвертации PDF в MBTiles
echo "1. Проверка конвертации PDF в MBTiles"
python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf tests/data/mbtiles/test.mbtiles
if [ $? -ne 0 ]; then
    echo "Ошибка: Конвертация PDF в MBTiles не пройдена"
    exit 1
fi

# 2. Валидация MBTiles файла
echo "2. Валидация MBTiles файла"
python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles
if [ $? -ne 0 ]; then
    echo "Ошибка: Валидация MBTiles не пройдена"
    exit 1
fi

# 3. Проверка зависимостей для конвертации
echo "3. Проверка зависимостей для конвертации"
bash scripts/check_pdf_conversion.sh
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка зависимостей не пройдена"
    exit 1
fi

# 4. Запуск всех unit-тестов (включая QR-позиционирование)
echo "4. Запуск всех unit-тестов"
./gradlew test
if [ $? -ne 0 ]; then
    echo "Ошибка: Unit-тесты не пройдены"
    exit 1
fi

# 5. Запуск интеграционных тестов
echo "5. Запуск интеграционных тестов"
./gradlew connectedAndroidTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Интеграционные тесты не пройдены"
    exit 1
fi

# 6. Проверка линтинга
echo "6. Проверка линтинга"
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
    echo "Ошибка: Линтинг не пройден"
    exit 1
fi

# 7. Проверка Detekt
echo "7. Проверка Detekt"
./gradlew detekt
if [ $? -ne 0 ]; then
    echo "Ошибка: Detekt не пройден"
    exit 1
fi

echo "✓ Финальная проверка пройдена"
exit 0
