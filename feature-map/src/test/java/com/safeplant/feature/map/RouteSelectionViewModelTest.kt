package com.safeplant.feature.map

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.mapping.layers.HazardZone
import com.safeplant.core.mapping.layers.RouteModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Тесты для RouteSelectionViewModel
 */
class RouteSelectionViewModelTest {
    
    private lateinit var routeAccessManager: RouteAccessManager
    private lateinit var accessPassDao: AccessPassDao
    private lateinit var viewModel: RouteSelectionViewModel
    
    @Before
    fun setup() {
        routeAccessManager = mockk()
        accessPassDao = mockk()
        viewModel = RouteSelectionViewModel(routeAccessManager, accessPassDao)
    }
    
    @Test
    fun testSelectRouteWithValidAccess() = runBlocking {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Маршрут для тестирования",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Настраиваем мок
        every { routeAccessManager.checkRouteAccess(any(), any(), any()) } 
            .returns(RouteAccessManager.RouteAccessResult.Allowed)
        
        // Выбираем маршрут
        viewModel.selectRoute(testRoute)
        
        // Проверяем, что маршрут выбран
        assertEquals(testRoute, viewModel.selectedRoute.value)
        
        // Проверяем результат доступа
        assertEquals(RouteAccessManager.RouteAccessResult.Allowed, viewModel.accessResult.value)
    }
    
    @Test
    fun testSelectRouteWithDeniedAccess() = runBlocking {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Маршрут для тестирования",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Настраиваем мок
        every { routeAccessManager.checkRouteAccess(any(), any(), any()) } 
            .returns(RouteAccessManager.RouteAccessResult.Denied)
        
        // Выбираем маршрут
        viewModel.selectRoute(testRoute)
        
        // Проверяем, что маршрут выбран
        assertEquals(testRoute, viewModel.selectedRoute.value)
        
        // Проверяем результат доступа
        assertEquals(RouteAccessManager.RouteAccessResult.Denied, viewModel.accessResult.value)
    }
    
    @Test
    fun testClearSelection() {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Маршрут для тестирования",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Выбираем маршрут
        viewModel.selectRoute(testRoute)
        
        // Сбрасываем выбор
        viewModel.clearSelection()
        
        // Проверяем, что выбор очищен
        assertNull(viewModel.selectedRoute.value)
        assertNull(viewModel.accessResult.value)
    }
    
    @Test
    fun testHasValidAccessPass() = runBlocking {
        // Настраиваем мок
        every { accessPassDao.hasValidAccessPass(any(), any()) } 
            .returns(true)
        
        // Проверяем наличие действующего допуска
        assertTrue(viewModel.hasValidAccessPass())
    }
    
    @Test
    fun testGetValidAccessPass() = runBlocking {
        // Создаем тестовый допуск
        val testAccessPass = AccessPass(
            userId = "test_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30),
            isValid = true
        )
        
        // Настраиваем мок
        every { accessPassDao.getValidAccessPass(any(), any()) } 
            .returns(testAccessPass)
        
        // Получаем действующий допуск
        val accessPass = viewModel.getValidAccessPass()
        
        // Проверяем, что допуск получен
        assertEquals(testAccessPass, accessPass)
    }
    
    @Test
    fun testFormatRemainingTimeWithValidPass() = runBlocking {
        // Создаем тестовый допуск с оставшимся временем 1 день
        val testAccessPass = AccessPass(
            userId = "test_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1),
            isValid = true
        )
        
        // Настраиваем мок
        every { accessPassDao.getValidAccessPass(any(), any()) } 
            .returns(testAccessPass)
        
        // Форматируем оставшееся время
        val formattedTime = viewModel.formatRemainingTime()
        
        // Проверяем формат
        assertTrue(formattedTime.contains("дн."))
    }
    
    @Test
    fun testFormatRemainingTimeWithExpiredPass() = runBlocking {
        // Создаем тестовый просроченный допуск
        val testAccessPass = AccessPass(
            userId = "test_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
            isValid = true
        )
        
        // Настраиваем мок
        every { accessPassDao.getValidAccessPass(any(), any()) } 
            .returns(testAccessPass)
        
        // Форматируем оставшееся время
        val formattedTime = viewModel.formatRemainingTime()
        
        // Проверяем, что срок истек
        assertEquals("Допуск истек", formattedTime)
    }
    
    @Test
    fun testSetHazardZones() {
        // Создаем тестовые опасные зоны
        val testZones = listOf(
            HazardZone(
                id = "zone1",
                name = "Опасная зона 1",
                coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
            )
        )
        
        // Устанавливаем зоны
        viewModel.setHazardZones(testZones)
        
        // Проверяем, что зоны установлены
        assertEquals(testZones, viewModel.hazardZones.value)
    }
    
    @Test
    fun testSetUserId() {
        // Устанавливаем ID пользователя
        viewModel.setUserId("new_user")
        
        // Проверяем, что ID установлен
        assertEquals("new_user", viewModel.userId.value)
    }
}