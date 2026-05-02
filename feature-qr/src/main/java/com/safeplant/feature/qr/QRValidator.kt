package com.safeplant.feature.qr

/**
 * Валидатор QR-кодов формата "objectId|name"
 * objectId: только латинские буквы и цифры, длина 1–20
 * name: буквы (любого алфавита), цифры, пробелы, дефис, подчёркивание, точка; длина 1–50
 */
class QRValidator {

    private val objectIdRegex = Regex("^[A-Za-z0-9]{1,20}$")
    private val nameRegex = Regex("^[\\p{L}\\p{N}\\s\\-_.]{1,50}$")

    fun validate(qrCode: String): Boolean {
        if (qrCode.isBlank()) return false
        // Проверяем, что нет пробелов сразу перед или после разделителя
        if (qrCode.contains(" |") || qrCode.contains("| ")) return false
        val parts = qrCode.split("|")
        if (parts.size != 2) return false
        val objectId = parts[0]
        val name = parts[1]
        if (objectId.isEmpty() || name.isEmpty()) return false
        // ObjectId и name не должны содержать пробелов в начале/конце (они уже без пробелов, т.к. не было пробелов вокруг |)
        return objectIdRegex.matches(objectId) && nameRegex.matches(name)
    }

    fun extractObjectId(qrCode: String): String {
        return qrCode.split("|")[0].trim()
    }

    fun extractName(qrCode: String): String {
        return qrCode.split("|")[1].trim()
    }

    fun extractCoordinates(qrCode: String): Pair<Double, Double>? = null
}
