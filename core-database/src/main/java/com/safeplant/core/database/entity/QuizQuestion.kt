package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения вопросов квиза
 * @property id Уникальный идентификатор (Long)
 * @property questionText Текст вопроса
 * @property options Варианты ответов (JSON-массив строк)
 * @property correctAnswerIndex Индекс правильного ответа в массиве options
 * @property category Категория вопроса для возможной фильтрации
 */
@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey val id: Long,
    val questionText: String,
    val options: String,
    val correctAnswerIndex: Int,
    val category: String,
)