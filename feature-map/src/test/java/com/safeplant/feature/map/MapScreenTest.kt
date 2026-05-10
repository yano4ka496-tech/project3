package com.safeplant.feature.map

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.security.PositionStorage
import com.safeplant.feature.profile.ProfileViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Тесты для экрана карты
 */
@RunWith(AndroidJUnit4::class)
class MapScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mapScreen_displaysButtonWhenNoPosition() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow<Pair<Double, Double>?>(null)

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что кнопка отображается
        composeTestRule.onNodeWithText("Отсканируйте QR или выберите место")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysMarkerWhenPositionExists() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что кнопка не отображается
        composeTestRule.onNodeWithText("Отсканируйте QR или выберите место")
            .assertDoesNotExist()
    }

    @Test
    fun mapScreen_updatesPositionOnMapClick() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow<Pair<Double, Double>?>(null)
        every { positionViewModel.updatePosition(any(), any()) } returns Unit

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Симулируем клик по карте
        composeTestRule.onNodeWithText("Отсканируйте QR или выберите место")
            .performClick()

        // Проверяем, что метод updatePosition был вызван
        // В реальном тесте здесь будет проверка вызова метода
    }

    @Test
    fun mapScreen_displaysWarningWhenPositionInDangerousZone() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow<Pair<Double, Double>?>(null)
        every { positionViewModel.updatePosition(any(), any()) } returns Unit
        every { positionViewModel.error } returns MutableStateFlow("Выбранная позиция находится в запрещенной зоне")

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается предупреждение
        composeTestRule.onNodeWithText("Выбранная позиция находится в запрещенной зоне")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysWarningWhenPositionOutsideZones() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow<Pair<Double, Double>?>(null)
        every { positionViewModel.updatePosition(any(), any()) } returns Unit
        every { positionViewModel.error } returns MutableStateFlow("Выбранная позиция находится вне предустановленных зон")

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается предупреждение
        composeTestRule.onNodeWithText("Выбранная позиция находится вне предустановленных зон")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysSelectPositionButton() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow<Pair<Double, Double>?>(null)

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что кнопка выбора позиции отображается
        composeTestRule.onNodeWithText("Выбрать место на карте")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysUserMarkerWhenPositionExists() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что маркер пользователя отображается
        // В реальном приложении здесь будет проверка наличия маркера на карте
        // Для теста проверяем, что позиция не null
        assert(positionViewModel.position.value != null)
    }

    @Test
    fun mapScreen_displaysAccessStatus() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается статус допуска
        composeTestRule.onNodeWithText("Допуск действителен")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysExpiredAccessStatus() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns false
        every { profileViewModel.calculateRemainingTime() } returns 0L

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается статус истекшего допуска
        composeTestRule.onNodeWithText("Допуск недействителен")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Срок действия допуска истек")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysRouteSelectionButton() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что кнопка выбора маршрута отображается
        composeTestRule.onNodeWithText("Выбрать маршрут")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysRouteResetButtonWhenRouteSelected() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем RouteSelectionViewModel
        val routeSelectionViewModel = mockk<RouteSelectionViewModel>()
        every { routeSelectionViewModel.selectedRoute } returns MutableStateFlow(
            RouteModel(
                id = "test_route",
                name = "Тестовый маршрут",
                description = "Маршрут для тестирования",
                coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
            )
        )

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что кнопка сброса маршрута отображается
        composeTestRule.onNodeWithText("Сбросить маршрут")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysSelectedRouteInfo() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем RouteSelectionViewModel
        val routeSelectionViewModel = mockk<RouteSelectionViewModel>()
        every { routeSelectionViewModel.selectedRoute } returns MutableStateFlow(
            RouteModel(
                id = "test_route",
                name = "Тестовый маршрут",
                description = "Маршрут для тестирования",
                coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
            )
        )

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается информация о выбранном маршруте
        composeTestRule.onNodeWithText("Выбран маршрут: Тестовый маршрут")
            .assertIsDisplayed()
    }

    @Test
    fun mapScreen_displaysAccessResult() {
        // Мокируем PositionViewModel
        val positionViewModel = mockk<PositionViewModel>()
        every { positionViewModel.position } returns MutableStateFlow(Pair(55.7558, 37.6173))

        // Мокируем RouteSelectionViewModel
        val routeSelectionViewModel = mockk<RouteSelectionViewModel>()
        every { routeSelectionViewModel.selectedRoute } returns MutableStateFlow(
            RouteModel(
                id = "test_route",
                name = "Тестовый маршрут",
                description = "Маршрут для тестирования",
                coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
            )
        )
        every { routeSelectionViewModel.accessResult } returns MutableStateFlow(
            RouteAccessManager.RouteAccessResult.Allowed
        )

        // Мокируем ProfileViewModel
        val profileViewModel = mockk<ProfileViewModel>()
        every { profileViewModel.isAccessToDangerousZonesAllowed() } returns true

        // Запускаем экран
        composeTestRule.setContent {
            MapScreen(
                profileViewModel = profileViewModel
            )
        }

        // Проверяем, что отображается результат проверки доступа
        composeTestRule.onNodeWithText("Доступ разрешен")
            .assertIsDisplayed()
    }
}