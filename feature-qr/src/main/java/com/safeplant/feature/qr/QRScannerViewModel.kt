package com.safeplant.feature.qr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.DatabaseKeyManager
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.security.QRValidator
import kotlinx.coroutines.launch

/**
 * ViewModel для сканера QR-кодов
 */
class QRScannerViewModel(application: Application) : AndroidViewModel(application) {
    
    private val qrCodeDao: QrCodeDao
    private val qrValidator: QRValidator
    
    private val _qrScanResult = MutableLiveData<QrScanResult>()
    val qrScanResult: LiveData<QrScanResult> = _qrScanResult
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    init {
        // Инициализация зависимостей
        val keyManager = DatabaseKeyManager(application)
        val database = AppDatabase.getEncryptedDatabase(application, keyManager)
        qrCodeDao = database.qrCodeDao()
        qrValidator = QRValidator(qrCodeDao)
    }
    
    /**
     * Обработка результата сканирования QR-кода
     */
    fun onQrCodeScanned(qrContent: String) {
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                // Проверяем формат QR-кода
                if (!qrValidator.validateQRCodeFormat(qrContent)) {
                    _qrScanResult.value = QrScanResult.Error("Неверный формат QR-кода")
                    return@launch
                }
                
                // Проверяем существование QR-кода в базе данных
                val qrMapping = qrValidator.validateQRCodeExists(qrContent)
                
                if (qrMapping == null) {
                    _qrScanResult.value = QrScanResult.Error("QR-код не найден в базе данных. Пожалуйста, повторите сканирование.")
                } else {
                    _qrScanResult.value = QrScanResult.Success(qrMapping)
                }
            } catch (e: Exception) {
                _qrScanResult.value = QrScanResult.Error("Ошибка при обработке QR-кода: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Сброс результата сканирования
     */
    fun resetScanResult() {
        _qrScanResult.value = null
    }
    
    /**
     * Результат сканирования QR-кода
     */
    sealed class QrScanResult {
        data class Success(val qrMapping: QrCodeMapping) : QrScanResult()
        data class Error(val message: String) : QrScanResult()
    }
}