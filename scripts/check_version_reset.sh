#!/bin/bash

# Скрипт для проверки механизма сброса допуска при обновлении версии

echo "Проверка механизма сброса допуска при обновлении версии..."

# Проверка наличия класса ProfileRepository
if [ ! -f "feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt" ]; then
    echo "Ошибка: не найден класс ProfileRepository в feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt"
    exit 1
fi

# Проверка наличия метода checkVersionReset
if ! grep -q "fun checkVersionReset()" "feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt"; then
    echo "Ошибка: не найден метод checkVersionReset() в классе ProfileRepository"
    exit 1
fi

# Проверка использования PackageManager
if ! grep -q "PackageManager" "feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt"; then
    echo "Ошибка: не используется PackageManager для проверки версии"
    exit 1
fi

# Проверка сброса допуска
if ! grep -q "resetAccessPass" "feature-profile/src/main/java/com/safeplant/feature/profile/ProfileRepository.kt"; then
    echo "Ошибка: не реализован сброс допуска при обновлении версии"
    exit 1
fi

# Проверка тестов для VersionReset
if [ ! -f "feature-profile/src/test/java/com/safeplant/feature/profile/VersionResetTest.kt" ]; then
    echo "Ошибка: не найдены тесты для VersionReset в feature-profile/src/test/java/com/safeplant/feature/profile/VersionResetTest.kt"
    exit 1
fi

echo "Проверка механизма сброса допуска при обновлении версии пройдена успешно!"
exit 0