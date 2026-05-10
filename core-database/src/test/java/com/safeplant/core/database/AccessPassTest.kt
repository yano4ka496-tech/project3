package com.safeplant.core.database

import com.safeplant.core.database.entity.AccessPass
import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Тесты для сущности AccessPass
 * Проверка срока действия и сброса допусков при обновлении версии
 */
class AccessPassTest {
    
    /**
     * Тест проверки срока действия допуска
     */
    @Test
    fun `isValid should return true for valid pass`() {
        val currentTime = System.currentTimeMillis()
        val validPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        assertTrue(validPass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при истечении срока
     */
    @Test
    fun `isValid should return false for expired pass`() {
        val currentTime = System.currentTimeMillis()
        val expiredPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 86400000, // 1 день назад
            expiryDate = currentTime - 1000, // 1 секунду назад
            isValid = true
        )
        
        assertFalse(expiredPass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при недействительном статусе
     */
    @Test
    fun `isValid should return false for invalid pass`() {
        val currentTime = System.currentTimeMillis()
        val invalidPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = false
        )
        
        assertFalse(invalidPass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при текущем времени в момент истечения
     */
    @Test
    fun `isValid should return false when expiry date equals current time`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime, // сейчас
            isValid = true
        )
        
        assertFalse(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при текущем времени в момент выдачи
     */
    @Test
    fun `isValid should return true when issued date equals current time`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime, // сейчас
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        assertTrue(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при истечении срока в прошлом
     */
    @Test
    fun `isValid should return false when expiry date is in past`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 86400000, // 1 день назад
            expiryDate = currentTime - 1000, // 1 секунду назад
            isValid = true
        )
        
        assertFalse(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при выдаче в будущем
     */
    @Test
    fun `isValid should return false when issued date is in future`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime + 1000, // 1 секунду вперед
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        assertFalse(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при истечении срока в будущем
     */
    @Test
    fun `isValid should return true when expiry date is in future`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        assertTrue(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при истечении срока через 30 дней
     */
    @Test
    fun `isValid should return true when expiry date is 30 days in future`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 30 * 86400000, // 30 дней вперед
            isValid = true
        )
        
        assertTrue(pass.isValid(currentTime))
    }
    
    /**
     * Тест проверки срока действия допуска при истечении срока через 31 день
     */
    @Test
    fun `isValid should return false when expiry date is 31 days in future`() {
        val currentTime = System.currentTimeMillis()
        val pass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 31 * 86400000, // 31 день вперед
            isValid = true
        )
        
        assertFalse(pass.isValid(currentTime))
    }
}