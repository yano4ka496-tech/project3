package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.MapLayer
import com.safeplant.core.mapping.MapRenderer
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Слой для отображения выбранного маршрута на карте
 * Отображает линию маршрута и стрелки направления движения
 * Проверяет пересечение с опасными зонами и блокирует маршрут при необходимости
 */
class RouteLayer(
    id: String,
    style: LayerStyle = LayerStyle.ROUTES
) : MapLayer(
    type = com.safeplant.core.mapping.LayerType.ROUTES,
    style = style,
    id = id
) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    private val arrowRenderer = ArrowDirectionRenderer()
    
    /**
     * Выбранный маршрут
     */
    private var selectedRoute: RouteModel? = null
    
    /**
     * Флаг блокировки маршрута
     */
    private var isBlocked = false
    
    /**
     * Слой линии маршрута
     */
    private var lineLayer: com.mapbox.mapboxsdk.style.layers.LineLayer? = null
    
    /**
     * Слой стрелок направления
     */
    private var arrowLayer: com.mapbox.mapboxsdk.style.layers.SymbolLayer? = null
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        // Данные маршрутов уже загружены в RoutesLayer
        // Здесь не требуется дополнительная загрузка
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        selectedRoute?.let { route ->
            if (lineLayer == null) {
                createLineLayer(route)
            }
            
            if (arrowLayer == null) {
                createArrowLayer(route)
            }
            
            // Проверяем пересечение с опасными зонами
            if (!isBlocked) {
                checkIntersectionWithHazards(route)
            }
            
            // Обновляем стиль в зависимости от блокировки
            updateLayerStyle()
        }
    }
    
    /**
     * Создает слой линии маршрута
     */
    private fun createLineLayer(route: RouteModel) {
        // Создаем источник GeoJSON для маршрута
        val geoJson = createRouteGeoJson(route)
        
        // Добавляем источник на карту
        mapRenderer?.addGeoJsonSource("${id}_route_source", geoJson)
        
        // Создаем слой линии
        lineLayer = mapRenderer?.createLineLayer(
            "${id}_route_line",
            "${id}_route_source",
            if (isBlocked) 0xFFFF0000 else style.color, // Красный если заблокирован, иначе цвет стиля
            style.lineWidth
        )
        
        // Добавляем слой на карту
        lineLayer?.let { layer ->
            mapRenderer?.addLayer(layer)
        }
    }
    
    /**
     * Создает слой стрелок направления
     */
    private fun createArrowLayer(route: RouteModel) {
        // Создаем источник для стрелок
        val arrowSource = createArrowSource(route)
        
        // Добавляем источник на карту
        mapRenderer?.addGeoJsonSource("${id}_arrow_source", arrowSource)
        
        // Создаем слой символов для стрелок
        arrowLayer = mapRenderer?.createSymbolLayer(
            "${id}_arrow_symbols",
            "${id}_arrow_source"
        )
        
        // Настраиваем стиль стрелок
        arrowLayer?.let { layer ->
            mapRenderer?.updateLayer(layer)
        }
    }
    
    /**
     * Создает GeoJSON для маршрута
     */
    private fun createRouteGeoJson(route: RouteModel): String {
        val coordinates = JSONArray()
        
        // Добавляем точки маршрута в формате [lon, lat]
        for (i in 0 until route.coordinates.size step 2) {
            val point = JSONArray()
            point.put(route.coordinates[i])     // longitude
            point.put(route.coordinates[i + 1]) // latitude
            coordinates.put(point)
        }
        
        val geometry = JSONObject().apply {
            put("type", "LineString")
            put("coordinates", coordinates)
        }
        
        return JSONObject().apply {
            put("type", "Feature")
            put("properties", JSONObject().apply {
                put("name", route.name)
                put("description", route.description)
            })
            put("geometry", geometry)
        }.toString()
    }
    
    /**
     * Создает источник для стрелок направления
     */
    private fun createArrowSource(route: RouteModel): String {
        val arrowPoints = JSONArray()
        
        // Вычисляем точки для стрелок (каждые 100 метров или 5 точек маршрута)
        val step = maxOf(1, route.coordinates.size / 10)
        
        for (i in step until route.coordinates.size step step) {
            val point = JSONArray()
            point.put(route.coordinates[i])     // longitude
            point.put(route.coordinates[i + 1]) // latitude
            arrowPoints.put(point)
        }
        
        val features = JSONArray()
        
        // Создаем feature для каждой стрелки
        for (i in 0 until arrowPoints.length()) {
            val point = arrowPoints.getJSONArray(i)
            val geometry = JSONObject().apply {
                put("type", "Point")
                put("coordinates", point)
            }
            
            val properties = JSONObject().apply {
                // Вычисляем угол направления
                val angle = calculateArrowAngle(
                    route.coordinates[i * 2 - 2], route.coordinates[i * 2 - 1],
                    route.coordinates[i * 2], route.coordinates[i * 2 + 1]
                )
                put("angle", angle)
                put("marker-size", "large")
                put("marker-symbol", "arrow")
            }
            
            features.put(JSONObject().apply {
                put("type", "Feature")
                put("properties", properties)
                put("geometry", geometry)
            })
        }
        
        return JSONObject().apply {
            put("type", "FeatureCollection")
            put("features", features)
        }.toString()
    }
    
    /**
     * Вычисляет угол направления между двумя точками
     */
    private fun calculateArrowAngle(lon1: Double, lat1: Double, lon2: Double, lat2: Double): Double {
        val dLon = Math.toRadians(lon2 - lon1)
        val lat1Rad = Math.toRadians(lat1)
        val lat2Rad = Math.toRadians(lat2)
        
        val y = kotlin.math.sin(dLon) * kotlin.math.cos(lat2Rad)
        val x = kotlin.math.cos(lat1Rad) * kotlin.math.sin(lat2Rad) -
                kotlin.math.sin(lat1Rad) * kotlin.math.cos(lat2Rad) * kotlin.math.cos(dLon)
        
        val bearing = kotlin.math.atan2(y, x)
        return (Math.toDegrees(bearing) + 360) % 360
    }
    
    /**
     * Проверяет пересечение маршрута с опасными зонами
     */
    private fun checkIntersectionWithHazards(route: RouteModel) {
        // В реальном приложении здесь будет получение опасных зон из HazardsLayer
        // Для заглушки используем пустой список
        val hazardZones = listOf<HazardZone>()
        
        // Проверяем каждую точку маршрута
        for (i in 0 until route.coordinates.size step 2) {
            val lon = route.coordinates[i]
            val lat = route.coordinates[i + 1]
            
            // Проверяем расстояние до ближайшей опасной зоны
            if (isPointInDangerousZone(lat, lon, hazardZones)) {
                isBlocked = true
                break
            }
        }
    }
    
    /**
     * Проверяет, находится ли точка в опасной зоне
     */
    private fun isPointInDangerousZone(lat: Double, lon: Double, hazardZones: List<HazardZone>): Boolean {
        // Пороговое расстояние в метрах
        val thresholdDistance = 10.0
        
        for (zone in hazardZones) {
            // Вычисляем расстояние от точки до центра опасной зоны
            val distance = calculateDistance(lat, lon, zone.centerLat, zone.centerLon)
            
            if (distance <= thresholdDistance) {
                return true
            }
        }
        
        return false
    }
    
    /**
     * Вычисляет расстояние между двумя точками в метрах
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
     * Обновляет стиль слоя в зависимости от блокировки
     */
    private fun updateLayerStyle() {
        lineLayer?.let { layer ->
            val color = if (isBlocked) 0xFFFF0000 else style.color
            mapRenderer?.updateLayer(layer)
        }
    }
    
    /**
     * Устанавливает выбранный маршрут
     */
    fun setRoute(route: RouteModel?) {
        selectedRoute = route
        isBlocked = false
        
        // Очищаем предыдущие слои
        clearLayers()
        
        // Перерисовываем слой
        render()
    }
    
    /**
     * Очищает слои маршрута
     */
    private fun clearLayers() {
        lineLayer?.let { layer ->
            mapRenderer?.removeLayer(layer.id)
        }
        
        arrowLayer?.let { layer ->
            mapRenderer?.removeLayer(layer.id)
        }
        
        lineLayer = null
        arrowLayer = null
    }
    
    /**
     * Проверяет, заблокирован ли маршрут
     */
    fun isBlocked(): Boolean = isBlocked
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        clearLayers()
        selectedRoute = null
        isBlocked = false
    }
    
    /**
     * Проверяет, виден ли слой на текущем масштабе
     */
    override fun isVisible(zoom: Double): Boolean {
        return selectedRoute != null && zoom >= style.minZoom && zoom <= style.maxZoom
    }
    
    /**
     * Обрабатывает нажатие на элемент слоя
     */
    override fun onFeatureClick(featureId: String): PopupInfo? {
        return selectedRoute?.let { route ->
            PopupInfo(
                title = route.name,
                description = if (isBlocked) "Маршрут заблокирован: проходит через опасную зону" else route.description,
                type = "Маршрут"
            )
        }
    }
}