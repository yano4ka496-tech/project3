package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.MapLayer
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой опасных зон (красные полигоны)
 * Отображает опасные зоны на карте и предоставляет доступ к ним для проверки маршрутов
 */
class HazardsLayer(
    id: String,
    style: LayerStyle = LayerStyle.HAZARDS
) : MapLayer(
    type = com.safeplant.core.mapping.LayerType.HAZARDS,
    style = style,
    id = id
) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    
    /**
     * Список опасных зон
     */
    private val hazardZones = mutableListOf<HazardZone>()
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        try {
            val geoJsonData = assetManager.loadFromAssets("geojson/hazards.geojson")
            if (geoJsonData.isNotEmpty()) {
                parseGeoJson(geoJsonData)
            }
        } catch (e: Exception) {
            // Обработка ошибки загрузки данных
            // В реальном приложении здесь будет логирование и уведомление пользователя
        }
    }
    
    /**
     * Парсит GeoJSON данные и создает модели опасных зон
     */
    private fun parseGeoJson(geoJsonData: String) {
        try {
            val jsonObject = JSONObject(geoJsonData)
            val features = jsonObject.getJSONArray("features")
            
            hazardZones.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается Polygon)
                if (geometry.getString("type") == "Polygon") {
                    val zone = HazardZone(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Неизвестная зона"),
                        description = properties.optString("description", "Нет описания"),
                        centerLat = properties.optDouble("center_lat", 0.0),
                        centerLon = properties.optDouble("center_lon", 0.0),
                        coordinates = parsePolygonCoordinates(geometry.getJSONArray("coordinates"))
                    )
                    
                    hazardZones.add(zone)
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки парсинга
            throw Exception("Ошибка парсинга GeoJSON опасных зон: ${e.message}", e)
        }
    }
    
    /**
     * Парсит координаты полигона
     */
    private fun parsePolygonCoordinates(coordinatesArray: JSONArray): List<List<Double>> {
        val result = mutableListOf<List<Double>>()
        
        for (i in 0 until coordinatesArray.length()) {
            val ring = coordinatesArray.getJSONArray(i)
            val points = mutableListOf<Double>()
            
            for (j in 0 until ring.length()) {
                val point = ring.getJSONArray(j)
                points.add(point.getDouble(0)) // longitude
                points.add(point.getDouble(1)) // latitude
            }
            
            result.add(points)
        }
        
        return result
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        // В реальном приложении здесь будет код отрисовки полигонов на карте
        // Для заглушки просто логируем
        println("Отрисовка ${hazardZones.size} опасных зон")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        hazardZones.clear()
    }
    
    /**
     * Проверяет, виден ли слой на текущем масштабе
     */
    override fun isVisible(zoom: Double): Boolean {
        return zoom >= style.minZoom && zoom <= style.maxZoom
    }
    
    /**
     * Обрабатывает нажатие на элемент слоя
     */
    override fun onFeatureClick(featureId: String): PopupInfo? {
        return hazardZones.find { it.id == featureId }?.let { zone ->
            PopupInfo(
                title = zone.name,
                description = zone.description,
                type = "Опасная зона"
            )
        }
    }
    
    /**
     * Возвращает список опасных зон
     */
    fun getHazardZones(): List<HazardZone> = hazardZones
    
    /**
     * Возвращает опасную зону по ID
     */
    fun getHazardZoneById(id: String): HazardZone? {
        return hazardZones.find { it.id == id }
    }
    
    /**
     * Проверяет, существует ли опасная зона с указанным ID
     */
    fun hasHazardZone(id: String): Boolean {
        return hazardZones.any { it.id == id }
    }
}