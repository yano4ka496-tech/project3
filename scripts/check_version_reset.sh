#!/bin/bash
echo "Проверка сброса допуска при обновлении версии"
echo "=============================================="
echo "Проверка наличия тестов для ProfileViewModel..."
if [ -f "feature-profile/src/test/java/com/safeplant/feature/profile/ProfileViewModelTest.kt" ]; then
    echo "Тесты для ProfileViewModel найдены"
    ./gradlew :feature-profile:test --tests "*ProfileViewModelTest*"
else
    echo "Тесты для ProfileViewModel не найдены. Создание заглушки..."
    mkdir -p feature-profile/src/test/java/com/safeplant/feature/profile
    cat > feature-profile/src/test/java/com/safeplant/feature/profile/ProfileViewModelTest.kt << 'EOF'
package com.safeplant.feature.profile

import org.junit.Assert.*
import org.junit.Test

class ProfileViewModelTest {
    @Test
    fun testVersionReset() {
        // TODO: Реализовать тесты для проверки сброса версии
        // 1. Создать тестовый контекст
        // 2. Имитировать обновление версии
        // 3. Проверить, что допуски сброшены
        // 4. Проверить, что новая версия сохранена
    }
}
EOF
    echo "Заглушка для тестов создана"
fi

echo "Проверка сборки feature-profile модуля..."
./gradlew :feature-profile:build

echo "Проверка покрытия кода..."
./gradlew :feature-profile:jacocoTestReport

if [ -f "feature-profile/build/reports/jacoco/test/html/index.html" ]; then
    echo "Отчет о покрытии кода сгенерирован: feature-profile/build/reports/jacoco/test/html/index.html"
else
    echo "Отчет о покрытии кода не сгенерирован"
fi

echo "Проверка завершена"