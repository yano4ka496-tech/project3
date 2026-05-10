package tests.unit

import com.safeplant.core.security.QRValidator
import org.junit.Assert.*
import org.junit.Test

/**
 * Тесты для валидатора QR-кодов
 * Альтернативная реализация тестов для QRValidator
 */
class TestQRValidator {
    
    private val validator = QRValidator()
    
    /**
     * Тест валидации корректных QR-кодов
     */
    @Test
    fun `validate should return true for valid QR codes`() {
        val validQRCodes = listOf(
            "123|Цех А",
            "ABC123|Склад Б",
            "XYZ789|Производственный участок",
            "A1B2C3|Точка СИЗ",
            "TEST123|Объект 1"
        )
        
        validQRCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тест валидации некорректных QR-кодов
     */
    @Test
    fun `validate should return false for invalid QR codes`() {
        val invalidQRCodes = listOf(
            // Неверный формат (неправильный разделитель)
            "123Цех А",
            "123|Цех|А",
            "|Цех А",
            "123|",
            
            // Неверный номер объекта (спецсимволы)
            "123!|Цех А",
            "123@|Цех А",
            "123#|Цех А",
            "123$|Цех А",
            "123%|Цех А",
            "123^|Цех А",
            "123&|Цех А",
            "123*|Цех А",
            "123(|Цех А",
            "123)|Цех А",
            "123-|Цех А",
            "123_|Цех А",
            "123+|Цех А",
            "123=|Цех А",
            "123{|Цех А",
            "123}|Цех А",
            "123[|Цех А",
            "123]|Цех А",
            "123;|Цех А",
            "123:|Цех А",
            "123\"|Цех А",
            "123'|Цех А",
            "123<|Цех А",
            "123>|Цех А",
            "123,|Цех А",
            "123.|Цех А",
            "123?|Цех А",
            "123/|Цех А",
            "123\\|Цех А",
            "123 |Цех А",
            "123\t|Цех А",
            "123\n|Цех А",
            
            // Неверное название (спецсимволы)
            "123|Цех!А",
            "123|Цех@А",
            "123|Цех#А",
            "123|Цех$А",
            "123|Цех%А",
            "123|Цех^А",
            "123|Цех&А",
            "123|Цех*А",
            "123|Цех(А",
            "123|Цех)А",
            "123|Цех-А",
            "123|Цех_А",
            "123|Цех+А",
            "123|Цех=А",
            "123|Цех{А",
            "123|Цех}А",
            "123|Цех[А",
            "123|Цех]А",
            "123|Цех;А",
            "123|Цех:А",
            "123|Цех\"А",
            "123|Цех'А",
            "123|Цех<А",
            "123|Цех>А",
            "123|Цех,А",
            "123|Цех.А",
            "123|Цех?А",
            "123|Цех/А",
            "123|Цех\\А",
            
            // Пустые значения
            "",
            "|",
            "123|",
            "|Цех А",
            
            // Пробелы в начале или конце названия
            "123| Цех А",
            "123|Цех А ",
            "123| Цех А ",
            
            // Только пробелы в названии
            "123|   ",
            "123|  ",
            "123| "
        )
        
        invalidQRCodes.forEach { qrCode ->
            assertFalse("QR-код '$qrCode' должен быть невалидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тест извлечения номера объекта из QR-кода
     */
    @Test
    fun `extractObjectId should return correct object id`() {
        val testCases = listOf(
            Pair("123|Цех А", "123"),
            Pair("ABC123|Склад Б", "ABC123"),
            Pair("XYZ789|Производственный участок", "XYZ789"),
            Pair("A1B2C3|Точка СИЗ", "A1B2C3"),
            Pair("TEST123|Объект 1", "TEST123")
        )
        
        testCases.forEach { (qrCode, expectedId) ->
            assertEquals("Неверный номер объекта для '$qrCode'", expectedId, validator.extractObjectId(qrCode))
        }
    }
    
    /**
     * Тест извлечения названия объекта из QR-кода
     */
    @Test
    fun `extractName should return correct object name`() {
        val testCases = listOf(
            Pair("123|Цех А", "Цех А"),
            Pair("ABC123|Склад Б", "Склад Б"),
            Pair("XYZ789|Производственный участок", "Производственный участок"),
            Pair("A1B2C3|Точка СИЗ", "Точка СИЗ"),
            Pair("TEST123|Объект 1", "Объект 1")
        )
        
        testCases.forEach { (qrCode, expectedName) ->
            assertEquals("Неверное название для '$qrCode'", expectedName, validator.extractName(qrCode))
        }
    }
    
    /**
     * Тест обработки QR-кода с пустым названием
     */
    @Test
    fun `extractName should return empty string for empty name`() {
        val qrCode = "123|"
        assertEquals("Название должно быть пустым", "", validator.extractName(qrCode))
    }
    
    /**
     * Тест обработки QR-кода с пустым номером
     */
    @Test
    fun `extractObjectId should return empty string for empty id`() {
        val qrCode = "|Цех А"
        assertEquals("Номер объекта должен быть пустым", "", validator.extractObjectId(qrCode))
    }
    
    /**
     * Тест обработки QR-кода с кириллическими символами
     */
    @Test
    fun `validate should return true for QR codes with Cyrillic characters`() {
        val validQRCodes = listOf(
            "123|Цех А",
            "ABC123|Склад Б",
            "XYZ789|Производственный участок",
            "A1B2C3|Точка СИЗ",
            "TEST123|Объект 1"
        )
        
        validQRCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тест обработки QR-кода с латинскими символами
     */
    @Test
    fun `validate should return true for QR codes with Latin characters`() {
        val validQRCodes = listOf(
            "123|Workshop A",
            "ABC123|Warehouse B",
            "XYZ789|Production Area",
            "A1B2C3|PPE Point",
            "TEST123|Object 1"
        )
        
        validQRCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тест обработки QR-кода с цифрами
     */
    @Test
    fun `validate should return true for QR codes with numbers in name`() {
        val validQRCodes = listOf(
            "123|Цех 1",
            "ABC123|Склад 2",
            "XYZ789|Участок 3",
            "A1B2C3|Точка 4",
            "TEST123|Объект 5"
        )
        
        validQRCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
    
    /**
     * Тест обработки QR-кода с пробелами в названии
     */
    @Test
    fun `validate should return true for QR codes with spaces in name`() {
        val validQRCodes = listOf(
            "123|Цех А",
            "ABC123|Склад Б",
            "XYZ789|Производственный участок",
            "A1B2C3|Точка СИЗ",
            "TEST123|Объект 1"
        )
        
        validQRCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }
}