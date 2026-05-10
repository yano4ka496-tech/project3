package com.safeplant.feature.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.security.PositionStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accessPassDao: AccessPassDao,
    private val positionStorage: PositionStorage,
    private val application: Application
) : ViewModel() {

    private val _accessPass = MutableStateFlow<AccessPass?>(null)
    val accessPass: StateFlow<AccessPass?> = _accessPass.asStateFlow()

    private val _appVersion = MutableStateFlow("")
    val appVersion: StateFlow<String> = _appVersion.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val userId = "current_user"  // TODO: заменить на реальный userId
            val pass = accessPassDao.getValidAccessPass(userId, currentTime)
            _accessPass.value = pass

            val versionName = application.packageManager.getPackageInfo(application.packageName, 0).versionName
            _appVersion.value = versionName ?: "1.0"
        }
    }

    fun resetAccessPass() {
        viewModelScope.launch {
            accessPassDao.deleteAll()
            _accessPass.value = null
        }
    }

    fun clearPosition() {
        viewModelScope.launch {
            positionStorage.clearPosition()
        }
    }
}
