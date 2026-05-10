package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.safeplant.core.database.entity.Permission

/**
 * DAO для работы с цифровыми допусками
 */
@Dao
interface PermissionDao {
    /**
     * Вставка нового допуска в базу данных
     */
    @Insert
    suspend fun insert(permission: Permission)

    /**
     * Получение действующего допуска
     */
    @Query("SELECT * FROM permissions WHERE isPassValid = 1 AND expiryDate > :currentTime ORDER BY issuedDate DESC LIMIT 1")
    suspend fun getValidPermission(currentTime: Long): Permission?

    /**
     * Получение всех допусков
     */
    @Query("SELECT * FROM permissions")
    suspend fun getAllPermissions(): List<Permission>

    /**
     * Обновление статуса допуска
     */
    @Update
    suspend fun update(permission: Permission)

    /**
     * Сброс всех допусков (установка isPassValid = 0)
     */
    @Query("UPDATE permissions SET isPassValid = 0")
    suspend fun invalidateAllPermissions()

    /**
     * Пометка истекших разрешений как недействительных
     * @param currentTime Текущее время в миллисекундах
     */
    @Query("UPDATE permissions SET isPassValid = 0 WHERE expiryDate <= :currentTime")
    suspend fun invalidateExpiredPermissions(currentTime: Long)

    /**
     * Проверка наличия действующего допуска
     */
    @Query("SELECT COUNT(*) FROM permissions WHERE isPassValid = 1 AND expiryDate > :currentTime")
    suspend fun hasValidPermission(currentTime: Long): Int
}
