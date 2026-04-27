package com.safeplant.core.database.dao

import androidx.room.*
import com.safeplant.core.database.entity.AccessPass
import java.util.Date

/**
 * DAO для работы с таблицей допусков
 */
@Dao
interface AccessPassDao {
    
    /**
     * Вставка нового допуска
     */
    @Insert
    suspend fun insert(accessPass: AccessPass): Long
    
    /**
     * Получение действующего допуска по идентификатору пользователя
     */
    @Query("SELECT * FROM access_pass WHERE userId = :userId AND isValid = 1 AND expiryDate > :currentDate ORDER BY expiryDate DESC LIMIT 1")
    suspend fun getValidById(userId: String, currentDate: Date): AccessPass?
    
    /**
     * Удаление всех допусков
     */
    @Query("DELETE FROM access_pass")
    suspend fun deleteAll()
    
    /**
     * Обновление флага действительности допуска
     */
    @Update
    suspend fun updateValidity(accessPass: AccessPass)
}