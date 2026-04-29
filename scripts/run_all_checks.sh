#!/bin/bash

# Скрипт для выполнения всех проверок SafePlant

echo "=== Запуск всех проверок SafePlant ==="

# Проверка конвертации PDF в MBTiles
echo "1. Проверка конвертации PDF в MBTiles"
python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf tests/data/mbtiles/test.mbtiles
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка конвертации PDF в MBTiles не пройдена"
    exit 1
fi

# Валидация MBTiles файла
echo "2. Валидация MBTiles файла"
python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles
if [ $? -ne 0 ]; then
    echo "Ошибка: Валидация MBTiles файла не пройдена"
    exit 1
fi

# Проверка зависимостей для конвертации
echo "3. Проверка зависимостей для конвертации"
bash scripts/check_pdf_conversion.sh
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка зависимостей для конвертации не пройдена"
    exit 1
fi

# Unit-тесты для QR-позиционирования
echo "4. Unit-тесты для QR-позиционирования"
./gradlew test --tests *QrCodeDaoTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Unit-тесты для QR-позиционирования не пройдены"
    exit 1
fi

# Тесты шифрования базы данных
echo "5. Тесты шифрования базы данных"
./gradlew test --tests *DatabaseEncryptionTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Тесты шифрования базы данных не пройдены"
    exit 1
fi

# Интеграционные тесты
echo "6. Интеграционные тесты"
./gradlew connectedAndroidTest --tests *MapScreenTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Интеграционные тесты не пройдены"
    exit 1
fi

# Тесты сканирования QR-кодов
echo "7. Тесты сканирования QR-кодов"
./gradlew test --tests *QrCodeScanTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Тесты сканирования QR-кодов не пройдены"
    exit 1
fi

# Тесты обновления приложения
echo "8. Тесты обновления приложения"
./gradlew test --tests *AppUpdateTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Тесты обновления приложения не пройдены"
    exit 1
fi

# Тесты обнаружения root-доступа
echo "9. Тесты обнаружения root-доступа"
./gradlew test --tests *RootDetectionTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Тесты обнаружения root-доступа не пройдены"
    exit 1
fi

# Тесты доступа к квизу
echo "10. Тесты доступа к квизу"
./gradlew test --tests *QuizAccessTest
if [ $? -ne 0 ]; then
    echo "Ошибка: Тесты доступа к квизу не пройдены"
    exit 1
fi

# Проверка линтинга
echo "11. Проверка линтинга"
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка линтинга не пройдена"
    exit 1
fi

# Проверка детекта
echo "12. Проверка детекта"
./gradlew detekt
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка детекта не пройдена"
    exit 1
fi

# Запуск всех тестов
echo "13. Запуск всех тестов"
bash scripts/run_all_tests.sh
if [ $? -ne 0 ]; then
    echo "Ошибка: Запуск всех тестов не удался"
    exit 1
fi

# Финальная проверка
echo "14. Финальная проверка"
bash scripts/run_final_checks.sh
if [ $? -ne 0 ]; then
    echo "Ошибка: Финальная проверка не пройдена"
    exit 1
fi

echo "=== Все проверки пройдены успешно ==="