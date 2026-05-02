package com.safeplant.feature.qr

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit-тесты для QRValidator
 */
class QRValidatorTest {
    
    private val validator = QRValidator()
    
    /**
     * Тестирование валидации корректных QR-кодов
     */
    @Test
    fun `validate should return true for valid QR codes`() {
        val validQrCodes = listOf(
            "123|ЦехА",
            "ABC123|Склад1",
            "aBc123|Лаборатория",
            "12345678901234567890|Название12345678901234567890123456789012345678901234567890",
            "1|A"
        )
        
        validQrCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тестирование валидации некорректных QR-кодов
     */
    @Test
    fun `validate should return false for invalid QR codes`() {
        val invalidQrCodes = listOf(
            "", // Пустая строка
            "123", // Нет разделителя
            "|ЦехА", // Нет objectId
            "123|", // Нет name
            "123|Цех А", // Пробел в objectId
            "123|ЦехА!", // Недопустимый символ в name
            "123456789012345678901|Название", // objectId слишком длинный
            "123|Название123456789012345678901234567890123456789012345678901", // name слишком длинный
            "123|Цех|А", // Более одного разделителя
            "123-ЦехА", // Неправильный разделитель
            "!@#|ЦехА", // Недопустимые символы в objectId
            "123|!@#" // Недопустимые символы в name
        )
        
        invalidQrCodes.forEach { qrCode ->
            assertFalse("QR-код '$qrCode' должен быть невалидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тестирование извлечения objectId из QR-кода
     */
    @Test
    fun `extractObjectId should return correct objectId`() {
        val testCases = listOf(
            Pair("123|ЦехА", "123"),
            Pair("ABC123|Склад1", "ABC123"),
            Pair("aBc123|Лаборатория", "aBc123"),
            Pair("12345678901234567890|Название12345678901234567890123456789012345678901234567890", "12345678901234567890")
        )
        
        testCases.forEach { (qrCode, expectedObjectId) ->
            assertEquals("ObjectId для '$qrCode' должен быть '$expectedObjectId'", 
                expectedObjectId, validator.extractObjectId(qrCode))
        }
    }
    
    /**
     * Тестирование извлечения name из QR-кода
     */
    @Test
    fun `extractName should return correct name`() {
        val testCases = listOf(
            Pair("123|ЦехА", "ЦехА"),
            Pair("ABC123|Склад1", "Склад1"),
            Pair("aBc123|Лаборатория", "Лаборатория"),
            Pair("123|Название12345678901234567890123456789012345678901234567890", "Название12345678901234567890123456789012345678901234567890")
        )
        
        testCases.forEach { (qrCode, expectedName) ->
            assertEquals("Name для '$qrCode' должен быть '$expectedName'", 
                expectedName, validator.extractName(qrCode))
        }
    }
    
    /**
     * Тестирование граничных случаев objectId
     */
    @Test
    fun `validate should handle edge cases for objectId`() {
        // Минимальная длина objectId
        assertTrue("ObjectId минимальной длины должен быть валидным", validator.validate("1|A"))
        
        // Максимальная длина objectId
        val maxObjectId = "a".repeat(20)
        assertTrue("ObjectId максимальной длины должен быть валидным", 
            validator.validate("$maxObjectId|A"))
        
        // ObjectId с минимальной длиной name
        assertTrue("ObjectId с минимальным name должен быть валидным", 
            validator.validate("123|A"))
        
        // ObjectId с максимальной длиной name
        val maxName = "a".repeat(50)
        assertTrue("ObjectId с максимальным name должен быть валидным", 
            validator.validate("123|$maxName"))
    }
    
    /**
     * Тестирование граничных случаев name
     */
    @Test
    fun `validate should handle edge cases for name`() {
        // Минимальная длина name
        assertTrue("Name минимальной длины должен быть валидным", validator.validate("1|A"))
        
        // Максимальная длина name
        val maxName = "a".repeat(50)
        assertTrue("Name максимальной длины должен быть валидным", 
            validator.validate("1|$maxName"))
    }
}