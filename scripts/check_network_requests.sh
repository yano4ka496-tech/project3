#!/bin/bash

# Скрипт для проверки отсутствия сетевых запросов в приложении
# Проверяет наличие запрещенных сетевых библиотек и конфигураций

echo "Проверка отсутствия сетевых запросов..."

# Проверяем наличие запрещенных сетевых библиотек в зависимостях
echo "1. Проверка зависимостей..."

# Проверяем наличие Retrofit
if grep -r "retrofit" app/build.gradle.kts core-*/build.gradle.kts feature-*/build.gradle.kts; then
    echo "Ошибка: Обнаружена библиотека Retrofit, которая запрещена в офлайн-приложении"
    exit 1
fi

# Проверяем наличие OkHttp
if grep -r "okhttp" app/build.gradle.kts core-*/build.gradle.kts feature-*/build.gradle.kts; then
    echo "Ошибка: Обнаружена библиотека OkHttp, которая запрещена в офлайн-приложении"
    exit 1
fi

# Проверяем наличие других сетевых библиотек
if grep -r "androidx.lifecycle:lifecycle-runtime" app/build.gradle.kts core-*/build.gradle.kts feature-*/build.gradle.kts; then
    echo "Ошибка: Обнаружена библиотека lifecycle-runtime, которая может выполнять сетевые запросы"
    exit 1
fi

echo "✓ Запрещенные сетевые библиотеки не обнаружены"

# Проверяем наличие NetworkSecurityConfig
echo "2. Проверка NetworkSecurityConfig..."

if [ -f "app/src/main/res/xml/network_security_config.xml" ]; then
    echo "Обнаружен NetworkSecurityConfig"
    
    # Проверяем, что в конфигурации нет разрешений на сетевые запросы
    if grep -q "cleartextTrafficPermitted" app/src/main/res/xml/network_security_config.xml; then
        echo "Ошибка: В NetworkSecurityConfig разрешен cleartext трафик"
        exit 1
    fi
    
    if grep -q "domain" app/src/main/res/xml/network_security_config.xml; then
        echo "Ошибка: В NetworkSecurityConfig разрешены домены"
        exit 1
    fi
    
    echo "✓ NetworkSecurityConfig настроен правильно"
else
    echo "Предупреждение: NetworkSecurityConfig не найден"
fi

# Проверяем наличие разрешений на сетевые запросы в манифесте
echo "3. Проверка разрешений в манифесте..."

if grep -q "INTERNET" app/src/main/AndroidManifest.xml; then
    echo "Ошибка: В манифесте разрешен доступ в интернет"
    exit 1
fi

if grep -q "ACCESS_NETWORK_STATE" app/src/main/AndroidManifest.xml; then
    echo "Ошибка: В манифесте разрешен доступ к состоянию сети"
    exit 1
fi

echo "✓ Запрещенные разрешения на сетевые запросы не обнаружены"

# Проверяем исходный код на наличие сетевых вызовов
echo "4. Проверка исходного кода..."

# Ищем сетевые вызовы в коде
if grep -r "HttpURLConnection\|OkHttpClient\|Retrofit\|WebSocket\|Socket" app/src/main/java/ core-*/src/main/java/ feature-*/src/main/java/ | grep -v "test" | grep -v "androidx.test"; then
    echo "Ошибка: Обнаружены сетевые вызовы в исходном коде"
    exit 1
fi

# Ищем сетевые вызовы через Retrofit
if grep -r "enqueue\|execute\|call\|request" app/src/main/java/ core-*/src/main/java/ feature-*/src/main/java/ | grep -v "test" | grep -v "androidx.test" | grep -v "enqueue" | grep -v "execute" | grep -v "call" | grep -v "request"; then
    echo "Ошибка: Обнаружены сетевые вызовы в исходном коде"
    exit 1
fi

echo "✓ Сетевые вызовы в исходном коде не обнаружены"

echo "Проверка отсутствия сетевых запросов пройдена успешно!"
exit 0