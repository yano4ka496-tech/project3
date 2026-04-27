package com.safeplant.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.maplibre.gl.android.MapLibreGL
import org.maplibre.gl.android.camera.CameraPosition
import org.maplibre.gl.android.camera.CameraUpdateFactory
import org.maplibre.gl.android.geometry.LatLng
import org.maplibre.gl.android.maps.MapView
import org.maplibre.gl.android.maps.Style

/**
 * Экран карты предприятия
 * Отображает карту с опасными зонами, маршрутами и зонами эвакуации
 */
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    val mapState by viewModel.mapState.collectAsState()
    val mapObjects by viewModel.mapObjects.collectAsState()

    MapView(
        modifier = Modifier.fillMaxSize()
    ) { mapView ->
        // Инициализация карты
        mapView.getMapAsync { map ->
            // Установка стиля карты
            map.setStyle(Style.getPredefinedStyle("Streets"))
            
            // Установка начальной позиции
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(mapState.centerLatitude, mapState.centerLongitude))
                .zoom(mapState.zoom)
                .build()
            
            map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            
            // Добавление слоёв в зависимости от видимости
            mapState.visibleLayers.forEach { layer ->
                when (layer) {
                    MapViewModel.MapLayer.ROADS -> {
                        // Добавление слоя дорог
                        map.addSource("roads", map.getSource("mapbox://mapbox.mapbox-streets-v8")!!)
                        map.addLayer("roads", "mapbox://mapbox.mapbox-streets-v8/layers/road")
                    }
                    MapViewModel.MapLayer.BUILDINGS -> {
                        // Добавление слоя зданий
                        map.addSource("buildings", map.getSource("mapbox://mapbox.mapbox-streets-v8")!!)
                        map.addLayer("buildings", "mapbox://mapbox.mapbox-streets-v8/layers/building")
                    }
                    MapViewModel.MapLayer.HAZARDS -> {
                        // Добавление слоя опасных зон
                        map.addSource("hazards", map.getSource("mapbox://mapbox.mapbox-streets-v8")!!)
                        map.addLayer("hazards", "mapbox://mapbox.mapbox-streets-v8/layers/hazard")
                    }
                    MapViewModel.MapLayer.EVACUATION -> {
                        // Добавление слоя зон эвакуации
                        map.addSource("evacuation", map.getSource("mapbox://mapbox.mapbox-streets-v8")!!)
                        map.addLayer("evacuation", "mapbox://mapbox.mapbox-streets-v8/layers/evacuation")
                    }
                    MapViewModel.MapLayer.ROUTES -> {
                        // Добавление слоя маршрутов
                        map.addSource("routes", map.getSource("mapbox://mapbox.mapbox-streets-v8")!!)
                        map.addLayer("routes", "mapbox://mapbox.mapbox-streets-v8/layers/routes")
                    }
                }
            }
            
            // Добавление объектов на карту
            mapObjects.forEach { mapObject ->
                // Здесь должна быть логика добавления маркеров или других элементов
                // на основе данных из mapObject
            }
        }
    }
}