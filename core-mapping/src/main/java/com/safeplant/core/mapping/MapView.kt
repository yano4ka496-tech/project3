package com.safeplant.core.mapping

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.mapboxsdk.maps.MapView as MapboxMapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style

/**
 * Компонент карты с поддержкой слоев
 * Интегрируется с MapLibre GL Native для отображения карты
 */
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onMapReady: (MapRenderer, MapController) -> Unit = { _, _ -> },
    onMapClick: (Double, Double) -> Unit = { _, _ -> },
    onLayerClick: (LayerType, Any) -> Unit = { _, _ -> }
) {
    // Создаем MapView
    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapboxMapView(context).apply {
                // Инициализация карты
               .getMapAsync { mapboxMap ->
                    // Устанавливаем стиль карты
                    mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                        // Уведомляем о готовности карты
                        val renderer = MapRenderer()
                        val controller = MapController()
                        
                        // Устанавливаем обработчик кликов
                        mapboxMap.addOnMapClickListener { latLon ->
                            onMapClick(latLon.latitude, latLon.longitude)
                        }
                        
                        // Уведомляем о готовности карты
                        onMapReady(renderer, controller)
                    }
                }
            }
        }
    )
}