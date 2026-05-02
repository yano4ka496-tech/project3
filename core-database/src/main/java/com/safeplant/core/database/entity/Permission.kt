package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения цифровых допусков
 * @property id Уникальный идентификатор
 * @property expiryDate Дата истечения
 * @property issuedDate Дата выдачи
 * @property isPassValid Флаг действительности
 * @property version Версия приложения
 */
@Entity(tableName = "permissions")
data class Permission(
    @PrimaryKey val id: String,
    val expiryDate: Long,
    val issuedDate: Long,
    val isPassValid: Int = 1,
    val version: String = "1.0",
)
