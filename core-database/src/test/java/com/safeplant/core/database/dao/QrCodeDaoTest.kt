package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QrCodeMapping
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class QrCodeDaoTest {
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
    fun insertAndGetQrCodeMapping() = runBlocking {
        val qrCodeMapping = QrCodeMapping(
            objectId = "obj1",
            name = "Объект 1",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        val id = qrCodeDao.insertOrUpdate(qrCodeMapping)
        val retrievedMapping = qrCodeDao.getByObjectId("obj1")
        
        assertEquals(id, retrievedMapping?.id)
        assertEquals("obj1", retrievedMapping?.objectId)
        assertEquals("Объект 1", retrievedMapping?.name)
        assertEquals(55.7558, retrievedMapping?.latitude, 0.0001)
        assertEquals(37.6173, retrievedMapping?.longitude, 0.0001)
    }

    @Test
    fun updateQrCodeMapping() = runBlocking {
        val qrCodeMapping = QrCodeMapping(
            objectId = "obj2",
            name = "Объект 2",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        qrCodeDao.insertOrUpdate(qrCodeMapping)
        
        val updatedMapping = qrCodeMapping.copy(name = "Обновленный объект 2")
        qrCodeDao.insertOrUpdate(updatedMapping)
        
        val retrievedMapping = qrCodeDao.getByObjectId("obj2")
        assertEquals("Обновленный объект 2", retrievedMapping?.name)
    }

    @Test
    fun getNonExistentQrCodeMapping() = runBlocking {
        val retrievedMapping = qrCodeDao.getByObjectId("nonexistent")
        assertNull(retrievedMapping)
    }

    @Test
    fun deleteAllQrCodeMappings() = runBlocking {
        val qrCodeMapping1 = QrCodeMapping(
            objectId = "obj3",
            name = "Объект 3",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        val qrCodeMapping2 = QrCodeMapping(
            objectId = "obj4",
            name = "Объект 4",
            latitude = 55.7558,
            longitude = 37.6173
        )
        
        qrCodeDao.insertOrUpdate(qrCodeMapping1)
        qrCodeDao.insertOrUpdate(qrCodeMapping2)
        
        qrCodeDao.deleteAll()
        
        val retrievedMapping1 = qrCodeDao.getByObjectId("obj3")
        val retrievedMapping2 = qrCodeDao.getByObjectId("obj4")
        
        assertNull(retrievedMapping1)
        assertNull(retrievedMapping2)
    }
}