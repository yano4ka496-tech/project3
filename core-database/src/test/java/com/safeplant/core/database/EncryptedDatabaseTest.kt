package com.safeplant.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.database.entity.QuizResult
import com.safeplant.core.database.entity.TrainingVideo
import kotlinx.coroutines.runBlocking
 org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class EncryptedDatabaseTest {
    private lateinit var database: AppDatabase
    private lateinit var accessPassDao: AccessPassDao
    private lateinit var quizResultDao: QuizResultDao
    private lateinit var trainingVideoDao: TrainingVideoDao
    private lateinit var qrCodeDao: QrCodeDao

    @Before
    fun setup() {
        // Создаем базу данных без шифрования для тестирования
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        
        accessPassDao = database.accessPassDao()
        quizResultDao = database.quizResultDao()
        trainingVideoDao = database.trainingVideoDao()
        qrCodeDao = database.qrCodeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAccessPassOperations() = runBlocking {
        // Создание допуска
        val accessPass = AccessPass(
            userId = "test_user",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)),
            isValid = true
        )
        
        val id = accessPassDao.insert(accessPass)
        assertNotNull(id)
        
        // Получение допуска
        val retrievedPass = accessPassDao.getValidById("test_user", Date())
        assertNotNull(retrievedPass)
        assertEquals("test_user", retrievedPass?.userId)
        assertEquals(true, retrievedPass?.isValid)
        
        // Обновление допуска
        val updatedPass = retrievedPass?.copy(isValid = false)
        updatedPass?.let {
            accessPassDao.updateValidity(it)
        }
        
        val invalidatedPass = accessPassDao.getValidById("test_user", Date())
        assertEquals(false, invalidatedPass?.isValid)
    }

    @Test
    fun testQuizResultOperations() = runBlocking {
        // Создание результата квиза
        val quizResult = QuizResult(
            userId = "test_user",
            score = 8,
            passed = true,
            timestamp = Date()
        )
        
        val id = quizResultDao.insert(quizResult)
        assertNotNull(id)
        
        // Получение результатов
        val results = quizResultDao.getByUser("test_user")
        assertEquals(1, results.size)
        assertEquals(8, results.first().score)
        assertEquals(true, results.first().passed)
    }

    @Test
    fun testTrainingVideoOperations() = runBlocking {
        // Создание видео
        val trainingVideo = TrainingVideo(
            title = "Тестовое видео",
            fileName = "test_video.mp4",
            duration = 120
        )
        
        val id = trainingVideoDao.insert(trainingVideo)
        assertNotNull(id)
        
        // Получение видео
        val retrievedVideo = trainingVideoDao.getById(id)
        assertNotNull(retrievedVideo)
        assertEquals("Тестовое видео", retrievedVideo?.title)
        assertEquals("test_video.mp4", retrievedVideo?.fileName)
        assertEquals(120, retrievedVideo?.duration)
    }

    @Test
    fun testQrCodeMappingOperations() = runBlocking {
        // Создание сопоставления QR-кода
        val qrCodeMapping = QrCodeMapping(
            objectId = "test_obj",
            name = "Тестовый объект",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        val id = qrCodeDao.insertOrUpdate(qrCodeMapping)
        assertNotNull(id)
        
        // Получение сопоставления
        val retrievedMapping = qrCodeDao.getByObjectId("test_obj")
        assertNotNull(retrievedMapping)
        assertEquals("test_obj", retrievedMapping?.objectId)
        assertEquals("Тестовый объект", retrievedMapping?.name)
        assertEquals(55.7558, retrievedMapping?.latitude, 0.0001)
        assertEquals(37.6173, retrievedMapping?.longitude, 0.0001)
    }

    @Test
    fun testDatabaseMigration() {
        // Проверка, что все таблицы созданы
        val tables = database.openHelper.readableDatabase
            .columnNames("sqlite_master")
        
        assertTrue(tables.contains("access_pass"))
        assertTrue(tables.contains("quiz_result"))
        assertTrue(tables.contains("training_video"))
        assertTrue(tables.contains("qr_code_mappings"))
    }
}