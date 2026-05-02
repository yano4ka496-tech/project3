package com.safeplant.feature.qr

import org.junit.Assert.*
import org.junit.Test

class QRValidatorTest {

    private val validator = QRValidator()

    @Test
    fun `validate should return true for valid QR codes`() {
        val validQrCodes = listOf(
            "123|ЦехА",
            "ABC123|Производственный участок",
            "a1b2c3d4e5|Тестовый объект",
            "1|Короткий идентификатор"
        )
        validQrCodes.forEach { qrCode ->
            assertTrue("QR-код '$qrCode' должен быть валидным", validator.validate(qrCode))
        }
    }

    @Test
    fun `validate should return false for invalid QR codes`() {
        val invalidQrCodes = listOf(
            "",
            " ",
            "123ЦехА",
            "123",
            "ЦехА",
            "123|ЦехА|Дополнительно",
            "123||ЦехА",
            "|ЦехА",
            "123!|ЦехА",
            "123|",
            "123|ЦехА!",
            " 123 | ЦехА ",
            "123 |ЦехА",
            "123| ЦехА"
        )
        invalidQrCodes.forEach { qrCode ->
            assertFalse("QR-код '$qrCode' должен быть невалидным", validator.validate(qrCode))
        }
    }

    @Test
    fun `extractObjectId should return correct objectId`() {
        val testCases = listOf(
            "123|ЦехА" to "123",
            "ABC123|Производственный участок" to "ABC123",
            "a1b2c3d4e5|Тестовый объект" to "a1b2c3d4e5",
            "1|Короткий идентификатор" to "1"
        )
        testCases.forEach { (qrCode, expected) ->
            assertEquals(expected, validator.extractObjectId(qrCode))
        }
    }

    @Test
    fun `extractName should return correct name`() {
        val testCases = listOf(
            "123|ЦехА" to "ЦехА",
            "ABC123|Производственный участок" to "Производственный участок",
            "a1b2c3d4e5|Тестовый объект" to "Тестовый объект",
            "1|Короткий идентификатор" to "Короткий идентификатор"
        )
        testCases.forEach { (qrCode, expected) ->
            assertEquals(expected, validator.extractName(qrCode))
        }
    }
}
