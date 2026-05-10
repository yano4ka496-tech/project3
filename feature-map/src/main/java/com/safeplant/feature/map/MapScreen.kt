package com.safeplant.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.safeplant.core.mapping.MapController
import com.safeplant.core.mapping.MapLibreMapView
import org.maplibre.android.maps.MapLibreMap

@Composable
fun MapScreen() {
    val styleUrl = "https://demotiles.maplibre.org/style.json"

    MapLibreMapView(
        modifier = Modifier.fillMaxSize(),
        onMapReady = { mapLibreMap: MapLibreMap ->
            mapLibreMap.setStyle(styleUrl)
            val controller = MapController(mapLibreMap)
            // Пример: показать Москву
            controller.animateCamera(55.7558, 37.6173, 10.0)
        }
    )
}
