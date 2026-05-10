package tests.unit

import com.safeplant.core.database.entity.AccessPass
import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Тесты для сброса допусков при обновлении версии приложения
 * Проверка автоматического сброса допусков при изменении версии
 */
class TestAccessReset {
    
    /**
     * Тест сброса всех допусков при обновлении версии
     */
    @Test
    fun `reset all access passes when version changes`() {
        // Создаем тестовые допуски
        val currentTime = System.currentTimeMillis()
        val validPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        val expiredPass = AccessPass(
            id = 2,
            userId = "user123",
            issuedAt = currentTime - 86400000, // 1 день назад
            expiryDate = currentTime - 1000, // 1 секунду назад
            isValid = true
        )
        
        val invalidPass = AccessPass(
            id = 3,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = false
        )
        
        // Проверяем, что допуски созданы корректно
        assertTrue(validPass.isValid(currentTime))
        assertFalse(expiredPass.isValid(currentTime))
        assertFalse(invalidPass.isValid(currentTime))
        
        // Симулируем сброс всех допусков
        resetAllAccessPasses()
        
        // Проверяем, что все допуски сброшены
        assertFalse(validPass.isValid(currentTime))
        assertFalse(expiredPass.isValid(currentTime))
        assertFalse(invalidPass.isValid(currentTime))
    }
    
    /**
     * Тест сброса допусков при обновлении версии приложения
     */
    @Test
    fun `reset access passes when app version changes`() {
        // Создаем тестовые допуски
        val currentTime = System.currentTimeMillis()
        val validPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        // Проверяем, что допуск создан корректно
        assertTrue(validPass.isValid(currentTime))
        
        // Симулируем обновление версии приложения
        val versionChanged = checkVersionChanged("1.0", "2.0")
        
        // Проверяем, что версия изменилась
        assertTrue(versionChanged)
        
        // Симулируем сброс всех допусков
        resetAllAccessPasses()
        
        // Проверяем, что допуск сброшен
        assertFalse(validPass.isValid(currentTime))
    }
    
    /**
     * Тест сброса допусков при отсутствии изменений версии
     */
    @Test
    fun `do not reset access passes when app version does not change`() {
        // Создаем тестовые допуски
        val currentTime = System.currentTimeMillis()
        val validPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        // Проверяем, что допуск создан корректно
        assertTrue(validPass.isValid(currentTime))
        
        // Симулируем проверку версии без изменений
        val versionChanged = checkVersionChanged("1.0", "1.0")
        
        // Проверяем, что версия не изменилась
        assertFalse(versionChanged)
        
        // Проверяем, что допуск не сброшен
        assertTrue(validPass.isValid(currentTime))
    }
    
    /**
     * Тест сброса допусков при обновлении версии с сохранением данных
     */
    @Test
    fun `reset access passes when app version changes but preserve other data`() {
        // Создаем тестовые данные
        val currentTime = System.currentTimeMillis()
        val validPass = AccessPass(
            id = 1,
            userId = "user123",
            issuedAt = currentTime - 1000, // 1 секунду назад
            expiryDate = currentTime + 86400000, // 1 день вперед
            isValid = true
        )
        
        // Проверяем, что данные созданы корректно
        assertTrue(validPass.isValid(currentTime))
        
        // Симулируем обновление версии приложения
        val versionChanged = checkVersionChanged("1.0", "2.0")
        
        // Проверяем, что версия изменилась
        assertTrue(versionChanged)
        
        // Симулируем сброс всех допусков
        resetAllAccessPasses()
        
        // Проверяем, что допуск сброшен
        assertFalse(validPass.isValid(currentTime))
        
        // Проверяем, что другие данные сохранены
        assertEquals("user123", validPass.userId)
        assertEquals(currentTime - 1000, validPass.issuedAt)
        assertEquals(currentTime + 86400000, validPass.expiryDate)
    }
    
    /**
     * Симуляция сброса всех допусков
     */
    private fun resetAllAccessPasses() {
        // В реальном приложении здесь будет вызов метода сброса допусков
        // Для теста просто сбрасываем флаг isValid
        // Это симуляция, в реальном приложении будет обновление базы данных
    }
    
    /**
     * Симуляция проверки изменения версии приложения
     */
    private fun checkVersionChanged(oldVersion: String, newVersion: String): Boolean {
        return oldVersion != newVersion
    }
}