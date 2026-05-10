package com.safeplant.feature.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * ViewModel для экрана профиля пользователя
 * Управляет состоянием допуска, проверкой срока действия и обновления версии
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val context: Context,
    private val accessPassDao: AccessPassDao
) : ViewModel() {
    
    private val _accessStatus = MutableStateFlow<AccessStatus>(AccessStatus.Loading)
    val accessStatus: StateFlow<AccessStatus> = _accessStatus.asStateFlow()
    
    private val _remainingTime = MutableStateFlow<Long>(0)
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()
    
    private val _showVersionUpdateDialog = MutableStateFlow(false)
    val showVersionUpdateDialog: StateFlow<Boolean> = _showVersionUpdateDialog.asStateFlow()
    
    private val _currentVersion = MutableStateFlow(0)
    val currentVersion: StateFlow<Int> = _currentVersion.asStateFlow()
    
    private val _savedVersion = MutableStateFlow(0)
    val savedVersion: StateFlow<Int> = _savedVersion.asStateFlow()
    
    private val encryptedPrefs: SharedPreferences by lazy {
        // Используем EncryptedSharedPreferences для хранения чувствительных данных
        androidx.security.crypto.EncryptedSharedPreferences.create(
            context,
            "safeplant_encrypted_prefs",
            android.security.keystore.KeyProperties.KEY_ALGORITHM_AES,
            androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    
    /**
     * Состояние доступа к опасным зонам
     */
    sealed class AccessStatus {
        object Loading : AccessStatus()
        object Allowed : AccessStatus()
        object Denied : AccessStatus()
        object Expired : AccessStatus()
    }
    
    /**
     * Инициализация при создании ViewModel
     */
    init {
        loadAccessStatus()
        loadVersionInfo()
    }
    
    /**
     * Загружает статус доступа к опасным зонам
     */
    private fun loadAccessStatus() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val hasValidPass = accessPassDao.hasValidAccessPass("default_user", currentTime)
            
            _accessStatus.value = when {
                hasValidPass -> AccessStatus.Allowed
                else -> AccessStatus.Expired
            }
            
            // Обновляем оставшееся время
            updateRemainingTime()
        }
    }
    
    /**
     * Проверяет, разрешен ли доступ к опасным зонам
     */
    fun isAccessToDangerousZonesAllowed(): Boolean {
        return when (_accessStatus.value) {
            AccessStatus.Allowed -> true
            else -> false
        }
    }
    
    /**
     * Вычисляет оставшееся время действия допуска
     */
    fun calculateRemainingTime(): Long {
        val currentTime = System.currentTimeMillis()
        val accessPass = accessPassDao.getValidAccessPass("default_user", currentTime)
        
        return accessPass?.expiryDate?.minus(currentTime) ?: 0
    }
    
    /**
     * Форматирует оставшееся время в читаемом формате
     */
    fun formatRemainingTime(): String {
        val remaining = calculateRemainingTime()
        
        val days = TimeUnit.MILLISECONDS.toDays(remaining)
        val hours = TimeUnit.MILLISECONDS.toHours(remaining) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remaining) % 60
        
        return when {
            days > 0 -> "$days дн. $hours ч."
            hours > 0 -> "$hours ч."
            minutes > 0 -> "$minutes мин."
            else -> "Менее минуты"
        }
    }
    
    /**
     * Обновляет оставшееся время
     */
    private fun updateRemainingTime() {
        _remainingTime.value = calculateRemainingTime()
    }
    
    /**
     * Загружает информацию о версии приложения
     */
    private fun loadVersionInfo() {
        // Получаем текущую версию из PackageManager
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        _currentVersion.value = packageInfo.versionCode
        
        // Получаем сохраненную версию из EncryptedSharedPreferences
        _savedVersion.value = encryptedPrefs.getInt("app_version", 0)
    }
    
    /**
     * Проверяет, было ли обновление версии приложения
     * @return true, если версия изменилась
     */
    fun checkVersionAndResetAccessIfNeeded(): Boolean {
        val current = _currentVersion.value
        val saved = _savedVersion.value
        
        if (current != saved) {
            // Версия изменилась - показываем диалог обновления
            _showVersionUpdateDialog.value = true
            return true
        }
        
        return false
    }
    
    /**
     * Подтверждает обновление версии и сбрасывает данные
     */
    fun confirmVersionUpdate() {
        // Сохраняем новую версию
        encryptedPrefs.edit().putInt("app_version", _currentVersion.value).apply()
        
        // Сбрасываем допуски
        viewModelScope.launch {
            accessPassDao.resetAllAccessPasses()
        }
        
        // Закрываем диалог
        _showVersionUpdateDialog.value = false
        
        // Обновляем статус
        loadAccessStatus()
    }
    
    /**
     * Отменяет обновление версии
     */
    fun cancelVersionUpdate() {
        _showVersionUpdateDialog.value = false
    }
    
    /**
     * Обновляет статус доступа
     */
    fun updateAccessStatus() {
        loadAccessStatus()
    }
}