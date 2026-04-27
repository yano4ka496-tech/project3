#!/bin/bash

# Скрипт для проверки механизма обнаружения root-доступа

echo "Проверка механизма обнаружения root-доступа..."

# Проверка наличия класса RootDetector
if [ ! -f "core-security/src/main/java/com/safeplant/core/security/RootDetector.kt" ]; then
    echo "Ошибка: не найден класс RootDetector в core-security/src/main/java/com/safeplant/core/security/RootDetector.kt"
    exit 1
fi

# Проверка наличия метода isRooted
if ! grep -q "fun isRooted()" "core-security/src/main/java/com/safeplant/core/security/RootDetector.kt"; then
    echo "Ошибка: не найден метод isRooted() в классе RootDetector"
    exit 1
fi

# Проверка наличия метода showRootWarningDialog
if ! grep -q "fun showRootWarningDialog()" "core-security/src/main/java/com/safeplant/core/security/RootDetector.kt"; then
    echo "Ошибка: не найден метод showRootWarningDialog() в классе RootDetector"
    exit 1
fi

# Проверка использования RootBeer
if ! grep -q "RootBeer" "core-security/src/main/java/com/safeplant/core/security/RootDetector.kt"; then
    echo "Ошибка: не используется RootBeer для обнаружения root"
    exit 1
fi

# Проверка дополнительных проверок root
if ! grep -q "/system/app/Superuser.apk" "core-security/src/main/java/com/safeplant/core/security/RootDetector.kt"; then
    echo "Ошибка: не реализованы дополнительные проверки путей к su-файлам"
    exit 1
fi

echo "Проверка механизма обнаружения root-доступа пройдена успешно!"
exit 0