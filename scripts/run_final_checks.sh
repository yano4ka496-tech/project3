#!/bin/bash

# Скрипт для финальной проверки SafePlant

echo "=== Финальная проверка SafePlant ==="

# Выполняем все проверки
bash scripts/run_all_checks.sh

if [ $? -eq 0 ]; then
    echo "Финальная проверка пройдена успешно"
    exit 0
else
    echo "Финальная проверка не пройдена"
    exit 1
fi