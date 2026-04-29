package com.safeplant.feature.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

/**
 * ViewModel для экрана профиля
 */
class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val accessPassDao = database.accessPassDao()
    
    // Состояние профиля
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val profileState: StateFlow<ProfileState> = _profileState
    
    // Действующий допуск
    private val _accessPass = MutableStateFlow<AccessPass?>(null)
    val accessPass: StateFlow<AccessPass?> = _accessPass
    
    // Сообщение об ошибке
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    /**
     * Состояния профиля
     */
    sealed class ProfileState {
        object Loading : ProfileState()
        object Loaded : ProfileState()
        object NoAccessPass : ProfileState()
        object Error : ProfileState()
    }
    
    /**
     * Загрузить профиль
     */
    fun loadProfile() {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading
            
            try {
                val currentTime = System.currentTimeMillis()
                val pass = accessPassDao.getValidAccessPass("default_user", currentTime)
                
                if (pass != null) {
                    _accessPass.value = pass
                    _profileState.value = ProfileState.Loaded
                } else {
                    _profileState.value = ProfileState.NoAccessPass
                }
                
            } catch (e: Exception) {
                _profileState.value = ProfileState.Error
                _errorMessage.value = "Ошибка при загрузке профиля: ${e.message}"
            }
        }
    }
    
    /**
     * Сбросить все допуски (при обновлении версии приложения)
     */
    fun resetAllAccessPasses() {
        viewModelScope.launch {
            try {
                accessPassDao.deleteAll()
                _accessPass.value = null
                _profileState.value = ProfileState.NoAccessPass
                _errorMessage.value = "Все допуски сброшены"
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка при сбросе допусков: ${e.message}"
            }
        }
    }
    
    /**
     * Проверить, нужно ли сбрасывать допуски при обновлении версии
     */
    fun shouldResetAccessPasses(currentVersion: String, storedVersion: String?): Boolean {
        // Если сохраненной версии нет, это первый запуск
        if (storedVersion == null) {
            return false
        }
        
        // Если версии не совпадают, сбрасываем допуски
        return currentVersion != storedVersion
    }
    
    /**
     * Рассчитать оставшееся время действия допуска
     */
    fun calculateRemainingTime(accessPass: AccessPass): Long {
        val currentTime = System.currentTimeMillis()
        val remainingTime = accessPass.expiryDate - currentTime
        
        return if (remainingTime > 0) remainingTime else 0L
    }
    
    /**
     * Форматировать оставшееся время в читаемый вид
     */
    fun formatRemainingTime(remainingTime: Long): String {
        val days = remainingTime / (1000 * 60 * 60 * 24)
        val hours = (remainingTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
        
        return when {
            days > 0 -> "$days дн. $hours ч."
            hours > 0 -> "$hours ч."
            else -> "Менее часа"
        }
    }
}