package com.safeplant.core.mapping

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.Layer
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import org.json.JSONObject

/**
 * Рендерер карты
 * Отвечает за отрисовку карты и слоев
 */
class MapRenderer(
    private val context: Context? = null
) {
    private var mapboxMap: MapboxMap? = null
    private var errorListener: MapErrorListener? = null
    private var performanceOptimizer: MapPerformanceOptimizer? = null
    
    /**
     * Рендерит карту в указанном контейнере
     */
    fun renderMap(mapView: Any) {
        // В реальном приложении здесь будет инициализация карты
        Log.d("MapRenderer", "Рендеринг карты в контейнере: $mapView")
    }
    
    /**
     * Вызывается, когда карта готова к использованию
     */
    fun onMapReady() {
        // В реальном приложении здесь будет настройка карты
        Log.d("MapRenderer", "Карта готова")
    }
    
    /**
     * Устанавливает источник тайлов
     */
    fun setTileSource(url: String) {
        // В реальном приложении здесь будет установка источника тайлов
        Log.d("MapRenderer", "Установка источника тайлов: $url")
    }
    
    /**
     * Устанавливает обработчик ошибок
     */
    fun setMapErrorListener(listener: MapErrorListener) {
        this.errorListener = listener
    }
    
    /**
     * Устанавливает оптимизатор производительности
     */
    fun setPerformanceOptimizer(optimizer: MapPerformanceOptimizer) {
        this.performanceOptimizer = optimizer
    }
    
    /**
     * Добавляет слой на карту
     */
    fun addLayer(layer: Layer) {
        mapboxMap?.let { map ->
            map.addLayer(layer)
            Log.d("MapRenderer", "Добавление слоя: ${layer.id}")
        }
    }
    
    /**
     * Удаляет слой с карты
     */
    fun removeLayer(layerId: String) {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                style.getLayer(layerId)?.let { layer ->
                    style.removeLayer(layer)
                    Log.d("MapRenderer", "Удаление слоя: $layerId")
                }
            }
        }
    }
    
    /**
     * Обновляет слой на карте
     */
    fun updateLayer(layer: Layer) {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                style.getLayer(layer.id)?.let { existingLayer ->
                    style.removeLayer(existingLayer)
                    style.addLayer(layer)
                    Log.d("MapRenderer", "Обновление слоя: ${layer.id}")
                }
            }
        }
    }
    
    /**
     * Добавляет источник GeoJSON на карту
     */
    fun addGeoJsonSource(sourceId: String, geoJson: String) {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                try {
                    val source = GeoJsonSource(sourceId, geoJson)
                    style.addSource(source)
                    Log.d("MapRenderer", "Добавление источника GeoJSON: $sourceId")
                } catch (e: Exception) {
                    Log.e("MapRenderer", "Ошибка добавления источника GeoJSON: ${e.message}")
                    errorListener?.onMapError("Ошибка загрузки данных слоя: ${e.message}")
                }
            }
        }
    }
    
    /**
     * Создает слой линий из GeoJSON источника
     */
    fun createLineLayer(
        layerId: String,
        sourceId: String,
        color: Int,
        width: Float = 1.0f,
        visibility: Property.VISIBLE = Property.VISIBLE
    ): LineLayer {
        return LineLayer(layerId, sourceId).apply {
            setProperties(
                PropertyFactory.lineColor(color),
                PropertyFactory.lineWidth(width),
                PropertyFactory.visibility(visibility)
            )
        }
    }
    
    /**
     * Создает слой символов из GeoJSON источника
     */
    fun createSymbolLayer(
        layerId: String,
        sourceId: String,
        visibility: Property.VISIBLE = Property.VISIBLE
    ): SymbolLayer {
        return SymbolLayer(layerId, sourceId).apply {
            setProperties(
                PropertyFactory.visibility(visibility)
            )
        }
    }
    
    /**
     * Устанавливает видимость слоя
     */
    fun setLayerVisibility(layerId: String, visible: Boolean) {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                style.getLayer(layerId)?.let { layer ->
                    val visibility = if (visible) Property.VISIBLE else Property.NONE
                    layer.setProperties(PropertyFactory.visibility(visibility))
                }
            }
        }
    }
    
    /**
     * Уничтожает карту и освобождает ресурсы
     */
    fun destroy() {
        mapboxMap = null
        Log.d("MapRenderer", "Уничтожение карты")
    }
    
    /**
     * Интерфейс обработчика ошибок карты
     */
    interface MapErrorListener {
        fun onMapError(error: String)
    }
}