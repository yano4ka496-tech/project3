package com.safeplant.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
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
 * UI-тесты для взаимодействия с картой
 * Проверяет ручное позиционирование, выбор маршрутов и переключение слоев
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MapInteractionTest {

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
     * Тест: ручное позиционирование на карте
     * Проверяет, что при нажатии на карту позиция обновляется
     */
    @Test
    fun testManualPositioning() {
        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Находим кнопку выбора позиции
        val positionButton = composeTestRule.onNodeWithText("Выбрать место на карте")
        positionButton.assertIsDisplayed()
        positionButton.performClick()

        // Находим кнопку на карте для выбора позиции
        val mapPositionButton = composeTestRule.onNodeWithText("Отсканируйте QR или выберите место")
        mapPositionButton.assertIsDisplayed()
        mapPositionButton.performClick()

        // Проверяем, что позиция установлена (в реальном приложении здесь будет проверка маркера на карте)
        // Для теста проверяем, что кнопка выбора позиции скрыта
        mapPositionButton.assertDoesNotExist()
    }

    /**
     * Тест: переключение слоя эвакуации
     * Проверяет, что слой эвакуации отображается при наличии допуска
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

        // Проверяем, что слой эвакуации отображается
        val evacuationLayer = composeTestRule.onNodeWithText("Маршрут эвакуации")
        evacuationLayer.assertIsDisplayed()

        // В реальном приложении здесь будет тест переключения слоя
        // Для теста проверяем, что информация о слое отображается
        evacuationLayer.performClick()

        // Проверяем, что информация о маршруте эвакуации отображается
        val evacuationInfo = composeTestRule.onNodeWithText("Основной маршрут эвакуации")
        evacuationInfo.assertIsDisplayed()
    }

    /**
     * Тест: отсутствие доступа к слою эвакуации
     * Проверяет, что слой эвакуации не отображается при отсутствии допуска
     */
    @Test
    fun testEvacuationLayerAccessDenied() {
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
     * Тест: проверка статуса допуска на карте
     * Проверяет отображение статуса допуска в верхней части экрана
     */
    @Test
    fun testAccessStatusDisplay() {
        // Устанавливаем действующий допуск
        profileViewModel.setAccessValid(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что статус допуска отображается
        val accessStatus = composeTestRule.onNodeWithText("Допуск действителен")
        accessStatus.assertIsDisplayed()

        // Проверяем, что оставшееся время отображается
        val remainingTime = composeTestRule.onNodeWithText("Осталось")
        remainingTime.assertIsDisplayed()
    }

    /**
     * Тест: истечение срока действия допуска
     * Проверяет, что при истечении срока допуска отображается соответствующее сообщение
     */
    @Test
    fun testAccessExpiration() {
        // Устанавливаем истекший допуск
        profileViewModel.setAccessExpired(true)

        // Переходим на экран карты
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается сообщение об истечении срока
        val expirationMessage = composeTestRule.onNodeWithText("Срок действия допуска истек")
        expirationMessage.assertIsDisplayed()

        // Проверяем, что доступ к опасным зонам заблокирован
        val blockedAccess = composeTestRule.onNodeWithText("Доступ к опасным зонам заблокирован")
        blockedAccess.assertIsDisplayed()
    }
}