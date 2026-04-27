package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.AccessPass
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.Date

class AccessPassDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var accessPassDao: AccessPassDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        accessPassDao = database.accessPassDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetAccessPass() = runBlocking {
        val accessPass = AccessPass(
            userId = "user1",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000),
            isValid = true
        )
        
        val id = accessPassDao.insert(accessPass)
        val retrievedPass = accessPassDao.getValidById("user1", Date())
        
        assertEquals(id, retrievedPass?.id)
        assertEquals("user1", retrievedPass?.userId)
        assertEquals(true, retrievedPass?.isValid)
    }

    @Test
    fun getExpiredAccessPass() = runBlocking {
        val expiredDate = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)
        val accessPass = AccessPass(
            userId = "user2",
            issuedAt = expiredDate,
            expiryDate = expiredDate,
            isValid = true
        )
        
        accessPassDao.insert(accessPass)
        val retrievedPass = accessPassDao.getValidById("user2", Date())
        
        assertNull(retrievedPass)
    }

    @Test
    fun updateAccessPassValidity() = runBlocking {
        val accessPass = AccessPass(
            userId = "user3",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000),
            isValid = true
        )
        
        accessPassDao.insert(accessPass)
        
        val updatedPass = accessPass.copy(isValid = false)
        accessPassDao.updateValidity(updatedPass)
        
        val retrievedPass = accessPassDao.getValidById("user3", Date())
        assertEquals(false, retrievedPass?.isValid)
    }

    @Test
    fun deleteAllAccessPasses() = runBlocking {
        val accessPass1 = AccessPass(
            userId = "user4",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000),
            isValid = true
        )
        
        val accessPass2 = AccessPass(
            userId = "user5",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000),
            isValid = true
        )
        
        accessPassDao.insert(accessPass1)
        accessPassDao.insert(accessPass2)
        
        accessPassDao.deleteAll()
        
        val retrievedPass1 = accessPassDao.getValidById("user4", Date())
        val retrievedPass2 = accessPassDao.getValidById("user5", Date())
        
        assertNull(retrievedPass1)
        assertNull(retrievedPass2)
    }
}