#!/bin/bash

# Скрипт для проверки валидации QR-кодов формата 'номер_объекта|название'

echo "Проверка валидации QR-кодов..."

# Проверка наличия тестовых данных
if [ ! -f "tests/data/qr/valid_qr.txt" ]; then
    echo "Ошибка: не найден файл с валидными QR-кодами tests/data/qr/valid_qr.txt"
    exit 1
fi

if [ ! -f "tests/data/qr/invalid_qr.txt" ]; then
    echo "Ошибка: не найден файл с невалидными QR-кодами tests/data/qr/invalid_qr.txt"
    exit 1
fi

# Проверка валидных QR-кодов
echo "Проверка валидных QR-кодов..."
while IFS= read -r qr_code; do
    if [[ ! $qr_code =~ ^[0-9]+\|[^|]+$ ]]; then
        echo "Ошибка: валидный QR-код '$qr_code' не соответствует формату 'номер_объекта|название'"
        exit 1
    fi
    echo "OK: $qr_code"
done < tests/data/qr/valid_qr.txt

# Проверка невалидных QR-кодов
echo "Проверка невалидных QR-кодов..."
while IFS= read -r qr_code; do
    if [[ $qr_code =~ ^[0-9]+\|[^|]+$ ]]; then
        echo "Ошибка: невалидный QR-код '$qr_code' соответствует формату 'номер_объекта|название'"
        exit 1
    fi
    echo "OK: $qr_code не соответствует формату (ожидается)"
done < tests/data/qr/invalid_qr.txt

echo "Все проверки валидации QR-кодов пройдены успешно!"
exit 0