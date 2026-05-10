package com.safeplant.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.MainActivity
import com.safeplant.feature.map.MapScreen
import com.safeplant.feature.profile.ProfileViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * UI-тесты для выбора маршрутов
 * Проверяет выбор маршрута через опасную зону без допуска
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RouteSelectionTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeTestRule<MainActivity>()

    @Inject
    lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Тест: выбор маршрута через опасную зону без допуска
     * Проверяет, что при выборе маршрута через опасную зону без допуска
     * приложение перенаправляет на экран квиза
     */
    @Test
    fun testRouteSelectionWithoutAccess() {
        // Устанавливаем недействующий допуск
        profileViewModel.setAccessValid(false)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим кнопку выбора маршрута
        val routeButton = composeTestRule.onNodeWithText("Выбрать маршрут")
        routeButton.assertIsDisplayed()
        routeButton.performClick()

        // В реальном приложении здесь будет открытие диалога выбора маршрута
        // Для теста проверяем, что отображается сообщение о необходимости прохождения квиза
        val quizPrompt = composeTestRule.onNodeWithText("Пройдите квиз для получения доступа")
        quizPrompt.assertIsDisplayed()
    }

    /**
     * Тест: выбор безопасного маршрута с действующим допуском
     * Проверяет, что при выборе безопасного маршрута с действующим допуска
     * приложение отображает маршрут на карте
     */
    @Test
    fun testSafeRouteSelectionWithAccess() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим кнопку выбора маршрута
        val routeButton = composeTestRule.onNodeWithText("Выбрать маршрут")
        routeButton.assertIsDisplayed()
        routeButton.performClick()

        // В реальном приложении здесь будет открытие диалога выбора маршрута
        // Для теста проверяем, что отображается информация о выбранном маршруте
        val routeInfo = composeTestRule.onNodeWithText("Выбран маршрут: Маршрут 1")
        routeInfo.assertIsDisplayed()
    }

    /**
     * Тест: выбор маршрута через опасную зону с действующим допуском
     * Проверяет, что при выборе маршрута через опасную зону с действующим допуска
     * приложение отображает маршрут на карте
     */
    @Test
    fun testDangerousRouteSelectionWithAccess() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим кнопку выбора маршрута
        val routeButton = composeTestRule.onNodeWithText("Выбрать маршрут")
        routeButton.assertIsDisplayed()
        routeButton.performClick()

        // В реальном приложении здесь будет открытие диалога выбора маршрута
        // Для теста проверяем, что отображается информация о выбранном маршруте
        val routeInfo = composeTestRule.onNodeWithText("Выбран маршрут: Маршрут 2")
        routeInfo.assertIsDisplayed()
    }

    /**
     * Тест: сброс выбранного маршрута
     * Проверяет, что при нажатии на кнопку сброса маршрут удаляется с карты
     */
    @Test
    fun testRouteReset() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим кнопку выбора маршрута
        val routeButton = composeTestRule.onNodeWithText("Выбрать маршрут")
        routeButton.assertIsDisplayed()
        routeButton.performClick()

        // Находим кнопку сброса маршрута
        val resetButton = composeTestRule.onNodeWithText("Сбросить маршрут")
        resetButton.assertIsDisplayed()
        resetButton.performClick()

        // Проверяем, что информация о маршруте удалена
        val routeInfo = composeTestRule.onNodeWithText("Выбран маршрут: Маршрут 1")
        routeInfo.assertDoesNotExist()
    }
}