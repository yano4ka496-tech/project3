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
 * UI-тесты для слоя эвакуации
 * Проверяет отображение и взаимодействие со слоем эвакуации
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class EvacuationLayerTest {

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
     * Тест: отображение слоя эвакуации при наличии допуска
     * Проверяет, что слой эвакуации отображается при наличии действующего допуска
     */
    @Test
    fun testEvacuationLayerDisplayWithAccess() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что слой эвакуации отображается
        val evacuationLayer = composeTestRule.onNodeWithText("Маршрут эвакуации")
        evacuationLayer.assertIsDisplayed()

        // Проверяем, что информация о маршруте эвакуации отображается
        val evacuationInfo = composeTestRule.onNodeWithText("Основной маршрут эвакуации")
        evacuationInfo.assertIsDisplayed()
    }

    /**
     * Тест: скрытие слоя эвакуации при отсутствии допуска
     * Проверяет, что слой эвакуации не отображается при отсутствии допуска
     */
    @Test
    fun testEvacuationLayerHiddenWithoutAccess() {
        // Устанавливаем недействующий допуск
        profileViewModel.setAccessValid(false)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что слой эвакуации не отображается
        val evacuationLayer = composeTestRule.onNodeWithText("Маршрут эвакуации")
        evacuationLayer.assertDoesNotExist()
    }

    /**
     * Тест: переключение слоя эвакуации
     * Проверяет, что при нажатии на слой эвакуации отображается подробная информация
     */
    @Test
    fun testEvacuationLayerToggle() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим слой эвакуации
        val evacuationLayer = composeTestRule.onNodeWithText("Маршрут эвакуации")
        evacuationLayer.assertIsDisplayed()
        evacuationLayer.performClick()

        // Проверяем, что отображается подробная информация о маршруте
        val evacuationDetails = composeTestRule.onNodeWithText("Основной маршрут эвакуации из здания")
        evacuationDetails.assertIsDisplayed()
    }

    /**
     * Тест: отображение нескольких маршрутов эвакуации
     * Проверяет, что при наличии нескольких маршрутов эвакуации все они отображаются
     */
    @Test
    fun testMultipleEvacuationRoutes() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается основной маршрут эвакуации
        val mainRoute = composeTestRule.onNodeWithText("Основной маршрут эвакуации")
        mainRoute.assertIsDisplayed()

        // Проверяем, что отображается альтернативный маршрут эвакуации
        val alternativeRoute = composeTestRule.onNodeWithText("Альтернативный маршрут")
        alternativeRoute.assertIsDisplayed()
    }

    /**
     * Тест: отсутствие маршрутов эвакуации
     * Проверяет, что при отсутствии маршрутов эвакуации отображается соответствующее сообщение
     */
    @Test
    fun testNoEvacuationRoutes() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // В реальном приложении здесь будет мокирование отсутствия маршрутов эвакуации
        // Для теста проверяем, что отображается сообщение об отсутствии маршрутов
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается сообщение об отсутствии маршрутов
        val noRoutesMessage = composeTestRule.onNodeWithText("Маршруты эвакуации не найдены")
        noRoutesMessage.assertIsDisplayed()
    }
}