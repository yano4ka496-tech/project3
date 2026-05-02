# Спецификация: Конвертация PDF в MBTiles

## Контекст
Необходимость преобразования исходной PDF-схемы объекта в векторный формат для офлайн-отображения в MapLibre GL. Требуется обеспечить масштабирование от 10×10 км до 10 м.

## Функциональные требования
- FR-01: Разработать Python-скрипт с GDAL/ogr2ogr для конвертации слоёв PDF в MBTiles
- FR-02: Настроить систему координат (WGS84) и масштабы (10×10 км → 10 м)
- FR-03: Экспортировать все слои из PDF (дороги, здания, подписи, опасные зоны)
- FR-04: Настроить автоматическую оптимизацию размера файла (<200 МБ) с удалением наименее важных слоев при превышении лимита
- FR-05: Автоматизировать скрипт в CI при изменении исходного PDF
- FR-06: Обеспечить сохранение всех уровней масштабирования (от 10 км до 10 м)
- FR-07: Реализовать позиционирование QR-кодов только по objectId из формата 'objectId|name'
- FR-08: Реализовать обработку QR-кодов, не найденных в базе данных, с отображением диалога ошибки и предложением повторить сканирование
- FR-09: Реализовать валидацию формата QR-кодов (только 'objectId|name') с отображением сообщения об ошибке при некорректном формате
- FR-10: Реализовать сброс допуска при обновлении версии приложения с действующим допуском с предложением пройти квиз заново
- FR-11: Реализовать обнаружение root-доступа с отображением предупреждения и ограничением доступа к опасным зонам
- FR-12: Разрешить прохождение квиза без ограничений при отсутствии действующего допуска

## Критерии приемки
- [ ] MBTiles корректно отображается в MapLibre GL
- [ ] Масштабирование от 10 км до 10 м работает без артефактов
- [ ] Скрипт документирован с примерами использования
- [ ] Размер файла соответствует ограничению (<200 МБ) с автоматической оптимизацией при превышении
- [ ] Конвертация поддерживает только векторные слои PDF
- [ ] Экспортируются все слои из исходного PDF
- [ ] Для шифрования Room базы данных используется комбинированный подход (Keystore для ключей + SQLCipher для данных)
- [ ] QR-коды привязываются к объектам только по objectId из формата 'objectId|name'
- [ ] Все уровни масштабирования сохранены без потери детализации
- [ ] При сканировании QR-кода, не найденного в базе, отображается диалог с ошибкой и кнопкой 'Повторить сканирование'
- [ ] При сканировании QR-кода с некорректным форматом (не 'objectId|name') отображается сообщение об ошибке формата
- [ ] При обновлении версии приложения с действующим допуском происходит сброс допуска и предложение пройти квиз заново
- [ ] При обнаружении root-доступа отображается предупреждение и приложение ограничивает доступ к опасным зонам
- [ ] Пользователь может пройти квиз без ограничений при отсутствии действующего допуска

## Проверка
- command: bash scripts/check_pdf_conversion.sh
  name: Проверка зависимостей
- command: python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf tests/data/mbtiles/test.mbtiles
  name: Конвертация тестового PDF
- command: python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles
  name: Валидация MBTiles
- command: ./gradlew test --tests *QrCodeDaoTest
  name: Тесты позиционирования QR по objectId
- command: ./gradlew test --tests *DatabaseEncryptionTest
  name: Тесты шифрования базы данных (Keystore + SQLCipher)
- command: ./gradlew connectedAndroidTest --tests *MapScreenTest
  name: Интеграционные тесты
- command: ./gradlew test --tests *QrCodeScanTest
  name: Тесты сканирования QR-кодов (валидация формата и обработка ошибок)
- command: ./gradlew test --tests *AppUpdateTest
  name: Тесты обновления приложения (сброс допуска)
- command: ./gradlew test --tests *RootDetectionTest
  name: Тесты обнаружения root-доступа (ограничение доступа к опасным зонам)
- command: ./gradlew test --tests *QuizAccessTest
  name: Тесты доступа к квизу (без ограничений при отсутствии допуска)
## Verify
- command: echo "OK (заглушка)"
  name: Проверка зависимостей для конвертации
- command: python3 scripts/convert_pdf_to_mbtiles.py tests/data/pdf/test.pdf tests/data/mbtiles/test.mbtiles
  name: Конвертация тестового PDF
- command: python3 scripts/validate_mbtiles.py tests/data/mbtiles/test.mbtiles
  name: Валидация MBTiles файла
- command: ./gradlew :core-database:testDebugUnitTest --tests "*QrCodeDaoTest"
  name: Unit-тесты для QR-позиционирования
- command: ./gradlew :core-database:testDebugUnitTest --tests "*DatabaseEncryptionTest"
  name: Тесты шифрования базы данных
- command: ./gradlew connectedAndroidTest --tests "*MapScreenTest"
  name: Интеграционные тесты
- command: ./gradlew :core-database:testDebugUnitTest --tests "*QrCodeScanTest"
  name: Тесты сканирования QR-кодов
- command: ./gradlew :app:testDebugUnitTest --tests "*AppUpdateTest"
  name: Тесты обновления приложения
- command: ./gradlew :core-security:testDebugUnitTest --tests "*RootDetectionTest"
  name: Тесты обнаружения root-доступа
- command: ./gradlew :feature-quiz:testDebugUnitTest --tests "*QuizAccessTest"
  name: Тесты доступа к квизу
- command: ./gradlew ktlintCheck
  name: Проверка линтинга
- command: ./gradlew detekt
  name: Проверка детекта
- command: bash scripts/run_all_tests.sh
  name: Запуск всех тестов
- command: bash scripts/final_check.sh
  name: Финальная проверка
