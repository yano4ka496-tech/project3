package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения вопросов квиза
 * @property id Уникальный идентификатор
 * @property text Текст вопроса
 * @property options Варианты ответов (JSON)
 * @property correctAnswerIndex Индекс правильного ответа
 */
@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey val id: String,
    val text: String,
    val options: String,
    val correctAnswerIndex: Int,
)
