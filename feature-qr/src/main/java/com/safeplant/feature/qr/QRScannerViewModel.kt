package com.safeplant.feature.qr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.security.QRValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для сканера QR-кодов
 * Обрабатывает сканирование QR-кодов и проверяет их валидность
 */
class QRScannerViewModel @Inject constructor(
    private val qrCodeDao: QrCodeDao,
    private val qrValidator: QRValidator
) : ViewModel() {

    private val _scanResult = MutableStateFlow<ScanResult?>(null)
    val scanResult: StateFlow<ScanResult?> = _scanResult.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    /**
     * Обрабатывает QR-код
     * @param qrCode Текст QR-кода для обработки
     */
    fun processQRCode(qrCode: String) {
        viewModelScope.launch {
            _isScanning.value = true

            // Проверяем валидность формата QR-кода
            if (!qrValidator.validate(qrCode)) {
                _scanResult.value = ScanResult(error = "Некорректный формат QR-кода")
                _isScanning.value = false
                return@launch
            }

            // Извлекаем ID объекта из QR-кода
            val objectId = qrValidator.extractObjectId(qrCode)
            
            // Проверяем наличие объекта в базе данных
            val mapping = qrCodeDao.getByObjectId(objectId)

            if (mapping == null) {
                // Объект не найден - показываем ошибку
                _scanResult.value = ScanResult(
                    error = "Объект с ID $objectId не найден в базе данных",
                    warning = "Проверьте QR-код и попробуйте снова"
                )
            } else {
                // Объект найден - очищаем ошибки
                _scanResult.value = ScanResult(warning = null)
            }

            _isScanning.value = false
        }
    }

    /**
     * Останавливает сканирование
     */
    fun stopScanning() {
        viewModelScope.launch {
            _isScanning.value = false
            _scanResult.value = null
        }
    }

    /**
     * Очищает результат сканирования
     */
    fun clearResult() {
        _scanResult.value = null
    }
}

/**
 * Результат сканирования QR-кода
 */
data class ScanResult(
    val error: String? = null,
    val warning: String? = null
)