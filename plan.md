## Verify
- name: Проверка сборки приложения
  command: ./gradlew assembleDebug
- name: Проверка навигации между экранами
  command: ./gradlew connectedAndroidTest --tests "com.safeplant.MainActivityTest"
- name: Проверка deep links
  command: adb shell am start -W -a android.intent.action.VIEW -d "safeplant://map?zoneId=123" com.safeplant
- name: Проверка линтинга
  command: ./gradlew ktlintCheck
- name: Проверка детекта
  command: ./gradlew detekt