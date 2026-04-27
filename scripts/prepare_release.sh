#!/bin/bash

# Скрипт для подготовки релиза SafePlant

echo "Подготовка релиза SafePlant..."

# Создаем директорию для релиза
mkdir -p release

# Копируем APK и AAB из артефактов сборки
cp app/build/outputs/apk/debug/app-debug.apk release/
cp app/build/outputs/bundle/release/app-release.aab release/

# Копируем документацию (если есть)
if [ -d "docs" ]; then
    cp -r docs release/
fi

# Копируем README.md
if [ -f "README.md" ]; then
    cp README.md release/
fi

# Копируем файл лицензии (если есть)
if [ -f "LICENSE" ]; then
    cp LICENSE release/
fi

# Создаем ZIP-архив
zip -r release-package.zip release/

echo "Релиз подготовлен: release-package.zip"

# Выводим информацию о релизе
echo "Информация о релизе:"
echo "Версия: $(grep versionName app/build.gradle.kts | awk -F'"' '{print $2}')"
echo "Код версии: $(grep versionCode app/build.gradle.kts | awk -F'=' '{print $2}' | tr -d ' ')"
echo "Дата сборки: $(date)"

# Удаляем временную директорию
rm -rf release

exit 0