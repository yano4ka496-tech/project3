package com.safeplant.feature.map

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.mapping.layers.HazardZone
import com.safeplant.core.mapping.layers.RouteModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Unit-тесты для RouteAccessManager
 * Проверка доступа к маршрутам без опасных зон, с опасными зонами при наличии и отсутствии допуска, проверка алгоритма пересечения маршрутов с опасными зонами
 */
class RouteAccessManagerTest {
    
    private lateinit var routeAccessManager: RouteAccessManager
    private val accessPassDao: AccessPassDao = mockk()
    
    @Before
    fun setUp() {
        routeAccessManager = RouteAccessManager(accessPassDao)
    }
    
    /**
     * Тест проверки доступа к маршруту без опасных зон
     */
    @Test
    fun `checkRouteAccess should allow access to route without hazards`() = runBlocking {
        // Создаем тестовый маршрут без опасных зон
        val route = RouteModel(
            id = "safe_route",
            name = "Безопасный маршрут",
            description = "Маршрут без опасных зон",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Создаем пустой список опасных зон
        val hazardZones = emptyList<HazardZone>()
        
        // Настраиваем мок AccessPassDao для возврата false (нет допусков, но они не нужны)
        every { accessPassDao.hasValidAccessPass(any(), any()) } returns false
        
        // Проверяем доступ
        val result = routeAccessManager.checkRouteAccess(
            route = route,
            hazardZones = hazardZones,
            userId = "test_user"
        )
        
        // Должен быть разрешен доступ
        assertEquals(RouteAccessManager.RouteAccessResult.Allowed, result)
    }
    
    /**
     * Тест проверки доступа к маршруту с опасными зонами при наличии допуска
     */
    @Test
    fun `checkRouteAccess should allow access to route with hazards when pass exists`() = runBlocking {
        // Создаем тестовый маршрут с опасными зонами
        val route = RouteModel(
            id = "hazardous_route",
            name = "Опасный маршрут",
            description = "Маршрут через опасную зону",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону
        val hazardZone = HazardZone(
            id = "hazard1",
            name = "Опасная зона 1",
            description = "Первая опасная зона",
            centerLat = 55.7559,
            centerLon = 37.6174,
            coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Настраиваем мок AccessPassDao для возврата true (есть действующий допуск)
        every { accessPassDao.hasValidAccessPass("test_user", any()) } returns true
        
        // Проверяем доступ
        val result = routeAccessManager.checkRouteAccess(
            route = route,
            hazardZones = hazardZones,
            userId = "test_user"
        )
        
        // Должен быть разрешен доступ
        assertEquals(RouteAccessManager.RouteAccessResult.Allowed, result)
    }
    
    /**
     * Тест проверки доступа к маршруту с опасными зонами при отсутствии допуска
     */
    @Test
    fun `checkRouteAccess should deny access to route with hazards when pass does not exist`() = runBlocking {
        // Создаем тестовый маршрут с опасными зонами
        val route = RouteModel(
            id = "hazardous_route",
            name = "Опасный маршрут",
            description = "Маршрут через опасную зону",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону
        val hazardZone = HazardZone(
            id = "hazard1",
            name = "Опасная зона 1",
            description = "Первая опасная зона",
            centerLat = 55.7559,
            centerLon = 37.6174,
            coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Настраиваем мок AccessPassDao для возврата false (нет действующего допуска)
        every { accessPassDao.hasValidAccessPass("test_user", any()) } returns false
        
        // Проверяем доступ
        val result = routeAccessManager.checkRouteAccess(
            route = route,
            hazardZones = hazardZones,
            userId = "test_user"
        )
        
        // Должен быть запрещен доступ
        assertEquals(RouteAccessManager.RouteAccessResult.Denied, result)
    }
    
    /**
     * Тест проверки алгоритма пересечения маршрутов с опасными зонами
     */
    @Test
    fun `checkRouteIntersection should detect route intersection with hazard zone`() {
        // Создаем тестовый маршрут, проходящий через опасную зону
        val route = RouteModel(
            id = "intersecting_route",
            name = "Пересекающий маршрут",
            description = "Маршрут, проходящий через опасную зону",
            coordinates = listOf(37.6173, 55.7558, 37.6174, 55.7559, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону в центре маршрута
        val hazardZone = HazardZone(
            id = "hazard1",
            name = "Опасная зона 1",
            description = "Первая опасная зона",
            centerLat = 55.7559,
            centerLon = 37.6174,
            coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Проверяем пересечение
        val intersects = routeAccessManager.checkRouteIntersection(route, hazardZones)
        
        // Должно быть пересечение
        assertTrue(intersects)
    }
    
    /**
     * Тест проверки алгоритма пересечения маршрутов с опасными зонами (без пересечения)
     */
    @Test
    fun `checkRouteIntersection should not detect route intersection with distant hazard zone`() {
        // Создаем тестовый маршрут, не проходящий через опасную зону
        val route = RouteModel(
            id = "safe_route",
            name = "Безопасный маршрут",
            description = "Маршрут без пересечения с опасными зонами",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону далеко от маршрута
        val hazardZone = HazardZone(
            id = "distant_hazard",
            name = "Далекая опасная зона",
            description = "Опасная зона далеко от маршрута",
            centerLat = 55.7600,
            centerLon = 37.6200,
            coordinates = listOf(listOf(37.6200, 55.7600, 37.6210, 55.7610))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Проверяем пересечение
        val intersects = routeAccessManager.checkRouteIntersection(route, hazardZones)
        
        // Не должно быть пересечения
        assertFalse(intersects)
    }
    
    /**
     * Тест проверки алгоритма пересечения маршрутов с опасными зонами (пороговое расстояние)
     */
    @Test
    fun `checkRouteIntersection should detect route intersection at threshold distance`() {
        // Создаем тестовый маршрут, проходящий на грани опасной зоны
        val route = RouteModel(
            id = "threshold_route",
            name = "Маршрут на грани",
            description = "Маршрут, проходящий на грани опасной зоны",
            coordinates = listOf(37.6173, 55.7558, 37.6174, 55.7559, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону на расстоянии ровно 10 метров (порог)
        val hazardZone = HazardZone(
            id = "threshold_hazard",
            name = "Опасная зона на грани",
            description = "Опасная зона на грани маршрута",
            centerLat = 55.7559,
            centerLon = 37.6174,
            coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Проверяем пересечение
        val intersects = routeAccessManager.checkRouteIntersection(route, hazardZones)
        
        // Должно быть пересечение (пороговое расстояние)
        assertTrue(intersects)
    }
    
    /**
     * Тест проверки алгоритма пересечения маршрутов с опасными зонами (безопасное расстояние)
     */
    @Test
    fun `checkRouteIntersection should not detect route intersection when safe distance`() {
        // Создаем тестовый маршрут, проходящий рядом с опасной зоной, но на безопасном расстоянии
        val route = RouteModel(
            id = "safe_route",
            name = "Безопасный маршрут",
            description = "Маршрут на безопасном расстоянии от опасной зоны",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Создаем опасную зону на расстоянии 11 метров (безопасно)
        val hazardZone = HazardZone(
            id = "safe_hazard",
            name = "Далекая опасная зона",
            description = "Опасная зона на безопасном расстоянии",
            centerLat = 55.7559,
            centerLon = 37.6174,
            coordinates = listOf(listOf(37.6173, 55.7558, 37.6175, 55.7560))
        )
        
        val hazardZones = listOf(hazardZone)
        
        // Проверяем пересечение
        val intersects = routeAccessManager.checkRouteIntersection(route, hazardZones)
        
        // Не должно быть пересечения (безопасное расстояние)
        assertFalse(intersects)
    }
}