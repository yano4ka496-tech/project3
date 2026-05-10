package com.safeplant.feature.qr

/**
 * Валидатор QR-кодов формата "objectId|name"
 * objectId: только латинские буквы и цифры, длина 1-20
 * name: любые символы, длина 1-50
 */
class QRValidator {
    private val objectIdRegex = Regex("^[A-Za-z0-9]{1,20}$")
    private val nameRegex = Regex("^[\\s\\S]{1,50}$") // Разрешает любые символы, включая пробелы, и ограничивает длину 1-50

    fun validate(qrCode: String): Boolean {
        if (qrCode.isBlank()) return false
        
        // Проверяем, что нет пробелов сразу перед или после разделителя
        if (qrCode.contains(" |") || qrCode.contains("| ")) return false
        
        val parts = qrCode.split("|")
        if (parts.size != 2) return false
        
        val objectId = parts[0]
        val name = parts[1]
        
        // Проверяем, что objectId и name не пустые
        if (objectId.isEmpty() || name.isEmpty()) return false
        
        // Проверяем objectId и name по регулярным выражениям
        return objectIdRegex.matches(objectId) && nameRegex.matches(name)
    }

    fun extractObjectId(qrCode: String): String {
        return qrCode.split("|")[0].trim()
    }

    fun extractName(qrCode: String): String {
        return qrCode.split("|")[1].trim()
    }
}