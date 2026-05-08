package com.safeplant.core.mapping

import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap

/**
 * Контроллер карты
 * Управляет состоянием карты и взаимодействием с пользователем
 */
class MapController(
    private var mapboxMap: MapboxMap? = null
) {
    
    /**
     * Устанавливает объект карты
     */
    fun setMap(map: MapboxMap) {
        this.mapboxMap = map
    }
    
    /**
     * Перемещает камеру на указанные координаты
     */
    fun moveCamera(lat: Double, lon: Double, zoom: Double = 15.0) {
        mapboxMap?.let { map ->
            val position = CameraPosition.Builder()
                .target(LatLng(lat, lon))
                .zoom(zoom)
                .build()
            map.cameraPosition = position
        }
    }
    
    /**
     * Анимированное перемещение камеры
     */
    fun animateCamera(lat: Double, lon: Double, zoom: Double = 15.0) {
        mapboxMap?.let { map ->
            val position = CameraPosition.Builder()
                .target(LatLng(lat, lon))
                .zoom(zoom)
                .build()
            map.animateCamera(CameraPosition.Builder(map.cameraPosition).target(position.target).zoom(position.zoom).build())
        }
    }
    
    /**
     * Увеличивает масштаб
     */
    fun zoomIn() {
        mapboxMap?.let { map ->
            map.animateCamera(CameraUpdateFactory.zoomIn())
        }
    }
    
    /**
     * Уменьшает масштаб
     */
    fun zoomOut() {
        mapboxMap?.let { map ->
            map.animateCamera(CameraUpdateFactory.zoomOut())
        }
    }
    
    /**
     * Проверяет, достигнут минимальный масштаб
     */
    fun isMinZoom(): Boolean {
        return mapboxMap?.minZoomLevel?.let { mapboxMap?.cameraPosition?.zoom ?: 0.0 <= it } ?: false
    }
    
    /**
     * Проверяет, достигнут максимальный масштаб
     */
    fun isMaxZoom(): Boolean {
        return mapboxMap?.maxZoomLevel?.let { mapboxMap?.cameraPosition?.zoom ?: 0.0 >= it } ?: false
    }
    
    /**
     * Получает текущий масштаб карты
     */
    fun getCurrentZoom(): Double {
        return mapboxMap?.cameraPosition?.zoom ?: 15.0
    }
    
    /**
     * Получает широту центра карты
     */
    fun getCenterLat(): Double {
        return mapboxMap?.cameraPosition?.target?.latitude ?: 0.0
    }
    
    /**
     * Получает долготу центра карты
     */
    fun getCenterLon(): Double {
        return mapboxMap?.cameraPosition?.target?.longitude ?: 0.0
    }
    
    /**
     * Устанавливает видимость слоя
     */
    fun setLayerVisibility(layerType: LayerType, visible: Boolean) {
        // В реальном приложении здесь будет управление видимостью слоя
        println("Установка видимости слоя ${layerType.name}: $visible")
    }
    
    /**
     * Обновляет видимость слоев в зависимости от масштаба
     */
    fun updateLayersVisibilityForZoom(zoom: Double) {
        // В реальном приложении здесь будет обновление видимости слоев
        println("Обновление видимости слоев для масштаба: $zoom")
    }
}