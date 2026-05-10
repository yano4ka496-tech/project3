package com.safeplant.core.mapping.layers

import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import org.json.JSONObject

/**
 * Класс для отрисовки стрелок направления движения вдоль линии маршрута
 */
class ArrowDirectionRenderer {
    
    /**
     * Создает слой стрелок для указания направления движения
     */
    fun createArrowLayer(
        sourceId: String,
        layerId: String,
        color: Int = 0xFF0000FF
    ): SymbolLayer {
        return SymbolLayer(layerId, sourceId).apply {
            setProperties(
                // Стиль иконки стрелки
                PropertyFactory.iconSize(1.5f),
                PropertyFactory.iconRotationAlignment(Property.ICON_ROTATION_ALIGNMENT_MAP),
                PropertyFactory.iconAllowOverlap(true),
                PropertyFactory.iconIgnorePlacement(true),
                
                // Цвет стрелки
                PropertyFactory.iconColor(color),
                
                // Используем встроенную иконку стрелки
                PropertyFactory.iconImage("arrow-icon")
            )
        }
    }
    
    /**
     * Обновляет слой стрелок с новыми данными
     */
    fun updateArrowLayer(
        layer: SymbolLayer,
        sourceId: String,
        geoJson: String
    ) {
        // В реальном приложении здесь будет обновление источника данных
        // Для заглушки просто логируем
        println("Обновление слоя стрелок: ${layer.id}")
    }
    
    /**
     * Устанавливает видимость слоя стрелок
     */
    fun setArrowLayerVisibility(layer: SymbolLayer, visible: Boolean) {
        val visibility = if (visible) Property.VISIBLE else Property.NONE
        layer.setProperties(PropertyFactory.visibility(visibility))
    }
}