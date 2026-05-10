package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Менеджер для работы с маршрутами
 * Загружает данные из GeoJSON и предоставляет доступ к маршрутам
 */
class RouteManager(
    private val context: Context
) {
    
    private val assetManager = AssetManager(context)
    private val routes = mutableListOf<RouteModel>()
    
    /**
     * Загружает маршруты из GeoJSON файла
     */
    fun loadRoutes(): Boolean {
        return try {
            val geoJsonData = assetManager.loadFromAssets("geojson/routes.geojson")
            if (geoJsonData.isNotEmpty()) {
                parseGeoJson(geoJsonData)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Парсит GeoJSON данные и создает модели маршрутов
     */
    private fun parseGeoJson(geoJsonData: String) {
        try {
            val jsonObject = JSONObject(geoJsonData)
            val features = jsonObject.getJSONArray("features")
            
            routes.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается LineString)
                if (geometry.getString("type") == "LineString") {
                    val route = RouteModel(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Неизвестный маршрут"),
                        description = properties.optString("description", "Нет описания"),
                        coordinates = parseLineStringCoordinates(geometry.getJSONArray("coordinates"))
                    )
                    
                    routes.add(route)
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки парсинга
            throw IOException("Ошибка парсинга GeoJSON: ${e.message}", e)
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
     * Возвращает список всех загруженных маршрутов
     */
    fun getRoutes(): List<RouteModel> = routes
    
    /**
     * Возвращает маршрут по ID
     */
    fun getRouteById(id: String): RouteModel? {
        return routes.find { it.id == id }
    }
    
    /**
     * Возвращает маршрут по имени
     */
    fun getRouteByName(name: String): RouteModel? {
        return routes.find { it.name.equals(name, ignoreCase = true) }
    }
    
    /**
     * Проверяет, существует ли маршрут с указанным ID
     */
    fun hasRoute(id: String): Boolean {
        return routes.any { it.id == id }
    }
    
    /**
     * Очищает загруженные маршруты
     */
    fun clearRoutes() {
        routes.clear()
    }
    
    /**
     * Возвращает количество загруженных маршрутов
     */
    fun getRouteCount(): Int = routes.size
}