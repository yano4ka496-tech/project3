package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.safeplant.core.database.Converters

/**
 * Результат прохождения квиза пользователем
 * @param id Уникальный идентификатор результата
 * @param userId Идентификатор пользователя
 * @param score Количество правильных ответов
 * @param passed Флаг успешного прохождения (true - успешно, false - неуспешно)
 * @param timestamp Дата и время прохождения квиза
 */
@Entity(tableName = "quiz_result")
@TypeConverters(Converters::class)
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val score: Int,
    val passed: Boolean,
    val timestamp: Date
)