#!/bin/bash

# Скрипт для проверки механизма шифрования Room БД

echo "Проверка механизма шифрования Room БД..."

# Проверка наличия класса DatabaseKeyManager
if [ ! -f "core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt" ]; then
    echo "Ошибка: не найден класс DatabaseKeyManager в core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt"
    exit 1
fi

# Проверка использования Android Keystore
if ! grep -q "AndroidKeyStore" "core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt"; then
    echo "Ошибка: не используется Android Keystore для управления ключами"
    exit 1
fi

# Проверка использования SQLCipher
if ! grep -q "SQLCipher" "core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt"; then
    echo "Ошибка: не используется SQLCipher для шифрования БД"
    exit 1
fi

# Проверка наличия метода getKey
if ! grep -q "fun getKey()" "core-database/src/main/java/com/safeplant/core/database/DatabaseKeyManager.kt"; then
    echo "Ошибка: не найден метод getKey() в классе DatabaseKeyManager"
    exit 1
fi

# Проверка тестов для DatabaseEncryption
if [ ! -f "core-database/src/test/java/com/safeplant/core/database/DatabaseEncryptionTest.kt" ]; then
    echo "Ошибка: не найдены тесты для DatabaseEncryption в core-database/src/test/java/com/safeplant/core/database/DatabaseEncryptionTest.kt"
    exit 1
fi

echo "Проверка механизма шифрования Room БД пройдена успешно!"
exit 0