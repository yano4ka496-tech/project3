package com.safeplant.core.database.dao

import com.safeplant.core.database.entity.QrCodeMapping

interface QrCodeDao {
    suspend fun getByObjectId(objectId: String): QrCodeMapping?
    suspend fun insert(mapping: QrCodeMapping)
}
