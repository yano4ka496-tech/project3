package com.safeplant.core.mapping.layers

import android.content.Context
import com.safeplant.core.mapping.LayerStyle
import com.safeplant.core.mapping.LayerType
import com.safeplant.core.mapping.MapLayer

/**
 * Фабрика для создания слоев карты
 * Создает конкретные реализации слоев на основе типа
 */
class LayerFactory(private val context: Context) {
    
    /**
     * Создает слой указанного типа
     */
    fun createLayer(type: LayerType, id: String): MapLayer {
        return when (type) {
            LayerType.HAZARDS -> HazardsLayer(id, LayerStyle.HAZARDS)
            LayerType.ROUTES -> RoutesLayer(id, LayerStyle.ROUTES)
            LayerType.EVACUATION -> EvacuationLayer(id, LayerStyle.EVACUATION)
            LayerType.PPE_POINTS -> PPEPointsLayer(id, LayerStyle.PPE_POINTS)
        }
    }
}