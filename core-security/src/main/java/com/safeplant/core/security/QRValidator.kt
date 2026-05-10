package com.safeplant.core.security

/**
 * Валидатор QR-кодов формата "номер_объекта|название"
 */
object QRValidator {

    fun validate(qrCode: String): Boolean {
        val parts = qrCode.split("|")
        if (parts.size != 2) return false

        val id = parts[0]
        val name = parts[1]

        if (!id.matches(Regex("^[A-Za-z0-9]+$"))) return false
        if (name.isBlank()) return false
        if (name.startsWith(' ') || name.endsWith(' ')) return false
        if (!name.matches(Regex("^[\\p{L}\\p{N} ]+$"))) return false

        return true
    }

    fun extractObjectId(qrCode: String): String = qrCode.substringBefore("|")
    fun extractName(qrCode: String): String = qrCode.substringAfter("|")
}
