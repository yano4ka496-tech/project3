package com.safeplant.core.database.dao

import com.safeplant.core.database.entity.QrCodeMapping
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QrCodeDaoImpl @Inject constructor() : QrCodeDao {
    private val storage = mutableMapOf<String, QrCodeMapping>()

    init {
        storage["123"] = QrCodeMapping("123", "Цех А", 55.7558, 37.6173)
        storage["456"] = QrCodeMapping("456", "Склад", 55.7568, 37.6183)
    }

    override suspend fun getByObjectId(objectId: String): QrCodeMapping? = storage[objectId]
    override suspend fun insert(mapping: QrCodeMapping) { storage[mapping.objectId] = mapping }
}
