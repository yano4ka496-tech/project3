package com.safeplant.core.mapping

import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap

class MapController(private val map: MapLibreMap) {

    fun animateCamera(lat: Double, lon: Double, zoom: Double) {
        val latLng = LatLng(lat, lon)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        map.animateCamera(cameraUpdate, 500)
    }

    fun moveCamera(lat: Double, lon: Double, zoom: Double) {
        val latLng = LatLng(lat, lon)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        map.moveCamera(cameraUpdate)
    }

    fun getCurrentZoom(): Double = map.cameraPosition?.zoom?.toDouble() ?: 0.0
    fun getCenterLat(): Double = map.cameraPosition?.target?.latitude ?: 0.0
    fun getCenterLon(): Double = map.cameraPosition?.target?.longitude ?: 0.0
}
