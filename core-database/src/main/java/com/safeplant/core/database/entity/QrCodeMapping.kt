package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сопоставление QR-кода с координатами на карте
 * @param id Уникальный идентификатор сопоставления
 * @param objectId Идентификатор объекта из QR-кода
 * @param name Название объекта
 * @param latitude Широта
 * @param longitude Долгота
 */
@Entity(tableName = "qr_code_mappings")
data class QrCodeMapping(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val objectId: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
)
