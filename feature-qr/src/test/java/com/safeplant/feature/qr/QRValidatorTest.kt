package com.safeplant.feature.qr

import org.junit.Assert.*
import org.junit.Test

class QRValidatorTest {

    @Test
    fun `validateQRCode with valid format should return true`() {
        val validQRCode = "123|Цех А"
        val result = QRValidator().validateQRCode(validQRCode)
        assertTrue(result)
    }

    @Test
    fun `validateQRCode with invalid format missing pipe should return false`() {
        val invalidQRCode = "123Цех А"
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }

    @Test
    fun `validateQRCode with invalid format missing number should return false`() {
        val invalidQRCode = "|Цех А"
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }

    @Test
    fun `validateQRCode with invalid format missing name should return false`() {
        val invalidQRCode = "123|"
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }

    @Test
    fun `validateQRCode with non-numeric number should return false`() {
        val invalidQRCode = "ABC|Цех А"
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }

    @Test
    fun `validateQRCode with empty string should return false`() {
        val invalidQRCode = ""
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }

    @Test
    fun `validateQRCode with special characters in name should return true`() {
        val validQRCode = "123|Цех №1"
        val result = QRValidator().validateQRCode(validQRCode)
        assertTrue(result)
    }

    @Test
    fun `validateQRCode with multiple pipes should return false`() {
        val invalidQRCode = "123|Цех|А"
        val result = QRValidator().validateQRCode(invalidQRCode)
        assertFalse(result)
    }
}