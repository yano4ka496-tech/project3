package com.safeplant.core.security

/**
 * Валидатор QR-кодов формата "номер_объекта|название"
 *
 * Правила:
 * - номер объекта: только латинские буквы и цифры (без пробелов и спецсимволов)
 * - разделитель: ровно один символ '|'
 * - название: буквы (латиница/кириллица), цифры и пробелы (но не в начале и не в конце), без других спецсимволов
 */
class QRValidator {
    /**
     * Проверяет, соответствует ли QR-код формату
     */
    fun validate(qrCode: String): Boolean {
        val parts = qrCode.split("|")
        if (parts.size != 2) return false

        val id = parts[0]
        val name = parts[1]

        // Номер: только латинские буквы и цифры, не пустой
        if (!id.matches(Regex("^[A-Za-z0-9]+$"))) return false

        // Название: не пустое, не начинается и не заканчивается пробелом,
        // содержит только буквы, цифры и пробелы
        if (name.isBlank()) return false
        if (name.startsWith(' ') || name.endsWith(' ')) return false
        if (!name.matches(Regex("^[\\p{L}\\p{N} ]+$"))) return false

        return true
    }

    /**
     * Извлекает номер объекта из QR-кода
     */
    fun extractObjectId(qrCode: String): String {
        return qrCode.substringBefore("|")
    }

    /**
     * Извлекает название объекта из QR-кода
     */
    fun extractName(qrCode: String): String {
        return qrCode.substringAfter("|")
    }
}
