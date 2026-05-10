package com.safeplant.feature.qr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.security.QRValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRScannerViewModel @Inject constructor(
    private val qrCodeDao: QrCodeDao
) : ViewModel() {

    private val _scanResult = MutableStateFlow<ScanResult?>(null)
    val scanResult: StateFlow<ScanResult?> = _scanResult.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    fun processQRCode(qrCode: String) {
        viewModelScope.launch {
            _isScanning.value = true

            if (!QRValidator.validate(qrCode)) {
                _scanResult.value = ScanResult(error = "Неверный формат QR-кода")
                _isScanning.value = false
                return@launch
            }

            val objectId = QRValidator.extractObjectId(qrCode)
            val mapping = qrCodeDao.getByObjectId(objectId)

            if (mapping == null) {
                _scanResult.value = ScanResult(error = "Объект не найден")
            } else {
                _scanResult.value = ScanResult(
                    success = true,
                    message = "Распознано: ${mapping.name}"
                )
            }

            _isScanning.value = false
        }
    }

    fun clearResult() {
        _scanResult.value = null
    }
}

data class ScanResult(
    val error: String? = null,
    val success: Boolean = false,
    val message: String? = null
)
