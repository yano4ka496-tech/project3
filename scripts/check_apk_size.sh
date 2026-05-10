#!/bin/bash

# Скрипт для проверки размера APK/AAB
# Проверяет, что размер APK/AAB не превышает 2 ГБ

echo "Проверка размера APK/AAB..."

# Проверяем наличие APK
if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
    APK_SIZE=$(stat -c%s "app/build/outputs/apk/release/app-release.apk")
    APK_SIZE_MB=$((APK_SIZE / 1024 / 1024))
    
    echo "Размер APK: $APK_SIZE_MB МБ"
    
    if [ "$APK_SIZE_MB" -gt 2048 ]; then
        echo "Ошибка: Размер APK ($APK_SIZE_MB МБ) превышает 2 ГБ"
        exit 1
    else
        echo "✓ Размер APK ($APK_SIZE_MB МБ) соответствует требованиям (< 2 ГБ)"
    fi
else
    echo "Ошибка: APK не найден в app/build/outputs/apk/release/app-release.apk"
    exit 1
fi

# Проверяем наличие AAB
if [ -f "app/build/outputs/bundle/release/app-release.aab" ]; then
    AAB_SIZE=$(stat -c%s "app/build/outputs/bundle/release/app-release.aab")
    AAB_SIZE_MB=$((AAB_SIZE / 1024 / 1024))
    
    echo "Размер AAB: $AAB_SIZE_MB МБ"
    
    if [ "$AAB_SIZE_MB" -gt 2048 ]; then
        echo "Ошибка: Размер AAB ($AAB_SIZE_MB МБ) превышает 2 ГБ"
        exit 1
    else
        echo "✓ Размер AAB ($AAB_SIZE_MB МБ) соответствует требованиям (< 2 ГБ)"
    fi
else
    echo "Ошибка: AAB не найден в app/build/outputs/bundle/release/app-release.aab"
    exit 1
fi

echo "Проверка размера APK/AAB пройдена успешно!"
exit 0