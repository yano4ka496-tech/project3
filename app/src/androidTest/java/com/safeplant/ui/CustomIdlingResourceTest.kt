package com.safeplant.ui

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Тест для проверки работы CustomIdlingResource
 * Проверяет корректность работы IdlingResource для ожидания загрузки карты
 */
@RunWith(AndroidJUnit4::class)
class CustomIdlingResourceTest {

    private lateinit var idlingResource: CustomIdlingResource

    @Before
    fun setup() {
        // Регистрируем IdlingResource
        idlingResource = CustomIdlingResource.createMapLoadingIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        // Отменяем регистрацию IdlingResource
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    /**
     * Тест: проверка состояния IdlingResource
     * Проверяет, что IdlingResource корректно переключается между состояниями
     */
    @Test
    fun testIdlingResourceState() {
        // Проверяем начальное состояние (простаивает)
        assert(idlingResource.isIdleNow())

        // Устанавливаем состояние "занято"
        idlingResource.setIdleState(false)
        assert(!idlingResource.isIdleNow())

        // Устанавливаем состояние "простаивает"
        idlingResource.setIdleState(true)
        assert(idlingResource.isIdleNow())
    }

    /**
     * Тест: проверка обратного вызова при переходе в состояние простаивания
     * Проверяет, что при переходе в состояние простаивания вызывается обратный вызов
     */
    @Test
    fun testIdlingResourceCallback() {
        // Создаем IdlingResource с обратным вызовом
        var callbackCalled = false
        val testIdlingResource = CustomIdlingResource("TEST_RESOURCE")
        testIdlingResource.registerIdleTransitionCallback {
            callbackCalled = true
        }

        // Устанавливаем состояние "занято"
        testIdlingResource.setIdleState(false)
        assert(!callbackCalled)

        // Устанавливаем состояние "простаивает" - должен вызоваться обратный вызов
        testIdlingResource.setIdleState(true)
        assert(callbackCalled)
    }
}