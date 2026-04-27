package com.safeplant.core.database.dao

import androidx.room.*
import com.safeplant.core.database.entity.QrCodeMapping

/**
 * DAO для работы с таблицей сопоставлений QR-кодов
 */
@Dao
interface QrCodeDao {
    
    /**
     * Получение сопоставления по objectId
     */
    @Query("SELECT * FROM qr_code_mappings WHERE objectId = :objectId")
    suspend fun getByObjectId(objectId: String): QrCodeMapping?
    
    /**
     * Вставка или обновление сопоставления
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(qrCodeMapping: QrCodeMapping)
    
    /**
     * Удаление всех сопоставлений
     */
    @Query("DELETE FROM qr_code_mappings")
    suspend fun deleteAll()
    
    /**
     * Получение всех сопоставлений
     */
    @Query("SELECT * FROM qr_code_mappings")
    suspend fun getAll(): List<QrCodeMapping>
}