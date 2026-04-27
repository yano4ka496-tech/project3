package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения вопросов квиза
 * @property id Уникальный идентификатор вопроса
 * @property text Текст вопроса
 * @property options Список вариантов ответа (JSON строка)
 * @property correctAnswerIndex Индекс правильного ответа в списке options
 */
@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey val id: String,
    val text: String,
    val options: String, // Храним как JSON строку для простоты
    val correctAnswerIndex: Int
)