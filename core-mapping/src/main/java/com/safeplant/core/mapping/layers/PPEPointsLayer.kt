package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.storage.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Слой точек СИЗ (желтые иконки)
 * Отображает точки расположения средств индивидуальной защиты на карте в виде желтых иконок
 */
class PPEPointsLayer(
    id: String,
    style: LayerStyle = LayerStyle.PPE_POINTS
) : BaseLayer(id, style) {
    
    private val context: Context? = null // В реальном приложении будет инжектирован
    private val assetManager = AssetManager(context)
    
    /**
     * Список точек СИЗ
     */
    private var ppePoints = mutableListOf<PPEPointFeature>()
    
    /**
     * Информация для всплывающих окон
     */
    private var popupInfo = mutableMapOf<String, PopupInfo>()
    
    /**
     * Загружает данные слоя из assets
     */
    override fun loadData() {
        try {
            val geoJsonData = assetManager.loadFromAssets("geojson/ppe_points.geojson")
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
            
            ppePoints.clear()
            popupInfo.clear()
            
            for (i in 0 until features.length()) {
                val feature = features.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")
                
                // Проверяем тип геометрии (ожидается Point)
                if (geometry.getString("type") == "Point") {
                    val point = geometry.getJSONArray("coordinates")
                    val ppePoint = PPEPointFeature(
                        id = feature.getString("id"),
                        name = properties.optString("name", "Точка СИЗ"),
                        description = properties.optString("description", "Место расположения средств индивидуальной защиты"),
                        latitude = point.getDouble(1),
                        longitude = point.getDouble(0)
                    )
                    
                    ppePoints.add(ppePoint)
                    
                    // Сохраняем информацию для всплывающего окна
                    popupInfo[ppePoint.id] = PopupInfo(
                        title = ppePoint.name,
                        description = ppePoint.description,
                        type = "Точка СИЗ"
                    )
                }
            }
        } catch (e: Exception) {
            // Обработка ошибки парсинга
            // В реальном приложении здесь будет логирование
        }
    }
    
    /**
     * Отрисовывает слой на карте
     */
    override fun render() {
        // В реальном приложении здесь будет код отрисовки точек на карте
        // Для заглушки просто логируем
        println("Отрисовка ${ppePoints.size} точек СИЗ")
    }
    
    /**
     * Очищает ресурсы слоя
     */
    override fun cleanup() {
        ppePoints.clear()
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
     * Возвращает список точек СИЗ
     */
    fun getPPEPoints(): List<PPEPointFeature> = ppePoints
    
    /**
     * Возвращает информацию для всплывающих окон
     */
    fun getPopupInfo(): Map<String, PopupInfo> = popupInfo
    
    /**
     * Класс для представления точки СИЗ
     */
    data class PPEPointFeature(
        val id: String,
        val name: String,
        val description: String,
        val latitude: Double,
        val longitude: Double
    )
}