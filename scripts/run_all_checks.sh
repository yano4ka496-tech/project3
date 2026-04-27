#!/bin/bash

echo "Запуск всех проверок для модуля core-database..."

# Проверка сборки проекта
echo "1. Проверка сборки проекта..."
./gradlew :core-database:build
if [ $? -ne 0 ]; then
    echo "Ошибка: Сборка проекта не прошла"
    exit 1
fi
echo "OK: Сборка проекта прошла успешно"

# Проверка линтера
echo "2. Проверка линтера..."
./gradlew :core-database:detekt
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка линтера не прошла"
    exit 1
fi
echo "OK: Проверка линтера прошла успешно"

# Unit-тесты для DAO
echo "3. Unit-тесты для DAO..."
./gradlew :core-database:test --tests "*DaoTest"
if [ $? -ne 0 ]; then
    echo "Ошибка: Unit-тесты для DAO не прошли"
    exit 1
fi
echo "OK: Unit-тесты для DAO прошли успешно"

# Unit-тесты для репозиториев
echo "4. Unit-тесты для репозиториев..."
./gradlew :core-database:test --tests "*RepositoryTest"
if [ $? -ne 0 ]; then
    echo "Ошибка: Unit-тесты для репозиториев не прошли"
    exit 1
fi
echo "OK: Unit-тесты для репозиториев прошли успешно"

# Интеграционные тесты для Room
echo "5. Интеграционные тесты для Room..."
./gradlew :core-database:test --tests "*EncryptedDatabaseTest"
if [ $? -ne 0 ]; then
    echo "Ошибка: Интеграционные тесты для Room не прошли"
    exit 1
fi
echo "OK: Интеграционные тесты для Room прошли успешно"

# Проверка покрытия тестами
echo "6. Проверка покрытия тестами..."
./gradlew :core-database:jacocoTestReport
if [ $? -ne 0 ]; then
    echo "Ошибка: Проверка покрытия тестами не прошла"
    exit 1
fi
echo "OK: Проверка покрытия тестами прошла успешно"

echo "Все проверки для модуля core-database успешно пройдены!"
exit 0