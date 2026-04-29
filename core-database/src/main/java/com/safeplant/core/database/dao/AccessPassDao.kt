package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Query
import com.safeplant.core.database.entity.AccessPass

/**
 * DAO для работы с цифровыми допусками
 */
@Dao
interface AccessPassDao {
    
    /**
     * Вставка допуска (без замены при конфликте)
     */
    @Insert
    suspend fun insert(accessPass: AccessPass)
    
    /**
     * Вставка или обновление допуска
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(accessPass: AccessPass)
    
    /**
     * Получение действующего допуска для пользователя
     */
    @Query("SELECT * FROM access_pass WHERE userId = :userId AND isValid = 1 AND expiryDate > :currentTime ORDER BY expiryDate DESC LIMIT 1")
    suspend fun getValidAccessPass(userId: String, currentTime: Long): AccessPass?
    
    /**
     * Получение действующего допуска по идентификатору
     */
    @Query("SELECT * FROM access_pass WHERE id = :id AND isValid = 1 AND expiryDate > :currentTime")
    suspend fun getValidById(id: Long, currentTime: Long): AccessPass?
    
    /**
     * Получение всех действующих допусков
     */
    @Query("SELECT * FROM access_pass WHERE isValid = 1 AND expiryDate > :currentTime")
    suspend fun getAllValid(currentTime: Long): List<AccessPass>
    
    /**
     * Пометка допуска как недействительного
     */
    @Query("UPDATE access_pass SET isValid = 0 WHERE id = :accessPassId")
    suspend fun invalidateAccessPass(accessPassId: Long)
    
    /**
     * Удаление всех допусков
     */
    @Query("DELETE FROM access_pass")
    suspend fun deleteAll()
    
    /**
     * Проверка наличия действующего допуска для пользователя
     */
    @Query("SELECT COUNT(*) > 0 FROM access_pass WHERE userId = :userId AND isValid = 1 AND expiryDate > :currentTime")
    suspend fun hasValidAccessPass(userId: String, currentTime: Long): Boolean
}