package com.safeplant.core.database.repository

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Date
import java.util.concurrent.TimeUnit

class AccessPassRepositoryTest {
    private val mockAccessPassDao = mockk<AccessPassDao>()
    private val repository = AccessPassRepository(mockAccessPassDao)

    @Test
    fun getValidAccessPass() = runBlocking {
        val validPass = AccessPass(
            userId = "user1",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)),
            isValid = true
        )
        
        every { mockAccessPassDao.getValidById("user1", any()) } returns validPass
        
        val result = repository.getValidAccessPass("user1")
        assertEquals(validPass, result)
    }

    @Test
    fun getExpiredAccessPass() = runBlocking {
        val expiredPass = AccessPass(
            userId = "user2",
            issuedAt = Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(31)),
            expiryDate = Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)),
            isValid = true
        )
        
        every { mockAccessPassDao.getValidById("user2", any()) } returns expiredPass
        
        val result = repository.getValidAccessPass("user2")
        assertEquals(expiredPass, result)
    }

    @Test
    fun createOrUpdateAccessPass() = runBlocking {
        val existingPass = AccessPass(
            id = 1,
            userId = "user3",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)),
            isValid = true
        )
        
        every { mockAccessPassDao.getValidById("user3", any()) } returns existingPass
        every { mockAccessPassDao.updateValidity(any()) } returns Unit
        every { mockAccessPassDao.insert(any()) } returns 2L
        
        val result = repository.createOrUpdateAccessPass("user3")
        
        assertEquals("user3", result.userId)
        assertTrue(result.isValid)
    }

    @Test
    fun hasValidAccessPass() = runBlocking {
        val validPass = AccessPass(
            userId = "user4",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)),
            isValid = true
        )
        
        every { mockAccessPassDao.getValidById("user4", any()) } returns validPass
        
        val result = repository.hasValidAccessPass("user4")
        assertTrue(result)
    }

    @Test
    fun invalidateAllAccessPasses() = runBlocking {
        val validPass = AccessPass(
            id = 1,
            userId = "user5",
            issuedAt = Date(),
            expiryDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)),
            isValid = true
        )
        
        every { mockAccessPassDao.getValidById("user5", any()) } returns validPass
        every { mockAccessPassDao.updateValidity(any()) } returns Unit
        
        repository.invalidateAllAccessPasses("user5")
        
        // Проверяем, что метод updateValidity был вызван
        verify { mockAccessPassDao.updateValidity(any()) }
    }

    @Test
    fun deleteAllAccessPasses() = runBlocking {
        every { mockAccessPassDao.deleteAll() } returns Unit
        
        repository.deleteAllAccessPasses()
        
        // Проверяем, что метод deleteAll был вызван
        verify { mockAccessPassDao.deleteAll() }
    }
}