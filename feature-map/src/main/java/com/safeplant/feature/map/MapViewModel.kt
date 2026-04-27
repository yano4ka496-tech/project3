package com.safeplant.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.database.entity.MapObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для карты предприятия
 * Управляет отображением карты, опасных зон и маршрутов
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapObjectDao: MapObjectDao
) : ViewModel() {

    // Состояние карты
    private val _mapState = MutableStateFlow(MapState())
    val mapState: StateFlow<MapState> = _mapState

    // Список объектов на карте
    private val _mapObjects = MutableStateFlow<List<MapObject>>(emptyList())
    val mapObjects: StateFlow<List<MapObject>> = _mapObjects

    /**
     * Загрузить объекты карты из базы данных
     */
    fun loadMapObjects() {
        viewModelScope.launch {
            _mapObjects.value = mapObjectDao.getAllMapObjects()
        }
    }

    /**
     * Установить центр карты
     * @param latitude - широта
     * @param longitude - долгота
     */
    fun setMapCenter(latitude: Double, longitude: Double) {
        _mapState.value = _mapState.value.copy(
            centerLatitude = latitude,
            centerLongitude = longitude
        )
    }

    /**
     * Установить уровень масштабирования
     * @param zoom - уровень масштабирования
     */
    fun setMapZoom(zoom: Double) {
        _mapState.value = _mapState.value.copy(zoom = zoom)
    }

    /**
     * Установить видимые слои карты
     * @param visibleLayers - список видимых слоёв
     */
    fun setVisibleLayers(visibleLayers: List<MapLayer>) {
        _mapState.value = _mapState.value.copy(visibleLayers = visibleLayers)
    }

    /**
     * Состояние карты
     */
    data class MapState(
        val centerLatitude: Double = 55.755826,
        val centerLongitude: Double = 37.617300,
        val zoom: Double = 12.0,
        val visibleLayers: List<MapLayer> = listOf(MapLayer.ROADS, MapLayer.BUILDINGS)
    )

    /**
     * Типы слоёв карты
     */
    enum class MapLayer {
        ROADS,      // Дороги
        BUILDINGS,  // Здания
        HAZARDS,    // Опасные зоны
        EVACUATION, // Зоны эвакуации
        ROUTES      // Маршруты
    }
}