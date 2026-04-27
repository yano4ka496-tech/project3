#!/bin/bash

# Скрипт для проверки навигации в приложении

echo "Проверка навигации..."

# Проверяем наличие класса AppNavigation
if [ -f "core-navigation/src/main/java/com/safeplant/core/navigation/AppNavigation.kt" ]; then
    echo "✓ Класс AppNavigation найден"
else
    echo "✗ Класс AppNavigation не найден"
    exit 1
fi

# Проверяем наличие классов навигационных destinations
if [ -f "core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt" ]; then
    echo "✓ Класс NavigationDestinations найден"
else
    echo "✗ Класс NavigationDestinations не найден"
    exit 1
fi

# Проверяем наличие всех необходимых destinations
required_destinations=("Profile" "Quiz" "Training" "Map" "QR" "ZoneNotFound" "AccessDenied")
for dest in "${required_destinations[@]}"; do
    if grep -q "$dest" "core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt"; then
        echo "✒ Destination $dest найден"
    else
        echo "✗ Destination $dest не найден"
        exit 1
    fi
done

# Проверяем наличие параметров для Map destination
if grep -q "zoneIdParam" "core-navigation/src/main/java/com/safeplant/core/navigation/NavigationDestinations.kt"; then
    echo "✒ Параметр zoneIdParam для Map destination найден"
else
    echo "✗ Параметр zoneIdParam для Map destination не найден"
    exit 1
fi

echo "✓ Навигация проверена успешно"
exit 0