package com.safeplant.feature.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel для экрана профиля (упрощённая версия для компиляции)
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loaded)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isRootDetected = MutableStateFlow(false)
    val isRootDetected: StateFlow<Boolean> = _isRootDetected.asStateFlow()

    private val _showRootWarning = MutableStateFlow(false)
    val showRootWarning: StateFlow<Boolean> = _showRootWarning.asStateFlow()

    private val _accessPass = MutableStateFlow<AccessPassUi?>(null)
    val accessPass: StateFlow<AccessPassUi?> = _accessPass.asStateFlow()

    sealed class UiState {
        object Loading : UiState()
        object Loaded : UiState()
        object NoAccessPass : UiState()
        data class Error(val message: String) : UiState()
        object RootWarning : UiState()
    }

    data class AccessPassUi(
        val expiryDate: Long,
        val isValid: Boolean
    )

    fun loadProfile() {
        _uiState.value = UiState.Loaded
        // заглушка: нет реальной логики
    }

    fun showRootWarning() {
        _showRootWarning.value = true
        _uiState.value = UiState.RootWarning
    }

    fun hideRootWarning() {
        _showRootWarning.value = false
        _uiState.value = UiState.Loaded
    }

    fun resetAllAccessPasses() {
        _accessPass.value = null
        _uiState.value = UiState.NoAccessPass
    }

    fun isAccessAllowed(): Boolean = true
    fun isAccessToDangerousZonesAllowed(): Boolean = true

    fun calculateRemainingTime(accessPass: AccessPassUi): Long {
        val remaining = accessPass.expiryDate - System.currentTimeMillis()
        return if (remaining > 0) remaining else 0L
    }

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
