#!/bin/bash
echo "Проверка сброса допуска при обновлении"
./gradlew :feature-profile:test --tests "*.VersionResetTest"
