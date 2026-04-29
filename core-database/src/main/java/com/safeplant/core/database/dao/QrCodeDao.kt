package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safeplant.core.database.entity.QrCodeMapping

@Dao
interface QrCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mapping: QrCodeMapping)

    @Query("SELECT * FROM qr_code_mappings WHERE objectId = :objectId")
    suspend fun getByObjectId(objectId: String): QrCodeMapping?
}
