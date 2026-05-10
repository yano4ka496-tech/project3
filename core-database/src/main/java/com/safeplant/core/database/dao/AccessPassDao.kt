package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safeplant.core.database.entity.AccessPass

@Dao
interface AccessPassDao {
    @Insert
    suspend fun insert(accessPass: AccessPass)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(accessPass: AccessPass)

    @Query("SELECT * FROM access_pass WHERE userId = :userId AND isValid = 1 AND expiryDate > :currentTime ORDER BY expiryDate DESC LIMIT 1")
    suspend fun getValidAccessPass(userId: String, currentTime: Long): AccessPass?

    @Query("SELECT * FROM access_pass WHERE id = :id AND isValid = 1 AND expiryDate > :currentTime")
    suspend fun getValidById(id: Long, currentTime: Long): AccessPass?

    @Query("SELECT * FROM access_pass WHERE isValid = 1 AND expiryDate > :currentTime")
    suspend fun getAllValid(currentTime: Long): List<AccessPass>

    @Query("UPDATE access_pass SET isValid = 0 WHERE id = :accessPassId")
    suspend fun invalidateAccessPass(accessPassId: Long)

    @Query("DELETE FROM access_pass")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) > 0 FROM access_pass WHERE userId = :userId AND isValid = 1 AND expiryDate > :currentTime")
    suspend fun hasValidAccessPass(userId: String, currentTime: Long): Boolean

    suspend fun resetAllAccessPasses() {
        deleteAll()
    }
}