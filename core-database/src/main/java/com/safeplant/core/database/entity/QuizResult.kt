package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения результатов прохождения квиза
 * @property id Уникальный идентификатор (первичный ключ)
 * @property timestamp Метка времени прохождения
 * @property correctAnswers Количество правильных ответов
 * @property totalQuestions Общее количество вопросов в тесте
 * @property passed Результат теста: true если ≥8 правильных ответов
 */
@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val passed: Boolean,
)
