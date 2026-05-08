package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.LayerType
import com.safeplant.core.mapping.MapLayer
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой опасных зон (красные полигоны)
 * Отображает опасные зоны на карте в виде красных полигонов
 */
class HazardsLayer(
    id: String,
    style: LayerStyle = LayerStyle.HAZARDS
) : BaseLayer(id, style) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    
    /**
     * Список опасных зон
     */
    private var hazards = mutableListOf<HazardFeature>()
    
    /**
     * Информация для всплывающих окон
     */
    private var popupInfo = mutableMapOf<String, PopupInfo>()
    
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
     * Парсит GeoJSON данные
     */
    private fun parseGeoJson(geoJsonData: String) {
        try {
            val jsonObject = JSONObject(geoJsonData)
            val features = jsonObject.getJSONArray("features")
            
            hazards.clear()
            popupInfo.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается Polygon)
                if (geometry.getString("type") == "Polygon") {
                    val hazard = HazardFeature(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Неизвестная зона"),
                        description = properties.optString("description", "Нет описания"),
                        coordinates = parsePolygonCoordinates(geometry.getJSONArray("coordinates"))
                    )
                    
                    hazards.add(hazard)
                    
                    // Сохраняем информацию для всплывающего окна
                    popupInfo[hazard.id] = PopupInfo(
                        title = hazard.name,
                        description = hazard.description,
                        type = "Опасная зона"
                    )
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки парсинга
            // В реальном приложении здесь будет логирование
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
        println("Отрисовка ${hazards.size} опасных зон")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        hazards.clear()
        popupInfo.clear()
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
        return popupInfo[featureId]
    }
    
    /**
     * Возвращает список опасных зон
     */
    fun getHazards(): List<HazardFeature> = hazards
    
    /**
     * Возвращает информацию для всплывающих окон
     */
    fun getPopupInfo(): Map<String, PopupInfo> = popupInfo
    
    /**
     * Класс для представления опасной зоны
     */
    data class HazardFeature(
        val id: String,
        val name: String,
        val description: String,
        val coordinates: List<List<Double>>
    )
}