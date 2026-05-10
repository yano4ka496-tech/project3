#!/bin/bash

# Скрипт для выполнения проверок Stage 5: Тестирование и интеграция QR-сканера
# Выполняет unit-тесты, lint и сборку модуля feature-qr

echo "Выполнение проверок Stage 5 для QR-сканера..."

# 1. Unit tests QRScannerViewModel
echo "Запуск unit-тестов для QRScannerViewModel..."
./gradlew :feature-qr:test --tests "*QRScannerViewModelTest*"
if [ $? -ne 0 ]; then
    echo "ОШИБКА: Unit-тесты для QRScannerViewModel не пройдены"
    exit 1
fi

# 2. Unit tests QRValidator
echo "Запуск unit-тестов для QRValidator..."
./gradlew :feature-qr:test --tests "*QRValidatorTest*"
if [ $? -ne 0 ]; then
    echo "ОШИБКА: Unit-тесты для QRValidator не пройдены"
    exit 1
fi

# 3. Lint feature-qr
echo "Запуск lint для feature-qr..."
./gradlew :feature-qr:lint
if [ $? -ne 0 ]; then
    echo "ОШИБКА: Lint для feature-qr не пройден"
    exit 1
fi

# 4. Build feature-qr
echo "Сборка feature-qr..."
./gradlew :feature-qr:assembleDebug
if [ $? -ne 0 ]; then
    echo "ОШИБКА: Сборка feature-qr не удалась"
    exit 1
fi

echo "Все проверки Stage 5 пройдены успешно!"