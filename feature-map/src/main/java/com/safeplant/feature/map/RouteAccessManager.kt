package com.safeplant.feature.map

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.mapping.layers.HazardZone
import com.safeplant.core.mapping.layers.RouteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Менеджер проверки доступа к маршрутам
 * Проверяет наличие действующего допуска для маршрутов, проходящих через опасные зоны
 */
class RouteAccessManager(
    private val accessPassDao: AccessPassDao
) {
    
    /**
     * Проверяет доступ к маршруту
     * @param route Маршрут для проверки
     * @param hazardZones Список опасных зон
     * @param userId Идентификатор пользователя
     * @return Результат проверки доступа
     */
    suspend fun checkRouteAccess(
        route: RouteModel,
        hazardZones: List<HazardZone>,
        userId: String
    ): RouteAccessResult = withContext(Dispatchers.IO) {
        // Проверяем, проходит ли маршрут через опасную зону
        val routeIntersectsHazard = checkRouteIntersection(route, hazardZones)
        
        if (!routeIntersectsHazard) {
            // Маршрут не проходит через опасную зону - доступ разрешен
            RouteAccessResult.Allowed
        } else {
            // Маршрут проходит через опасную зону - проверяем наличие допуска
            val currentTime = System.currentTimeMillis()
            val hasValidPass = accessPassDao.hasValidAccessPass(userId, currentTime)
            
            if (hasValidPass) {
                RouteAccessResult.Allowed
            } else {
                RouteAccessResult.Denied
            }
        }
    }
    
    /**
     * Проверяет, пересекает ли маршрут опасные зоны
     * Использует алгоритм проверки всех точек маршрута на вхождение в полигоны опасных зон
     * @param route Маршрут для проверки
     * @param hazardZones Список опасных зон
     * @return true, если маршрут пересекает хотя бы одну опасную зону
     */
    private fun checkRouteIntersection(route: RouteModel, hazardZones: List<HazardZone>): Boolean {
        // Проверяем каждую точку маршрута
        for (i in 0 until route.coordinates.size step 2) {
            val lon = route.coordinates[i]
            val lat = route.coordinates[i + 1]
            
            // Проверяем, находится ли точка в любой из опасных зон
            for (hazardZone in hazardZones) {
                if (isPointInPolygon(lat, lon, hazardZone.coordinates)) {
                    return true
                }
            }
        }
        
        return false
    }
    
    /**
     * Проверяет, находится ли точка внутри полигона
     * Использует алгоритм лучей (ray casting)
     * @param lat Широта точки
     * @param lon Долгота точки
     * @param polygon Координаты полигона (список списков точек)
     * @return true, если точка внутри полигона
     */
    private fun isPointInPolygon(lat: Double, lon: Double, polygon: List<List<Double>>): Boolean {
        if (polygon.isEmpty()) return false
        
        // Берем первый контур полигона
        val ring = polygon[0]
        if (ring.size < 3) return false
        
        var inside = false
        val n = ring.size
        
        for (i in 0 until n) {
            val j = (i + 1) % n
            val xi = ring[i]
            val yi = ring[i + 1]
            val xj = ring[j]
            val yj = ring[j]
            
            // Проверяем пересечение луча с ребром полигона
            if (((yi > lat) != (yj > lat)) &&
                (lon < (xj - xi) * (lat - yi) / (yj - yi) + xi)) {
                inside = !inside
            }
        }
        
        return inside
    }
    
    /**
     * Вычисляет расстояние между двумя точками в метрах
     * @param lat1 Широта первой точки
     * @param lon1 Долгота первой точки
     * @param lat2 Широта второй точки
     * @param lon2 Долгота второй точки
     * @return Расстояние в метрах
     */
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371000.0 // Радиус Земли в метрах
        
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        
        val a = kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
                kotlin.math.cos(Math.toRadians(lat1)) * kotlin.math.cos(Math.toRadians(lat2)) *
                kotlin.math.sin(dLon / 2) * kotlin.math.sin(dLon / 2)
        
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
        
        return earthRadius * c
    }
    
    /**
     * Результат проверки доступа к маршруту
     */
    sealed class RouteAccessResult {
        object Allowed : RouteAccessResult()
        object Denied : RouteAccessResult()
    }
}