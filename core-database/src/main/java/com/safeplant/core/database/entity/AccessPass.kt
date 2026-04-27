package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.safeplant.core.database.Converters

/**
 * Цифровой допуск пользователя
 * @param id Уникальный идентификатор допуска
 * @param userId Идентификатор пользователя
 * @param issuedAt Дата выдачи допуска
 * @param expiryDate Дата истечения срока действия допуска
 * @param isValid Флаг действительности допуска (true - действителен, false - аннулирован)
 */
@Entity(tableName = "access_pass")
@TypeConverters(Converters::class)
data class AccessPass(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val issuedAt: Date,
    val expiryDate: Date,
    val isValid: Boolean = true
)