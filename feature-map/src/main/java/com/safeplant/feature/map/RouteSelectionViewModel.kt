package com.safeplant.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.mapping.layers.HazardZone
import com.safeplant.core.mapping.layers.RouteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * ViewModel для выбора маршрута и проверки доступа
 * Управляет состоянием выбранного маршрута и проверяет доступ к опасным зонам
 */
class RouteSelectionViewModel(
    private val accessManager: RouteAccessManager,
    private val accessPassDao: AccessPassDao
) : ViewModel() {
    
    private val _selectedRoute = MutableStateFlow<RouteModel?>(null)
    val selectedRoute: StateFlow<RouteModel?> = _selectedRoute.asStateFlow()
    
    private val _accessResult = MutableStateFlow<RouteAccessManager.RouteAccessResult?>(null)
    val accessResult: StateFlow<RouteAccessManager.RouteAccessResult?> = _accessResult.asStateFlow()
    
    private val _hazardZones = MutableStateFlow<List<HazardZone>>(emptyList())
    val hazardZones: StateFlow<List<HazardZone>> = _hazardZones.asStateFlow()
    
    private val _userId = MutableStateFlow("default_user")
    val userId: StateFlow<String> = _userId.asStateFlow()
    
    private val _accessPass = MutableStateFlow<AccessPass?>(null)
    val accessPass: StateFlow<AccessPass?> = _accessPass.asStateFlow()
    
    /**
     * Устанавливает список опасных зон
     */
    fun setHazardZones(zones: List<HazardZone>) {
        _hazardZones.value = zones
    }
    
    /**
     * Устанавливает идентификатор пользователя
     */
    fun setUserId(userId: String) {
        _userId.value = userId
    }
    
    /**
     * Выбирает маршрут и проверяет доступ
     */
    fun selectRoute(route: RouteModel) {
        viewModelScope.launch {
            _selectedRoute.value = route
            
            // Проверяем доступ к маршруту
            val result = accessManager.checkRouteAccess(
                route = route,
                hazardZones = _hazardZones.value,
                userId = _userId.value
            )
            
            _accessResult.value = result
            
            // Если доступ запрещен, показываем уведомление
            if (result == RouteAccessManager.RouteAccessResult.Denied) {
                // В реальном приложении здесь будет показ диалога или тоста
                println("Доступ к маршруту запрещен. Пройдите квиз для получения допуска.")
            }
        }
    }
    
    /**
     * Очищает выбранный маршрут и сбрасывает состояние
     */
    fun clearSelection() {
        _selectedRoute.value = null
        _accessResult.value = null
    }
    
    /**
     * Сбрасывает состояние при обновлении версии приложения
     */
    fun resetForVersionUpdate() {
        clearSelection()
    }
    
    /**
     * Проверяет, есть ли действующий допуск
     */
    suspend fun hasValidAccessPass(): Boolean {
        val currentTime = System.currentTimeMillis()
        return accessPassDao.hasValidAccessPass(_userId.value, currentTime)
    }
    
    /**
     * Получает действующий допуск
     */
    suspend fun getValidAccessPass(): AccessPass? {
        val currentTime = System.currentTimeMillis()
        return accessPassDao.getValidAccessPass(_userId.value, currentTime)
    }
    
    /**
     * Форматирует оставшееся время действия допуска
     * При сроке менее часа отображает в минутах
     */
    fun formatRemainingTime(): String {
        val currentTime = System.currentTimeMillis()
        val accessPass = getValidAccessPass()
        
        if (accessPass == null || !accessPass.isValid(currentTime)) {
            return "Допуск истек"
        }
        
        val remainingTime = accessPass.expiryDate - currentTime
        
        val days = TimeUnit.MILLISECONDS.toDays(remainingTime)
        val hours = TimeUnit.MILLISECONDS.toHours(remainingTime) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60
        
        return when {
            days > 0 -> "$days дн. $hours ч."
            hours > 0 -> "$hours ч."
            minutes > 0 -> "Осталось $minutes минут"
            else -> "Менее минуты"
        }
    }
    
    /**
     * Загружает действующий допуск
     */
    fun loadAccessPass() {
        viewModelScope.launch {
            _accessPass.value = getValidAccessPass()
        }
    }
}