package com.safeplant.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.feature.qr.QRValidator
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QrCodePositioningTest {
    
    private lateinit var database: AppDatabase
    private lateinit var qrCodeDao: QrCodeDao
    
    @Before
    fun setup() {
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
    
    @Test
    fun testInsertAndGetByObjectId() = runBlocking {
        val qrMapping = QrCodeMapping(
            objectId = "123",
            name = "Цех А",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        // Вставляем сопоставление
        qrCodeDao.insertOrUpdate(qrMapping)
        
        // Получаем по objectId
        val retrieved = qrCodeDao.getByObjectId("123")
        
        assertNotNull(retrieved)
        assertEquals("123", retrieved?.objectId)
        assertEquals("Цех А", retrieved?.name)
        assertEquals(55.7558, retrieved?.latitude, 0.0001)
        assertEquals(37.6173, retrieved?.longitude, 0.0001)
    }
    
    @Test
    fun testInsertAndGetByCoordinates() = runBlocking {
        val qrMapping = QrCodeMapping(
            objectId = "456",
            name = "Цех Б",
            latitude = 55.7560,
            longitude = 37.6180
        )
        
        // Вставляем сопоставление
        qrCodeDao.insertOrUpdate(qrMapping)
        
        // Получаем по координатам
        val retrieved = qrCodeDao.getByCoordinates(55.7560, 37.6180)
        
        assertNotNull(retrieved)
        assertEquals("456", retrieved?.objectId)
        assertEquals("Цех Б", retrieved?.name)
        assertEquals(55.7560, retrieved?.latitude, 0.0001)
        assertEquals(37.6180, retrieved?.longitude, 0.0001)
    }
    
    @Test
    fun testGetByQrCodeValid() = runBlocking {
        val qrMapping = QrCodeMapping(
            objectId = "789",
            name = "Цех В",
            latitude = 55.7550,
            longitude = 37.6150
        )
        
        // Вставляем сопоставление
        qrCodeDao.insertOrUpdate(qrMapping)
        
        // Получаем по QR-коду
        val qrCode = "789|Цех В"
        val retrieved = qrCodeDao.getByQrCode(qrCode)
        
        assertNotNull(retrieved)
        assertEquals("789", retrieved?.objectId)
        assertEquals("Цех В", retrieved?.name)
        assertEquals(55.7550, retrieved?.latitude, 0.0001)
        assertEquals(37.6150, retrieved?.longitude, 0.0001)
    }
    
    @Test
    fun testGetByQrCodeInvalidFormat() = runBlocking {
        // Вставляем сопоставление
        val qrMapping = QrCodeMapping(
            objectId = "999",
            name = "Цех Г",
            latitude = 55.7540,
            longitude = 37.6140
        )
        qrCodeDao.insertOrUpdate(qrMapping)
        
        // Пытаемся получить по некорректному QR-коду
        val invalidQrCodes = listOf(
            "", // пустой
            "123", // нет разделителя
            "123|", // нет name
            "|Цех А", // нет objectId
            "123||Цех А", // два разделителя
            "123|Цех А|extra", // лишние данные
            "123|Цех А|extra|more" // лишние данные
        )
        
        for (qrCode in invalidQrCodes) {
            val retrieved = qrCodeDao.getByQrCode(qrCode)
            assertNull(retrieved)
        }
    }
    
    @Test
    fun testGetByQrCodeNonExistent() = runBlocking {
        // Вставляем сопоставление
        val qrMapping = QrCodeMapping(
            objectId = "111",
            name = "Цех Д",
            latitude = 55.7530,
            longitude = 37.6130
        )
        qrCodeDao.insertOrUpdate(qrMapping)
        
        // Пытаемся получить по несуществующему objectId
        val qrCode = "999|Цех Е"
        val retrieved = qrCodeDao.getByQrCode(qrCode)
        assertNull(retrieved)
    }
    
    @Test
    fun testQRValidator() {
        val validator = QRValidator()
        
        // Тесты валидации
        val validQrCodes = listOf(
            "123|Цех А",
            "abc_123|Цех Б",
            "abc-123|Цех В",
            "ABC_123|Цех Г"
        )
        
        for (qrCode in validQrCodes) {
            assert(validator.validate(qrCode))
        }
        
        // Тесты невалидации
        val invalidQrCodes = listOf(
            "", // пустой
            "123", // нет разделителя
            "123|", // нет name
            "|Цех А", // нет objectId
            "123||Цех А", // два разделителя
            "123|Цех А|extra", // лишние данные
            "123|Цех А|extra|more", // лишние данные
            "123@Цех А", // недопустимый символ
            "123|Цех@А", // недопустимый символ
            " 123 | Цех А", // пробелы в objectId
            "123 | Цех А " // пробелы в name
        )
        
        for (qrCode in invalidQrCodes) {
            assert(!validator.validate(qrCode))
        }
        
        // Тест извлечения objectId
        assertEquals("123", validator.extractObjectId("123|Цех А"))
        assertEquals("abc_123", validator.extractObjectId("abc_123|Цех Б"))
        
        // Тест извлечения name
        assertEquals("Цех А", validator.extractName("123|Цех А"))
        assertEquals("Цех Б", validator.extractName("abc_123|Цех Б"))
    }
    
    @Test
    fun testGetByNonExistentObjectId() = runBlocking {
        val retrieved = qrCodeDao.getByObjectId("999")
        assertNull(retrieved)
    }
    
    @Test
    fun testGetByNonExistentCoordinates() = runBlocking {
        val retrieved = qrCodeDao.getByCoordinates(99.9999, 99.9999)
        assertNull(retrieved)
    }
    
    @Test
    fun testUpdateExistingMapping() = runBlocking {
        val originalMapping = QrCodeMapping(
            objectId = "789",
            name = "Цех В",
            latitude = 55.7550,
            longitude = 37.6150
        )
        
        // Вставляем исходное сопоставление
        qrCodeDao.insertOrUpdate(originalMapping)
        
        // Обновляем с новыми координатами
        val updatedMapping = originalMapping.copy(
            latitude = 55.7551,
            longitude = 37.6151
        )
        
        qrCodeDao.insertOrUpdate(updatedMapping)
        
        // Проверяем, что обновление прошло успешно
        val retrievedById = qrCodeDao.getByObjectId("789")
        assertNotNull(retrievedById)
        assertEquals(55.7551, retrievedById?.latitude, 0.0001)
        assertEquals(37.6151, retrievedById?.longitude, 0.0001)
        
        // Проверяем, что старые координаты больше не возвращают результат
        val retrievedByOldCoords = qrCodeDao.getByCoordinates(55.7550, 37.6150)
        assertNull(retrievedByOldCoords)
    }
    
    @Test
    fun testMultipleMappings() = runBlocking {
        val mappings = listOf(
            QrCodeMapping(
                objectId = "001",
                name = "Зона 1",
                latitude = 55.7550,
                longitude = 37.6150
            ),
            QrCodeMapping(
                objectId = "002",
                name = "Зона 2",
                latitude = 55.7560,
                longitude = 37.6160
            ),
            QrCodeMapping(
                objectId = "003",
                name = "Зона 3",
                latitude = 55.7570,
                longitude = 37.6170
            )
        )
        
        // Вставляем все сопоставления
        mappings.forEach { qrCodeDao.insertOrUpdate(it) }
        
        // Проверяем, что все сопоставления сохранены
        val allMappings = qrCodeDao.getAll()
        assertEquals(3, allMappings.size)
        
        // Проверяем поиск по каждому objectId
        mappings.forEach { mapping ->
            val retrieved = qrCodeDao.getByObjectId(mapping.objectId)
            assertNotNull(retrieved)
            assertEquals(mapping.objectId, retrieved?.objectId)
            assertEquals(mapping.name, retrieved?.name)
            assertEquals(mapping.latitude, retrieved?.latitude, 0.0001)
            assertEquals(mapping.longitude, retrieved?.longitude, 0.0001)
        }
        
        // Проверяем поиск по координатам
        mappings.forEach { mapping ->
            val retrieved = qrCodeDao.getByCoordinates(mapping.latitude, mapping.longitude)
            assertNotNull(retrieved)
            assertEquals(mapping.objectId, retrieved?.objectId)
            assertEquals(mapping.name, retrieved?.name)
            assertEquals(mapping.latitude, retrieved?.latitude, 0.0001)
            assertEquals(mapping.longitude, retrieved?.longitude, 0.0001)
        }
    }
}