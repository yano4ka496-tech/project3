package com.safeplant.feature.qr

import org.junit.Assert.*
import org.junit.Test

class QRValidatorTest {
    private val validator = QRValidator()

    @Test
    fun `validate should return true for valid QR code`() {
        assertTrue(validator.validate("123|Цех А"))
        assertTrue(validator.validate("ABC123|Название объекта"))
        assertTrue(validator.validate("a1b2c3|Название с пробелами"))
        assertTrue(validator.validate("12345678901234567890|Название из 50 символов 123456789012345678901234567890"))
    }

    @Test
    fun `validate should return false for empty QR code`() {
        assertFalse(validator.validate(""))
        assertFalse(validator.validate("||"))
    }

    @Test
    fun `validate should return false for invalid objectId`() {
        assertFalse(validator.validate("|Цех А")) // objectId пустой
        assertFalse(validator.validate("123 |Цех А")) // пробел перед разделителем
        assertFalse(validator.validate("123| Цех А")) // пробел после разделителя
        assertFalse(validator.validate("123-456|Цех А")) // дефис в objectId
        assertFalse(validator.validate("123 456|Цех А")) // пробел в objectId
        assertFalse(validator.validate("123!|Цех А")) // спецсимвол в objectId
        assertFalse(validator.validate("123456789012345678901|Цех А")) // objectId слишком длинный (21 символ)
    }

    @Test
    fun `validate should return false for invalid name`() {
        assertFalse(validator.validate("123|")) // name пустой
        assertFalse(validator.validate("123|Название из 51 символов 1234567890123456789012345678901")) // name слишком длинный
    }

    @Test
    fun `extractObjectId should return correct objectId`() {
        assertEquals("123", validator.extractObjectId("123|Цех А"))
        assertEquals("ABC123", validator.extractObjectId("ABC123|Название объекта"))
    }

    @Test
    fun `extractName should return correct name`() {
        assertEquals("Цех А", validator.extractName("123|Цех А"))
        assertEquals("Название объекта", validator.extractName("ABC123|Название объекта"))
    }
}