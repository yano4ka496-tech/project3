package tests.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Интеграционный тест для позиционирования через QR-коды
 * Проверяет работу с базой данных QR-картирования и сопоставления с координатами
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class QRPositioningTest {
    
    private lateinit var database: AppDatabase
    private lateinit var qrCodeDao: QrCodeDao
    
    @Before
    fun setup() {
        // Создаем тестовую базу данных в памяти
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        
        qrCodeDao = database.qrCodeDao()
    }
    
    @After
    fun tearDown() {
        database.close()
    }
    
    /**
     * Тест сохранения и получения QR-картирования
     */
    @Test
    fun `save and get qr mapping should work correctly`() {
        // Создаем тестовое QR-картирование
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        // Сохраняем в базу данных
        qrCodeDao.insert(mapping)
        
        // Получаем из базы данных
        val savedMapping = qrCodeDao.getByObjectId("123")
        
        // Проверяем, что данные совпадают
        assertNotNull(savedMapping)
        assertEquals("123", savedMapping?.objectId)
        assertEquals("Цех А", savedMapping?.name)
        assertEquals(55.7558, savedMapping?.latitude, 0.0001)
        assertEquals(37.6173, savedMapping?.longitude, 0.0001)
    }
    
    /**
     * Тест получения QR-картирования для несуществующего объекта
     */
    @Test
    fun `get non-existent qr mapping should return null`() {
        // Пытаемся получить несуществующее QR-картирование
        val mapping = qrCodeDao.getByObjectId("non_existent")
        
        // Проверяем, что возвращается null
        assertNull(mapping)
    }
    
    /**
     * Тест обновления QR-картирования
     */
    @Test
    fun `update qr mapping should work correctly`() {
        // Создаем тестовое QR-картирование
        val mapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        // Сохраняем в базу данных
        qrCodeDao.insert(mapping)
        
        // Обновляем данные
        val updatedMapping = mapping.copy(
            name = "Цех А (обновленный)",
            latitude = 55.7560,
            longitude = 37.6175
        )
        
        // Сохраняем обновленные данные
        qrCodeDao.insert(updatedMapping)
        
        // Получаем обновленные данные
        val savedMapping = qrCodeDao.getByObjectId("123")
        
        // Проверяем, что данные обновлены
        assertNotNull(savedMapping)
        assertEquals("Цех А (обновленный)", savedMapping?.name)
        assertEquals(55.7560, savedMapping?.latitude, 0.0001)
        assertEquals(37.6175, savedMapping?.longitude, 0.0001)
    }
    
    /**
     * Тест проверки формата QR-кода
     */
    @Test
    fun `qr code format validation should work correctly`() {
        // Тестируем валидацию QR-кодов
        val validQR = "123|Цех А"
        val invalidQR1 = "123" // Нет разделителя
        val invalidQR2 = "|Цех А" // Нет номера объекта
        val invalidQR3 = "123|" // Нет названия
        val invalidQR4 = "123|Цех А|Дополнительно" // Лишний разделитель
        
        // Проверяем валидные QR-коды
        assertTrue(isValidQRCode(validQR))
        
        // Проверяем невалидные QR-коды
        assertFalse(isValidQRCode(invalidQR1))
        assertFalse(isValidQRCode(invalidQR2))
        assertFalse(isValidQRCode(invalidQR3))
        assertFalse(isValidQRCode(invalidQR4))
    }
    
    /**
     * Тест извлечения номера объекта из QR-кода
     */
    @Test
    fun `extract object id from qr code should work correctly`() {
        val qrCode = "123|Цех А"
        val objectId = extractObjectIdFromQR(qrCode)
        
        assertEquals("123", objectId)
    }
    
    /**
     * Тест извлечения названия объекта из QR-кода
     */
    @Test
    fun `extract object name from qr code should work correctly`() {
        val qrCode = "123|Цех А"
        val objectName = extractObjectNameFromQR(qrCode)
        
        assertEquals("Цех А", objectName)
    }
    
    /**
     * Тест обработки некорректных QR-кодов (объект не найден)
     */
    @Test
    fun `handling non-existent qr code should show error`() {
        // Симулируем сканирование QR-кода для несуществующего объекта
        val qrCode = "999|Несуществующий объект"
        
        // Проверяем, что объект не найден
        val mapping = qrCodeDao.getByObjectId("999")
        assertNull(mapping)
        
        // В реальном приложении здесь будет отображение ошибки
        // Для теста проверяем, что возвращается null
    }
    
    /**
     * Вспомогательные функции для тестирования QR-валидации
     */
    private fun isValidQRCode(qrCode: String): Boolean {
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
    
    private fun extractObjectIdFromQR(qrCode: String): String {
        return qrCode.substringBefore("|")
    }
    
    private fun extractObjectNameFromQR(qrCode: String): String {
        return qrCode.substringAfter("|")
    }
}