package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.safeplant.core.database.Converters

/**
 * Сущность цифрового допуска
 * @param id Уникальный идентификатор допуска
 * @param userId Идентификатор пользователя
 * @param issuedAt Дата выдачи допуска (в миллисекундах)
 * @param expiryDate Дата истечения срока действия допуска (в миллисекундах)
 * @param isValid Флаг действительности допуска
 */
@Entity(tableName = "access_pass")
@TypeConverters(Converters::class)
data class AccessPass(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val issuedAt: Long,
    val expiryDate: Long,
    val isValid: Boolean = true,
)
