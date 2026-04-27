package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения цифровых допусков
 * @property id Уникальный идентификатор допуска
 * @property expiryDate Дата истечения срока действия (timestamp)
 * @property issuedDate Дата выдачи (timestamp)
 * @property isPassValid Флаг действительности допуска (1 - действителен, 0 - недействителен)
 * @property version Версия приложения, при которой был выдан допуск
 */
@Entity(tableName = "permissions")
data class Permission(
    @PrimaryKey val id: String,
    val expiryDate: Long,
    val issuedDate: Long,
    val isPassValid: Int = 1, // По умолчанию действителен
    val version: String = "1.0" // Версия приложения
)