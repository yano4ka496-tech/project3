package com.safeplant.core.mapping

import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.style.layers.Layer
import org.maplibre.android.style.sources.Source

class MapRenderer(private val map: MapLibreMap) {

    fun setStyle(styleUrl: String) {
        map.setStyle(styleUrl)
    }

    fun addSource(source: Source) {
        if (map.style != null) {
            map.style?.addSource(source)
        }
    }

    fun addLayer(layer: Layer) {
        if (map.style != null) {
            map.style?.addLayer(layer)
        }
    }

    fun removeLayer(layerId: String) {
        map.style?.removeLayer(layerId)
    }

    fun removeSource(sourceId: String) {
        map.style?.removeSource(sourceId)
    }
}
