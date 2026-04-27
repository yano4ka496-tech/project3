package com.safeplant.core.security

import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Валидатор QR-кодов с проверкой существования в базе данных
 */
class QRValidator(
    private val qrCodeDao: QrCodeDao
) {
    
    companion object {
        private const val QR_PATTERN = "^\\d+\\|.+"
    }
    
    /**
     * Проверяет формат QR-кода: номер_объекта|название
     */
    fun validateQRCodeFormat(qrContent: String): Boolean {
        val pattern = Regex(QR_PATTERN)
        return pattern.matches(qrContent)
    }
    
    /**
     * Парсит QR-код и возвращает пару (ID зоны, название зоны)
     * @return Pair<String, String> или null, если парсинг не удался
     */
    fun parseQRCode(qrContent: String): Pair<String, String>? {
        if (!validateQRCodeFormat(qrContent)) {
            return null
        }
        
        val parts = qrContent.split("|")
        if (parts.size != 2) {
            return null
        }
        
        val objectId = parts[0].trim()
        val name = parts[1].trim()
        
        // Проверка на пустые значения
        if (objectId.isEmpty() || name.isEmpty()) {
            return null
        }
        
        return Pair(objectId, name)
    }
    
    /**
     * Проверяет существование QR-кода в базе данных
     * @return QrCodeMapping или null, если QR-код не найден
     */
    suspend fun validateQRCodeExists(qrContent: String): QrCodeMapping? = withContext(Dispatchers.IO) {
        val parsed = parseQRCode(qrContent) ?: return@withContext null
        qrCodeDao.getByObjectId(parsed.first)
    }
    
    /**
     * Валидирует ID зоны для навигации
     * @return true, если ID корректен
     */
    fun validateZoneId(zoneId: String): Boolean {
        // Проверяем, что ID не пустой и содержит только цифры
        return zoneId.isNotEmpty() && zoneId.all { Character.isDigit(it) }
    }
}