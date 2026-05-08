package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой эвакуации (зеленые линии)
 * Отображает маршруты эвакуации на карте в виде зеленых линий
 * Требует наличия действующего допуска для отображения
 */
class EvacuationLayer(
    id: String,
    style: LayerStyle = LayerStyle.EVACUATION,
    private val accessPassDao: AccessPassDao? = null // В реальном приложении будет инжектирован
) : BaseLayer(id, style) {
    
    /**
     * Список маршрутов эвакуации
     */
    private var evacuationRoutes = mutableListOf<EvacuationRouteFeature>()
    
    /**
     * Информация для всплывающих окон
     */
    private var popupInfo = mutableMapOf<String, PopupInfo>()
    
    /**
     * Флаг, показывающий, есть ли доступ к слою
     */
    private var hasAccess = false
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        // Проверяем доступ перед загрузкой данных
        if (!checkAccess()) {
            return
        }
        
        try {
            // В реальном приложении здесь будет загрузка из assets
            // Для заглушки используем тестовые данные
            parseGeoJson(generateTestGeoJson())
        } catch (e: Exception) {
            // Обработка ошибки загрузки данных
            // В реальном приложении здесь будет логирование и уведомление пользователя
        }
    }
    
    /**
     * Проверяет доступ к слою
     */
    private fun checkAccess(): Boolean {
        // В реальном приложении здесь будет проверка через AccessPassDao
        // Для заглушки всегда возвращаем true
        if (accessPassDao != null) {
            return runBlocking {
                val currentTime = System.currentTimeMillis()
                val accessPass = accessPassDao.getValidAccessPass("default_user", currentTime)
                hasAccess = accessPass != null
                hasAccess
            }
        }
        
        hasAccess = true
        return true
    }
    
    /**
     * Парсит GeoJSON данные
     */
    private fun parseGeoJson(geoJsonData: String) {
        try {
            val jsonObject = JSONObject(geoJsonData)
            val features = jsonObject.getJSONArray("features")
            
            evacuationRoutes.clear()
            popupInfo.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается LineString)
                if (geometry.getString("type") == "LineString") {
                    val route = EvacuationRouteFeature(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Маршрут эвакуации"),
                        description = properties.optString("description", "Маршрут эвакуации в случае чрезвычайной ситуации"),
                        coordinates = parseLineStringCoordinates(geometry.getJSONArray("coordinates"))
                    )
                    
                    evacuationRoutes.add(route)
                    
                    // Сохраняем информацию для всплывающего окна
                    popupInfo[route.id] = PopupInfo(
                        title = route.name,
                        description = route.description,
                        type = "Маршрут эвакуации"
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
     * Генерирует тестовые GeoJSON данные
     */
    private fun generateTestGeoJson(): String {
        return """
            {
                "type": "FeatureCollection",
                "features": [
                    {
                        "type": "Feature",
                        "id": "evacuation_1",
                        "geometry": {
                            "type": "LineString",
                            "coordinates": [
                                [37.6173, 55.7558],
                                [37.6273, 55.7658]
                            ]
                        },
                        "properties": {
                            "name": "Основной маршрут эвакуации",
                            "description": "Основной маршрут эвакуации из здания"
                        }
                    },
                    {
                        "type": "Feature",
                        "id": "evacuation_2",
                        "geometry": {
                            "type": "LineString",
                            "coordinates": [
                                [37.6173, 55.7558],
                                [37.6073, 55.7458]
                            ]
                        },
                        "properties": {
                            "name": "Альтернативный маршрут",
                            "description": "Альтернативный маршрут эвакуации"
                        }
                    }
                ]
            }
        """.trimIndent()
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        if (!hasAccess) {
            // Если нет доступа, не отрисовываем слой
            return
        }
        
        // В реальном приложении здесь будет код отрисовки линий на карте
        // Для заглушки просто логируем
        println("Отрисовка ${evacuationRoutes.size} маршрутов эвакуации")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        evacuationRoutes.clear()
        popupInfo.clear()
        hasAccess = false
    }
    
    /**
     * Проверяет, виден ли слой на текущем масштабе
     */
    override fun isVisible(zoom: Double): Boolean {
        return hasAccess && zoom >= style.minZoom && zoom <= style.maxZoom
    }
    
    /**
     * Обрабатывает нажатие на элемент слоя
     */
    override fun onFeatureClick(featureId: String): PopupInfo? {
        return popupInfo[featureId]
    }
    
    /**
     * Возвращает список маршрутов эвакуации
     */
    fun getEvacuationRoutes(): List<EvacuationRouteFeature> = evacuationRoutes
    
    /**
     * Возвращает информацию для всплывающих окон
     */
    fun getPopupInfo(): Map<String, PopupInfo> = popupInfo
    
    /**
     * Проверяет, есть ли доступ к слою
     */
    fun hasAccessToLayer(): Boolean = hasAccess
    
    /**
     * Класс для представления маршрута эвакуации
     */
    data class EvacuationRouteFeature(
        val id: String,
        val name: String,
        val description: String,
        val coordinates: List<Double>
    )
}