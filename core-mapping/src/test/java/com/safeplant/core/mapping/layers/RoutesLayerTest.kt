package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.MapLayer
import com.safeplant.core.mapping.MapRenderer
import com.safeplant.core.storage.AssetManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Unit-тесты для RoutesLayer
 * Проверка загрузки маршрутов, выбора маршрута, очистки, проверки существования маршрута и видимости слоя
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class RoutesLayerTest {
    
    private lateinit var routesLayer: RoutesLayer
    private val context: Context = mockk()
    private val assetManager: AssetManager = mockk()
    private val mapRenderer: MapRenderer = mockk()
    private val routeLayer: RouteLayer = mockk()
    
    @Before
    fun setUp() {
        // Мокируем AssetManager
        every { assetManager.loadFromAssets(any()) } returns ""
        
        // Создаем RoutesLayer с мокированными зависимостями
        routesLayer = RoutesLayer("test_routes")
        routesLayer.mapRenderer = mapRenderer
        routesLayer.routeLayer = routeLayer
    }
    
    /**
     * Тест загрузки маршрутов из GeoJSON
     */
    @Test
    fun `loadData should load routes from GeoJSON`() {
        // Подготавливаем тестовые данные GeoJSON
        val geoJsonData = """
            {
                "type": "FeatureCollection",
                "features": [
                    {
                        "type": "Feature",
                        "id": "route1",
                        "properties": {
                            "name": "Маршрут 1",
                            "description": "Первый тестовый маршрут"
                        },
                        "geometry": {
                            "type": "LineString",
                            "coordinates": [[37.6173, 55.7558], [37.6175, 55.7560]]
                        }
                    },
                    {
                        "type": "Feature",
                        "id": "route2",
                        "properties": {
                            "name": "Маршрут 2",
                            "description": "Второй тестовый маршрут"
                        },
                        "geometry": {
                            "type": "LineString",
                            "coordinates": [[37.6176, 55.7561], [37.6178, 55.7563]]
                        }
                    }
                ]
            }
        """.trimIndent()
        
        // Настраиваем мок AssetManager для возврата тестовых данных
        every { assetManager.loadFromAssets("geojson/routes.geojson") } returns geoJsonData
        
        // Вызываем метод загрузки
        routesLayer.loadData()
        
        // Проверяем, что маршруты загружены
        assertEquals(2, routesLayer.getRoutes().size)
        assertEquals("route1", routesLayer.getRoutes()[0].id)
        assertEquals("Маршрут 1", routesLayer.getRoutes()[0].name)
        assertEquals("route2", routesLayer.getRoutes()[1].id)
        assertEquals("Маршрут 2", routesLayer.getRoutes()[1].name)
    }
    
    /**
     * Тест выбора маршрута
     */
    @Test
    fun `setSelectedRoute should set selected route and update layer`() {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Описание тестового маршрута",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Вызываем метод выбора маршрута
        routesLayer.setSelectedRoute("test_route")
        
        // Проверяем, что маршрут выбран
        assertEquals("test_route", routesLayer.getSelectedRouteId())
        
        // Проверяем, что слой маршрута обновлен
        verify { routeLayer.setRoute(testRoute) }
    }
    
    /**
     * Тест очистки выбранного маршрута
     */
    @Test
    fun `clearSelectedRoute should clear selected route and update layer`() {
        // Сначала выбираем маршрут
        routesLayer.setSelectedRoute("test_route")
        assertEquals("test_route", routesLayer.getSelectedRouteId())
        
        // Очищаем выбор
        routesLayer.clearSelectedRoute()
        
        // Проверяем, что выбор очищен
        assertNull(routesLayer.getSelectedRouteId())
        
        // Проверяем, что слой маршрута очищен
        verify { routeLayer.setRoute(null) }
    }
    
    /**
     * Тест проверки существования маршрута
     */
    @Test
    fun `hasRoute should return true if route exists`() {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Описание тестового маршрута",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Добавляем маршрут в список
        routesLayer.getRoutes().add(testRoute)
        
        // Проверяем существование маршрута
        assertTrue(routesLayer.hasRoute("test_route"))
        assertFalse(routesLayer.hasRoute("nonexistent_route"))
    }
    
    /**
     * Тест получения маршрута по ID
     */
    @Test
    fun `getRouteById should return route by id`() {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Описание тестового маршрута",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Добавляем маршрут в список
        routesLayer.getRoutes().add(testRoute)
        
        // Проверяем получение маршрута по ID
        assertEquals(testRoute, routesLayer.getRouteById("test_route"))
        assertNull(routesLayer.getRouteById("nonexistent_route"))
    }
    
    /**
     * Тест получения маршрута по имени
     */
    @Test
    fun `getRouteByName should return route by name`() {
        // Создаем тестовый маршрут
        val testRoute = RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Описание тестового маршрута",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        )
        
        // Добавляем маршрут в список
        routesLayer.getRoutes().add(testRoute)
        
        // Проверяем получение маршрута по имени
        assertEquals(testRoute, routesLayer.getRouteByName("Тестовый маршрут"))
        assertNull(routesLayer.getRouteByName("Несуществующий маршрут"))
    }
    
    /**
     * Тест проверки видимости слоя на текущем масштабе
     */
    @Test
    fun `isVisible should return true if layer is visible at current zoom`() {
        // Проверяем видимость при масштабе 15.0 (в пределах диапазона)
        assertTrue(routesLayer.isVisible(15.0))
        
        // Проверяем видимость при минимальном масштабе
        assertTrue(routesLayer.isVisible(12.0))
        
        // Проверяем видимость при максимальном масштабе
        assertTrue(routesLayer.isVisible(20.0))
        
        // Проверяем невидимость при масштабе ниже минимального
        assertFalse(routesLayer.isVisible(11.0))
        
        // Проверяем невидимость при масштабе выше максимального
        assertFalse(routesLayer.isVisible(21.0))
    }
    
    /**
     * Тест очистки ресурсов слоя
     */
    @Test
    fun `cleanup should clear all resources`() {
        // Добавляем тестовые данные
        routesLayer.getRoutes().add(RouteModel(
            id = "test_route",
            name = "Тестовый маршрут",
            description = "Описание тестового маршрута",
            coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
        ))
        
        routesLayer.setSelectedRoute("test_route")
        
        // Вызываем очистку
        routesLayer.cleanup()
        
        // Проверяем, что все очищено
        assertTrue(routesLayer.getRoutes().isEmpty())
        assertNull(routesLayer.getSelectedRouteId())
        verify { routeLayer.cleanup() }
    }
}