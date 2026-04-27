package com.safeplant.core.database.dao

import androidx.room.*
import com.safeplant.core.database.entity.QuizResult
import java.util.Date

/**
 * DAO для работы с таблицей результатов квиза
 */
@Dao
interface QuizResultDao {
    
    /**
     * Вставка результата прохождения квиза
     */
    @Insert
    suspend fun insert(quizResult: QuizResult): Long
    
    /**
     * Получение всех результатов для пользователя
     */
    @Query("SELECT * FROM quiz_result WHERE userId = :userId ORDER BY timestamp DESC")
    suspend fun getByUser(userId: String): List<QuizResult>
    
    /**
     * Получение результатов за указанный период
     */
    @Query("SELECT * FROM quiz_result WHERE userId = :userId AND timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    suspend fun getByUserForPeriod(userId: String, startDate: Date, endDate: Date): List<QuizResult>
}