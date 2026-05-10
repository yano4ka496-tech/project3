package com.safeplant.core.mapping.layers

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource

/**
 * Класс для отрисовки направления движения по маршруту
 * Отображает стрелки вдоль линии маршрута
 */
class RouteDirectionRenderer(
    private val mapboxMap: MapboxMap,
    private val sourceId: String,
    private val layerId: String
) {
    
    /**
     * Интервал между стрелками в метрах
     */
    private val arrowSpacing = 50.0
    
    /**
     * Размер стрелки в пикселях
     */
    private val arrowSize = 15.0f
    
    /**
     * Отрисовывает направление движения по маршруту
     */
    fun renderDirection(route: RouteModel) {
        if (!route.isValid()) return
        
        // Создаем источник для стрелок
        val arrowSource = createArrowSource(route)
        
        // Добавляем источник на карту
        mapboxMap.getStyle { style ->
            style.addSource(arrowSource)
            
            // Создаем слой для стрелок
            val arrowLayer = createArrowLayer()
            style.addLayer(arrowLayer)
        }
    }
    
    /**
     * Создает GeoJSON источник для стрелок
     */
    private fun createArrowSource(route: RouteModel): GeoJsonSource {
        // Создаем массив для стрелок
        val arrows = mutableListOf<Map<String, Any>>()
        
        // Рассчитываем количество стрелок на основе длины маршрута
        val routeLength = calculateRouteLength(route)
        val arrowCount = (routeLength / arrowSpacing).toInt()
        
        // Генерируем стрелки вдоль маршрута
        for (i in 0 until arrowCount) {
            val position = i * arrowSpacing
            val point = getPointAtDistance(route, position)
            
            if (point != null) {
                // Создаем стрелку в виде GeoJSON Feature
                val arrow = mapOf(
                    "type" to "Feature",
                    "properties" to mapOf(
                        "arrow" to true,
                        "size" to arrowSize
                    ),
                    "geometry" to mapOf(
                        "type" to "Point",
                        "coordinates" to listOf(point.first, point.second)
                    )
                )
                arrows.add(arrow)
            }
        }
        
        // Создаем GeoJSON FeatureCollection
        val geoJson = mapOf(
            "type" to "FeatureCollection",
            "features" to arrows
        )
        
        return GeoJsonSource(sourceId, geoJson)
    }
    
    /**
     * Создает слой для отображения стрелок
     */
    private fun createArrowLayer(): LineLayer {
        return LineLayer(layerId, sourceId).apply {
            setProperties(
                PropertyFactory.lineColor(Color.RED),
                PropertyFactory.lineWidth(2.0f),
                PropertyFactory.visibility(Property.VISIBLE)
            )
        }
    }
    
    /**
     * Рассчитывает длину маршрута в метрах
     */
    private fun calculateRouteLength(route: RouteModel): Double {
        var length = 0.0
        val points = mutableListOf<LatLng>()
        
        // Преобразуем координаты в LatLng
        for (i in 0 until route.coordinates.size step 2) {
            if (i + 1 < route.coordinates.size) {
                points.add(LatLng(route.coordinates[i + 1], route.coordinates[i]))
            }
        }
        
        // Рассчитываем расстояние между точками
        for (i in 0 until points.size - 1) {
            length += points[i].distanceTo(points[i + 1])
        }
        
        return length
    }
    
    /**
     * Возвращает точку на маршруте на заданном расстоянии от начала
     */
    private fun getPointAtDistance(route: RouteModel, distance: Double): Pair<Double, Double>? {
        if (route.coordinates.size < 2) return null
        
        var accumulatedDistance = 0.0
        val points = mutableListOf<LatLng>()
        
        // Преобразуем координаты в LatLng
        for (i in 0 until route.coordinates.size step 2) {
            if (i + 1 < route.coordinates.size) {
                points.add(LatLng(route.coordinates[i + 1], route.coordinates[i]))
            }
        }
        
        // Ищем точку на заданном расстоянии
        for (i in 0 until points.size - 1) {
            val segmentLength = points[i].distanceTo(points[i + 1])
            
            if (accumulatedDistance + segmentLength >= distance) {
                // Точка находится в этом сегменте
                val ratio = (distance - accumulatedDistance) / segmentLength
                val lat = points[i].latitude + ratio * (points[i + 1].latitude - points[i].latitude)
                val lon = points[i].longitude + ratio * (points[i + 1].longitude - points[i].longitude)
                return Pair(lon, lat)
            }
            
            accumulatedDistance += segmentLength
        }
        
        // Если расстояние больше длины маршрута, возвращаем последнюю точку
        return if (points.isNotEmpty()) {
            Pair(points.last().longitude, points.last().latitude)
        } else {
            null
        }
    }
    
    /**
     * Удаляет слой стрелок с карты
     */
    fun clearDirection() {
        mapboxMap.getStyle { style ->
            style.removeLayer(layerId)
            style.removeSource(sourceId)
        }
    }
}