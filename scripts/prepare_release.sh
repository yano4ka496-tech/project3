#!/bin/bash

# Скрипт для подготовки релиза SafePlant

echo "Подготовка релиза SafePlant..."

# Создаем директорию для релиза
mkdir -p release

# Копируем APK и AAB из артефактов сборки
if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
    cp app/build/outputs/apk/release/app-release.apk release/
    echo "APK скопирован в release/"
else
    echo "Предупреждение: APK не найден, сборка не выполнена"
fi

if [ -f "app/build/outputs/bundle/release/app-release.aab" ]; then
    cp app/build/outputs/bundle/release/app-release.aab release/
    echo "AAB скопирован в release/"
else
    echo "Предупреждение: AAB не найден, сборка не выполнена"
fi

# Копируем отчет о покрытии тестами
if [ -d "app/build/reports/jacoco/test/html" ]; then
    cp -r app/build/reports/jacoco/test/html release/coverage-report
    echo "Отчет о покрытии тестами скопирован в release/coverage-report"
else
    echo "Предупреждение: Отчет о покрытии тестами не найден"
fi

# Копируем документацию (если есть)
if [ -d "docs" ]; then
    cp -r docs release/
    echo "Документация скопирована в release/docs"
fi

# Копируем README.md
if [ -f "README.md" ]; then
    cp README.md release/
    echo "README.md скопирован в release/"
fi

# Копируем файл лицензии (если есть)
if [ -f "LICENSE" ]; then
    cp LICENSE release/
    echo "LICENSE скопирован в release/"
fi

# Копируем AI_NOTES.md
if [ -f "AI_NOTES.md" ]; then
    cp AI_NOTES.md release/
    echo "AI_NOTES.md скопирован в release/"
fi

# Копируем ASSUMPTIONS.md
if [ -f "ASSUMPTIONS.md" ]; then
    cp ASSUMPTIONS.md release/
    echo "ASSUMPTIONS.md скопирован в release/"
fi

# Создаем ZIP-архив
if [ -d "release" ]; then
    zip -r release-package.zip release/
    echo "Релиз подготовлен: release-package.zip"
    
    # Выводим информацию о релизе
    echo "Информация о релизе:"
    echo "Версия: $(grep versionName app/build.gradle.kts | awk -F'"' '{print $2}')"
    echo "Код версии: $(grep versionCode app/build.gradle.kts | awk -F'=' '{print $2}' | tr -d ' ')"
    echo "Дата сборки: $(date)"
    
    # Удаляем временную директорию
    rm -rf release
else
    echo "Ошибка: Директория release не создана"
    exit 1
fi

exit 0