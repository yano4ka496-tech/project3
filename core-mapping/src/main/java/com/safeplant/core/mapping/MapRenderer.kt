package com.safeplant.core.mapping

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QrCodeMapping
import org.maplibre.gl.MapLibreMap
import org.maplibre.gl.MapView
import org.maplibre.gl.camera.CameraPosition
import org.maplibre.gl.camera.CameraUpdateFactory
import org.maplibre.gl.geometry.LatLng
import org.maplibre.gl.style.layers.Layer
import org.maplibre.gl.style.layers.PropertyFactory
import org.maplibre.gl.style.layers.SymbolLayer
import org.maplibre.gl.style.sources.Source
import org.maplibre.gl.style.sources.VectorSource
import java.io.IOException

/**
 * Рендерер для работы с картой MapLibre GL
 */
class MapRenderer(
    private val context: Context,
    private val mapView: MapView,
    private val database: AppDatabase
) {
    
    private var map: MapLibreMap? = null
    private var qrCodeDao = database.qrCodeDao()
    
    /**
     * Инициализация карты
     */
    fun initializeMap(onMapReady: (MapLibreMap) -> Unit) {
        mapView.getMapAsync { mapLibreMap ->
            this.map = mapLibreMap
            
            // Настройка карты
            mapLibreMap.setStyle("https://demotiles.maplibre.org/style.json") { style ->
                // Загрузка MBTiles файла из assets
                loadMBTiles(style)
                
                // Добавление слоев
                addLayers(style)
                
                // Добавление QR-маркеров
                addQrMarkers()
            }
            
            onMapReady(mapLibreMap)
        }
    }
    
    /**
     * Загрузка MBTiles файла
     */
    private fun loadMBTiles(style: org.maplibre.gl.style.Style) {
        try {
            // Путь к MBTiles файлу в assets
            val mbtilesPath = "file:///android_asset/map.mbtiles"
            
            // Создание источника данных
            val mbtilesSource = Source.fromTileJson(
                "mbtiles-source",
                mbtilesPath
            )
            
            // Добавление источника в стиль
            style.addSource(mbtilesSource)
            
        } catch (e: IOException) {
            // Обработка ошибки загрузки MBTiles
            e.printStackTrace()
        }
    }
    
    /**
     * Добавление слоев на карту
     */
    private fun addLayers(style: org.maplibre.gl.style.Style) {
        // Слой зданий
        style.addLayer(
            SymbolLayer("buildings-layer", "mbtiles-source")
                .withProperties(
                    PropertyFactory.iconImage("marker-15"),
                    PropertyFactory.iconSize(1.5f)
                )
        )
        
        // Слой подписей
        style.addLayer(
            SymbolLayer("labels-layer", "mbtiles-source")
                .withProperties(
                    PropertyFactory.textField("{name}"),
                    PropertyFactory.textColor("#ffffff"),
                    PropertyFactory.textSize(12f),
                    PropertyFactory.textAnchor("top")
                )
        )
        
        // Слой опасных зон
        style.addLayer(
            SymbolLayer("danger-zones-layer", "mbtiles-source")
                .withProperties(
                    PropertyFactory.iconImage("warning-15"),
                    PropertyFactory.iconSize(1.2f)
                )
        )
        
        // Слой дорог
        style.addLayer(
            SymbolLayer("roads-layer", "mbtiles-source")
                .withProperties(
                    PropertyFactory.iconImage("road-12"),
                    PropertyFactory.iconSize(1.0f)
                )
        )
    }
    
    /**
     * Добавление QR-маркеров на карту
     */
    private fun addQrMarkers() {
        map?.let { mapLibreMap ->
            // Получаем все QR-сопоставления из базы данных
            qrCodeDao.getAll().forEach { mapping ->
                val position = LatLng(mapping.latitude, mapping.longitude)
                mapLibreMap.addMarker(
                    org.maplibre.gl.MarkerOptions()
                        .position(position)
                        .title(mapping.name)
                        .snippet("ID: ${mapping.objectId}")
                )
            }
        }
    }
    
    /**
     * Позиционирование карты по QR-коду
     */
    fun positionOnQrCode(objectId: String) {
        val mapping = qrCodeDao.getByObjectId(objectId)
        mapping?.let {
            val position = LatLng(it.latitude, it.longitude)
            moveToPosition(position)
            
            // Добавляем маркер для QR-позиции
            map?.addMarker(
                org.maplibre.gl.MarkerOptions()
                    .position(position)
                    .title(it.name)
                    .snippet("ID: ${it.objectId}")
            )
        }
    }
    
    /**
     * Позиционирование карты по координатам
     */
    fun positionOnCoordinates(latitude: Double, longitude: Double) {
        val position = LatLng(latitude, longitude)
        moveToPosition(position)
        
        // Добавляем маркер для координат
        map?.addMarker(
            org.maplibre.gl.MarkerOptions()
                .position(position)
                .title("QR-позиция")
                .snippet("ID: ${latitude.toInt()}|${longitude.toInt()}")
        )
    }
    
    /**
     * Перемещение камеры к указанной позиции
     */
    private fun moveToPosition(position: LatLng) {
        map?.let { mapLibreMap ->
            val cameraPosition = CameraPosition.Builder()
                .target(position)
                .zoom(15.0)
                .build()
            
            mapLibreMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition),
                1000
            )
        }
    }
    
    /**
     * Добавление маркера на карту
     */
    fun addMarker(
        position: LatLng,
        title: String,
        @DrawableRes iconRes: Int? = null
    ): org.maplibre.gl.Marker? {
        return map?.addMarker(
            org.maplibre.gl.MarkerOptions()
                .position(position)
                .title(title)
                .apply {
                    iconRes?.let { icon ->
                        val bitmap = BitmapFactory.decodeResource(context.resources, icon)
                        icon(bitmap)
                    }
                }
        )
    }
    
    /**
     * Очистка карты
     */
    fun clearMap() {
        map?.clear()
    }
    
    /**
     * Получение текущей позиции камеры
     */
    fun getCameraPosition(): CameraPosition? {
        return map?.cameraPosition
    }
    
    /**
     * Установка позиции камеры
     */
    fun setCameraPosition(position: CameraPosition) {
        map?.moveCamera(CameraUpdateFactory.newCameraPosition(position))
    }
    
    /**
     * Проверка корректности отображения MBTiles
     */
    fun validateMBTiles(): Boolean {
        // Проверяем, что карта инициализирована
        if (map == null) return false
        
        // Проверяем наличие слоев
        val style = map?.style ?: return false
        
        // Проверяем наличие источника данных
        val source = style.getSource("mbtiles-source")
        if (source == null) return false
        
        // Проверяем наличие слоев
        val layers = listOf(
            "buildings-layer",
            "labels-layer",
            "danger-zones-layer",
            "roads-layer"
        )
        
        for (layerId in layers) {
            val layer = style.getLayer(layerId)
            if (layer == null) return false
        }
        
        return true
    }
    
    /**
     * Проверка поддержки масштабирования
     */
    fun validateZoomLevels(): Boolean {
        // Проверяем, что карта инициализирована
        if (map == null) return false
        
        // Проверяем минимальный и максимальный зум
        val minZoom = map?.minZoomLevel ?: 0.0
        val maxZoom = map?.maxZoomLevel ?: 0.0
        
        // Ожидаемый диапазон: от 0 (10 км) до 18 (10 м)
        return minZoom <= 0.0 && maxZoom >= 18.0
    }
}