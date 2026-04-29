package com.safeplant.feature.qr

/**
 * Валидатор QR-кодов
 */
class QRValidator {
    
    /**
     * Валидация формата QR-кода
     * Ожидаемый формат: objectId|name
     */
    fun validate(qrCode: String): Boolean {
        if (qrCode.isEmpty()) {
            return false
        }
        
        // Проверяем наличие разделителя |
        val parts = qrCode.split("|")
        if (parts.size != 2) {
            return false
        }
        
        // Проверяем, что objectId не пустой
        val objectId = parts[0].trim()
        if (objectId.isEmpty()) {
            return false
        }
        
        // Проверяем, что name не пустой
        val name = parts[1].trim()
        if (name.isEmpty()) {
            return false
        }
        
        // Проверяем objectId на наличие недопустимых символов
        if (!isValidObjectId(objectId)) {
            return false
        }
        
        return true
    }
    
    /**
     * Извлечение objectId из QR-кода
     */
    fun extractObjectId(qrCode: String): String {
        return qrCode.split("|")[0].trim()
    }
    
    /**
     * Извлечение name из QR-кода
     */
    fun extractName(qrCode: String): String {
        return qrCode.split("|")[1].trim()
    }
    
    /**
     * Извлечение координат из QR-кода (если они есть)
     * Возвращает Pair(latitude, longitude) или null
     */
    fun extractCoordinates(qrCode: String): Pair<Double, Double>? {
        // В текущей реализации QR-коды не содержат координат
        // Этот метод оставлен для будущего расширения
        return null
    }
    
    /**
     * Проверка objectId на допустимые символы
     * Разрешены: цифры, буквы, дефисы, подчеркивания
     */
    private fun isValidObjectId(objectId: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9_-]+$")
        return regex.matches(objectId)
    }
}