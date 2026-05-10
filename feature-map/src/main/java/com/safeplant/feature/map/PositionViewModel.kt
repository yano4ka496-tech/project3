package com.safeplant.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.security.PositionStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * ViewModel для управления позицией пользователя
 * Отвечает за хранение, обновление и чтение координат пользователя
 * Проверяет позиции на соответствие разрешенным зонам
 */
class PositionViewModel(
    private val positionStorage: PositionStorage,
    private val mapObjectDao: MapObjectDao,
    private val accessPassDao: AccessPassDao
) : ViewModel() {

    private val _position = MutableStateFlow<Pair<Double, Double>?>(null)
    val position: StateFlow<Pair<Double, Double>?> = _position.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Инициализация при создании ViewModel
     * Читает последнюю сохраненную позицию из хранилища и проверяет ее валидность
     */
    init {
        loadLastPosition()
    }

    /**
     * Загружает последнюю сохраненную позицию из хранилища
     * Проверяет, не находится ли позиция в запрещенной зоне
     */
    private fun loadLastPosition() {
        viewModelScope.launch {
            positionStorage.getPosition()?.let { coordinates ->
                if (!isPositionInDangerousZone(coordinates.first, coordinates.second)) {
                    _position.value = coordinates
                } else {
                    // Позиция в запрещенной зоне - очищаем ее
                    clearPosition()
                    _error.value = "Сохраненная позиция находится в запрещенной зоне"
                }
            }
        }
    }

    /**
     * Проверяет, находится ли позиция в запрещенной зоне
     * @param latitude Широта
     * @param longitude Долгота
     * @return true, если позиция в опасной зоне
     */
    private fun isPositionInDangerousZone(latitude: Double, longitude: Double): Boolean {
        // Проверяем наличие действующего допуска
        val currentTime = System.currentTimeMillis()
        val hasValidPass = accessPassDao.hasValidAccessPass("default_user", currentTime)
        
        // Если допуска нет, считаем любую позицию опасной
        if (!hasValidPass) {
            return true
        }
        
        // Получаем все объекты из базы данных
        val allObjects = mapObjectDao.getAllMapObjects()
        
        // Проверяем, не находится ли позиция в радиусе 10 метров от точки с категорией "dangerous_zone"
        for (mapObject in allObjects) {
            if (mapObject.category == "dangerous_zone") {
                val distance = calculateDistance(
                    latitude, longitude,
                    mapObject.latitude, mapObject.longitude
                )
                
                // Если расстояние меньше 10 метров, позиция в опасной зоне
                if (distance <= 10.0) {
                    return true
                }
            }
        }
        
        return false
    }

    /**
     * Проверяет, находится ли позиция в запрещенной зоне (публичный метод)
     * @param latitude Широта
     * @param longitude Долгота
     * @return true, если позиция в запрещенной зоне
     */
    fun isPositionInDangerousZone(latitude: Double, longitude: Double): Boolean {
        return isPositionInDangerousZone(latitude, longitude)
    }

    /**
     * Вычисляет расстояние между двумя точками в метрах
     * @param lat1 Широта первой точки
     * @param lon1 Долгота первой точки
     * @param lat2 Широта второй точки
     * @param lon2 Долгота второй точки
     * @return Расстояние в метрах
     */
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371000.0 // Радиус Земли в метрах
        
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        
        val a = kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
                kotlin.math.cos(Math.toRadians(lat1)) * kotlin.math.cos(Math.toRadians(lat2)) *
                kotlin.math.sin(dLon / 2) * kotlin.math.sin(dLon / 2)
        
        val c = 2 * kotlin.math.atan2(kotlin.math.sqrt(a), kotlin.math.sqrt(1 - a))
        
        return earthRadius * c
    }

    /**
     * Обновляет позицию пользователя
     * Проверяет, не находится ли позиция в запрещенной зоне
     * @param latitude Широта
     * @param longitude Долгота
     */
    fun updatePosition(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            if (isPositionInDangerousZone(latitude, longitude)) {
                _error.value = "Выбранная позиция находится в запрещенной зоне"
                return@launch
            }
            
            positionStorage.savePosition(latitude, longitude)
            _position.value = Pair(latitude, longitude)
            _error.value = null
        }
    }

    /**
     * Очищает сохраненную позицию
     */
    fun clearPosition() {
        viewModelScope.launch {
            positionStorage.clearPosition()
            _position.value = null
            _error.value = null
        }
    }
    
    /**
     * Очищает сообщение об ошибке
     */
    fun clearError() {
        _error.value = null
    }
}