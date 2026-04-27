package com.safeplant.core.database.repository

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * Репозиторий для работы с цифровыми допусками
 * Обеспечивает бизнес-логику управления допусками пользователей
 */
class AccessPassRepository(
    private val accessPassDao: AccessPassDao
) {
    
    /**
     * Получение действующего допуска для пользователя
     * @param userId Идентификатор пользователя
     * @return Действующий допуск или null, если допуска нет или он просрочен
     */
    suspend fun getValidAccessPass(userId: String): AccessPass? {
        val currentDate = Date()
        return accessPassDao.getValidById(userId, currentDate)
    }
    
    /**
     * Создание или обновление допуска пользователя
     * При обновлении старый допуск аннулируется, создается новый
     * @param userId Идентификатор пользователя
     * @return Созданный/обновленный допуск
     */
    suspend fun createOrUpdateAccessPass(userId: String): AccessPass {
        val currentDate = Date()
        val expiryDate = Date(currentDate.time + TimeUnit.DAYS.toMillis(30)) // 30 дней
        
        // Аннулируем все предыдущие допуски пользователя
        val previousPass = accessPassDao.getValidById(userId, currentDate)
        previousPass?.let {
            val updatedPass = it.copy(isValid = false)
            accessPassDao.updateValidity(updatedPass)
        }
        
        // Создаем новый допуск
        val newPass = AccessPass(
            userId = userId,
            issuedAt = currentDate,
            expiryDate = expiryDate,
            isValid = true
        )
        
        accessPassDao.insert(newPass)
        return newPass
    }
    
    /**
     * Проверка, действителен ли допуск для пользователя
     * @param userId Идентификатор пользователя
     * @return true, если действующий допуск есть, иначе false
     */
    suspend fun hasValidAccessPass(userId: String): Boolean {
        return getValidAccessPass(userId) != null
    }
    
    /**
     * Аннулирование всех допусков пользователя
     * @param userId Идентификатор пользователя
     */
    suspend fun invalidateAllAccessPasses(userId: String) {
        val currentDate = Date()
        val validPasses = accessPassDao.getValidById(userId, currentDate)
        
        validPasses?.let {
            val updatedPass = it.copy(isValid = false)
            accessPassDao.updateValidity(updatedPass)
        }
    }
    
    /**
     * Удаление всех допусков (используется при обновлении версии приложения)
     */
    suspend fun deleteAllAccessPasses() {
        accessPassDao.deleteAll()
    }
}