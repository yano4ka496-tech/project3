package tests.integration

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.safeplant.MainActivity
import com.safeplant.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Интеграционный тест для полного сценария использования приложения
 * Проверяет весь путь пользователя: от запуска до прохождения квиза и использования карты
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class FullFlowTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    /**
     * Тест полного сценария: запуск приложения -> сканирование QR -> выбор маршрута -> прохождение квиза -> доступ к карте
     */
    @Test
    fun `full application flow should work correctly`() {
        // Шаг 1: Запуск приложения
        // Проверяем, что отображается экран карты
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        
        // Шаг 2: Сканирование QR-кода для позиционирования
        // Нажимаем кнопку сканирования QR
        onView(withId(R.id.btn_open_qr_scanner)).perform(click())
        
        // Проверяем, что отображается экран сканера QR
        onView(withId(R.id.qr_scanner_view)).check(matches(isDisplayed()))
        
        // Симулируем успешное сканирование QR-кода
        // В реальном приложении это делается через QRScannerViewModel
        onView(withId(R.id.btn_scan)).perform(click())
        
        // Проверяем, что отображается диалог успешного сканирования
        onView(withId(R.id.dialog_qr_success)).check(matches(isDisplayed()))
        
        // Подтверждаем сканирование
        onView(withId(R.id.btn_confirm_scan)).perform(click())
        
        // Проверяем, что карта обновилась с позицией пользователя
        onView(withId(R.id.user_position_marker)).check(matches(isDisplayed()))
        
        // Шаг 3: Выбор маршрута через опасную зону
        // Нажимаем кнопку выбора маршрута
        onView(withId(R.id.btn_select_route)).perform(click())
        
        // Проверяем, что отображается диалог выбора маршрута
        onView(withId(R.id.dialog_route_selection)).check(matches(isDisplayed()))
        
        // Выбираем маршрут через опасную зону
        onView(withId(R.id.route_through_hazard)).perform(click())
        
        // Проверяем, что отображается предупреждение о опасной зоне
        onView(withId(R.id.hazard_zone_warning)).check(matches(isDisplayed()))
        
        // Подтверждаем выбор маршрута
        onView(withId(R.id.btn_confirm_route)).perform(click())
        
        // Проверяем, что отображается диалог прохождения квиза
        onView(withId(R.id.dialog_quiz_required)).check(matches(isDisplayed()))
        
        // Шаг 4: Прохождение квиза
        // Нажимаем кнопку начала квиза
        onView(withId(R.id.btn_start_quiz)).perform(click())
        
        // Проверяем, что отображается экран квиза
        onView(withId(R.id.quiz_screen)).check(matches(isDisplayed()))
        
        // Проходим квиз (выбираем ответы)
        onView(withId(R.id.option_1)).perform(click())
        onView(withId(R.id.btn_next)).perform(click())
        
        onView(withId(R.id.option_2)).perform(click())
        onView(withId(R.id.btn_next)).perform(click())
        
        onView(withId(R.id.option_3)).perform(click())
        onView(withId(R.id.btn_next)).perform(click())
        
        onView(withId(R.id.option_4)).perform(click())
        onView(withId(R.id.btn_finish)).perform(click())
        
        // Проверяем, что отображается результат квиза
        onView(withId(R.id.quiz_result)).check(matches(isDisplayed()))
        
        // Подтверждаем результат
        onView(withId(R.id.btn_confirm_result)).perform(click())
        
        // Шаг 5: Доступ к карте с выбранным маршрутом
        // Проверяем, что отображается карта с выбранным маршрутом
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        onView(withId(R.tv_selected_route)).check(matches(isDisplayed()))
        
        // Проверяем, что отображается маркер позиции пользователя
        onView(withId(R.id.user_position_marker)).check(matches(isDisplayed()))
        
        // Проверяем, что отображается слой опасных зон
        onView(withId(R.id.hazard_zones_layer)).check(matches(isDisplayed()))
        
        // Проверяем, что отображается слой маршрутов
        onView(withId(R.id.routes_layer)).check(matches(isDisplayed()))
    }
    
    /**
     * Тест сценария: запуск приложения -> сканирование QR -> выбор безопасного маршрута -> доступ к карте без квиза
     */
    @Test
    fun `safe route flow should not require quiz`() {
        // Шаг 1: Запуск приложения
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        
        // Шаг 2: Сканирование QR-кода
        onView(withId(R.id.btn_open_qr_scanner)).perform(click())
        onView(withId(R.id.qr_scanner_view)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_scan)).perform(click())
        onView(withId(R.id.dialog_qr_success)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_confirm_scan)).perform(click())
        onView(withId(R.id.user_position_marker)).check(matches(isDisplayed()))
        
        // Шаг 3: Выбор безопасного маршрута
        onView(withId(R.id.btn_select_route)).perform(click())
        onView(withId(R.id.dialog_route_selection)).check(matches(isDisplayed()))
        onView(withId(R.id.safe_route)).perform(click())
        onView(withId(R.id.route_confirmation)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_confirm_route)).perform(click())
        
        // Проверяем, что диалог квиза не отображается
        onView(withId(R.id.dialog_quiz_required)).check(matches(isNotDisplayed()))
        
        // Проверяем, что отображается карта с выбранным маршрутом
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        onView(withId(R.tv_selected_route)).check(matches(isDisplayed()))
    }
    
    /**
     * Тест сценария: запуск приложения -> сканирование некорректного QR-кода -> ошибка
     */
    @Test
    fun `invalid qr code should show error`() {
        // Шаг 1: Запуск приложения
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        
        // Шаг 2: Попытка сканирования некорректного QR-кода
        onView(withId(R.id.btn_open_qr_scanner)).perform(click())
        onView(withId(R.id.qr_scanner_view)).check(matches(isDisplayed()))
        
        // Симулируем сканирование некорректного QR-кода
        onView(withId(R.id.btn_scan_invalid)).perform(click())
        
        // Проверяем, что отображается диалог ошибки QR-кода
        onView(withId(R.id.dialog_qr_error)).check(matches(isDisplayed()))
        
        // Проверяем, что предложение отсканировать другой QR-код отображается
        onView(withId(R.id.tv_scan_another)).check(matches(isDisplayed()))
        
        // Закрываем диалог
        onView(withId(R.id.btn_close_error)).perform(click())
        
        // Проверяем, что экран сканера QR остается открытым
        onView(withId(R.id.qr_scanner_view)).check(matches(isDisplayed()))
    }
    
    /**
     * Тест сценария: запуск приложения -> обновление версии -> сброс допусков
     */
    @Test
    fun `version update should reset all passes`() {
        // Шаг 1: Запуск приложения
        onView(withId(R.id.map_view)).check(matches(isDisplayed()))
        
        // Шаг 2: Симулируем обновление версии
        onView(withId(R.id.btn_simulate_update)).perform(click())
        
        // Проверяем, что отображается диалог обновления версии
        onView(withId(R.id.dialog_version_update)).check(matches(isDisplayed()))
        
        // Подтверждаем обновление
        onView(withId(R.id.btn_confirm_update)).perform(click())
        
        // Проверяем, что все допуски сброшены
        onView(withId(R.id.tv_access_denied)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_select_route)).check(matches(isNotEnabled()))
        
        // Проверяем, что отображается предложение пройти квиз
        onView(withId(R.id.tv_pass_quiz)).check(matches(isDisplayed()))
    }
    
    /**
     * Вспомогательные методы для проверки состояний элементов
     */
    private fun isNotDisplayed() = matches(isDisplayed().negate())
}