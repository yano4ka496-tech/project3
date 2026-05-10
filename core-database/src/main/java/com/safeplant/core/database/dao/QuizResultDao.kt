package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.safeplant.core.database.entity.QuizResult

/**
 * DAO для работы с результатами прохождения квиза
 */
@Dao
interface QuizResultDao {
    /**
     * Сохранение результата прохождения квиза
     * @param quizResult Объект с результатом прохождения
     */
    @Insert
    suspend fun insert(quizResult: QuizResult)

    /**
     * Получение всех результатов прохождения квиза
     */
    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    suspend fun getAllResults(): List<QuizResult>

    /**
     * Получение результатов прохождения квиза по ID пользователя
     * @deprecated Не используется в текущей реализации, так как нет аутентификации
     */
    @Deprecated("Не используется в текущей реализации")
    @Query("SELECT * FROM quiz_results WHERE userId = :userId ORDER BY timestamp DESC")
    suspend fun getByUser(userId: String): List<QuizResult>
}