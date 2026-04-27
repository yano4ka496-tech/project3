package com.safeplant.feature.profile

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accessPassDao: AccessPassDao
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState
    
    private val _currentVersion = MutableStateFlow<String>("")
    val currentVersion: StateFlow<String> = _currentVersion
    
    init {
        checkVersionAndResetPasses()
        loadCurrentVersion()
    }
    
    /**
     * Проверяет версию приложения и сбрасывает допуски при обновлении
     */
    private fun checkVersionAndResetPasses() {
        viewModelScope.launch {
            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_ACTIVITIES
                )
                val currentVersionName = packageInfo.versionName
                
                // Загружаем сохраненную версию
                val savedVersion = getSavedVersion()
                
                // Если версии не совпадают, сбрасываем допуски
                if (savedVersion != currentVersionName) {
                    accessPassDao.deleteAll()
                    saveVersion(currentVersionName)
                    _uiState.value = ProfileUiState.PassesReset
                } else {
                    _uiState.value = ProfileUiState.Ready
                }
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error("Ошибка проверки версии: ${e.message}")
            }
        }
    }
    
    /**
     * Загружает текущую версию приложения
     */
    private fun loadCurrentVersion() {
        viewModelScope.launch {
            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_ACTIVITIES
                )
                _currentVersion.value = packageInfo.versionName ?: "неизвестно"
            } catch (e: Exception) {
                _currentVersion.value = "неизвестно"
            }
        }
    }
    
    /**
     * Сохраняет версию приложения
     */
    private fun saveVersion(version: String) {
        // В реальном приложении здесь будет использоваться EncryptedSharedPreferences
        // Для простоты используем обычные SharedPreferences
        val prefs = context.getSharedPreferences("app_version", Context.MODE_PRIVATE)
        prefs.edit().putString("version", version).apply()
    }
    
    /**
     * Загружает сохраненную версию приложения
     */
    private fun getSavedVersion(): String {
        val prefs = context.getSharedPreferences("app_version", Context.MODE_PRIVATE)
        return prefs.getString("version", "") ?: ""
    }
    
    /**
     * Сбрасывает все допуски вручную
     */
    fun resetAllPasses() {
        viewModelScope.launch {
            accessPassDao.deleteAll()
            _uiState.value = ProfileUiState.PassesReset
        }
    }
    
    sealed class ProfileUiState {
        object Loading : ProfileUiState()
        object Ready : ProfileUiState()
        object PassesReset : ProfileUiState()
        data class Error(val message: String) : ProfileUiState()
    }
}