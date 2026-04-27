#!/bin/bash
echo "Проверка валидации QR-кодов"
./gradlew :core-security:test --tests "*.QRValidatorTest"
