package com.safeplant.core.security

import org.junit.Assert.*
import org.junit.Test

/**
 * Тесты для QRValidator
 * Проверяют валидацию QR-кодов согласно формату "номер_объекта|название"
 */
class QRValidatorTest {
    
    private val validator = QRValidator()
    
    @Test
    fun testValidQRCode() {
        val validQR = "123|Цех А"
        assertTrue(validator.validate(validQR))
        assertEquals("123", validator.extractObjectId(validQR))
        assertEquals("Цех А", validator.extractName(validQR))
    }
    
    @Test
    fun testValidQRCodeWithNumbers() {
        val validQR = "ABC123|Зона производства"
        assertTrue(validator.validate(validQR))
        assertEquals("ABC123", validator.extractObjectId(validQR))
        assertEquals("Зона производства", validator.extractName(validQR))
    }
    
    @Test
    fun testInvalidQRCodeMissingSeparator() {
        val invalidQR = "123Цех А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeMultipleSeparators() {
        val invalidQR = "123|Цех|А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeEmptyObjectId() {
        val invalidQR = "|Цех А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeEmptyName() {
        val invalidQR = "123|"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeObjectIdWithSpaces() {
        val invalidQR = "123 Цех|Цех А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeObjectIdWithSpecialChars() {
        val invalidQR = "123#Цех|Цех А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeNameWithSpecialChars() {
        val invalidQR = "123|Цех#А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeNameLeadingSpace() {
        val invalidQR = "123| Цех А"
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testInvalidQRCodeNameTrailingSpace() {
        val invalidQR = "123|Цех А "
        assertFalse(validator.validate(invalidQR))
    }
    
    @Test
    fun testValidQRCodeWithCyrillic() {
        val validQR = "123|Цех производства"
        assertTrue(validator.validate(validQR))
        assertEquals("123", validator.extractObjectId(validQR))
        assertEquals("Цех производства", validator.extractName(validQR))
    }
    
    @Test
    fun testValidQRCodeWithMixedCase() {
        val validQR = "AbC123|Производственный цех"
        assertTrue(validator.validate(validQR))
        assertEquals("AbC123", validator.extractObjectId(validQR))
        assertEquals("Производственный цех", validator.extractName(validQR))
    }
    
    @Test
    fun testExtractObjectId() {
        val qrCode = "123|Цех А"
        assertEquals("123", validator.extractObjectId(qrCode))
    }
    
    @Test
    fun testExtractName() {
        val qrCode = "123|Цех А"
        assertEquals("Цех А", validator.extractName(qrCode))
    }
    
    @Test
    fun testExtractObjectIdWithoutName() {
        val qrCode = "123|"
        assertEquals("123", validator.extractObjectId(qrCode))
    }
    
    @Test
    fun testExtractNameWithoutObjectId() {
        val qrCode = "|Цех А"
        assertEquals("Цех А", validator.extractName(qrCode))
    }
}