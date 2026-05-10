package com.safeplant.core.mapping

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.MapLibreMap

@Composable
fun MapLibreMapView(
    modifier: Modifier = Modifier,
    onMapReady: (MapLibreMap) -> Unit = {},
    onMapClick: (LatLng) -> Unit = {},
    onMapLongClick: (LatLng) -> Unit = {}
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.getMapAsync { mapLibreMap ->
            onMapReady(mapLibreMap)
            mapLibreMap.addOnMapClickListener { point ->
                onMapClick(point)
                true
            }
            mapLibreMap.addOnMapLongClickListener { point ->
                onMapLongClick(point)
                true
            }
        }
        onDispose {
            mapView.onDestroy()
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = modifier,
        update = { view ->
            view.onResume()
        }
    )
}
