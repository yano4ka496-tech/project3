package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.LayerType
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой предустановленных маршрутов (синие линии)
 * Отображает предустановленные маршруты на карте в виде синих линий
 */
class RoutesLayer(
    id: String,
    style: LayerStyle = LayerStyle.ROUTES
) : BaseLayer(id, style) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    
    /**
     * Список маршрутов
     */
    private var routes = mutableListOf<RouteFeature>()
    
    /**
     * Информация для всплывающих окон
     */
    private var popupInfo = mutableMapOf<String, PopupInfo>()
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        try {
            val geoJsonData = assetManager.loadFromAssets("geojson/routes.geojson")
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
            
            routes.clear()
            popupInfo.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается LineString)
                if (geometry.getString("type") == "LineString") {
                    val route = RouteFeature(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Неизвестный маршрут"),
                        description = properties.optString("description", "Нет описания"),
                        coordinates = parseLineStringCoordinates(geometry.getJSONArray("coordinates"))
                    )
                    
                    routes.add(route)
                    
                    // Сохраняем информацию для всплывающего окна
                    popupInfo[route.id] = PopupInfo(
                        title = route.name,
                        description = route.description,
                        type = "Маршрут"
                    )
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки парсинга
            // В реальном приложении здесь будет логирование
        }
    }
    
    /**
     * Парсит координаты линии
     */
    private fun parseLineStringCoordinates(coordinatesArray: JSONArray): List<Double> {
        val result = mutableListOf<Double>()
        
        for (i in 0 until coordinatesArray.length()) {
            val point = coordinatesArray.getJSONArray(i)
            result.add(point.getDouble(0)) // longitude
            result.add(point.getDouble(1)) // latitude
        }
        
        return result
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        // В реальном приложении здесь будет код отрисовки линий на карте
        // Для заглушки просто логируем
        println("Отрисовка ${routes.size} маршрутов")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        routes.clear()
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
     * Возвращает список маршрутов
     */
    fun getRoutes(): List<RouteFeature> = routes
    
    /**
     * Возвращает информацию для всплывающих окон
     */
    fun getPopupInfo(): Map<String, PopupInfo> = popupInfo
    
    /**
     * Класс для представления маршрута
     */
    data class RouteFeature(
        val id: String,
        val name: String,
        val description: String,
        val coordinates: List<Double>
    )
}