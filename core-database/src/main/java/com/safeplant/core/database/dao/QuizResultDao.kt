package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.safeplant.core.database.entity.QuizResult

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insert(quizResult: QuizResult)

    @Query("SELECT * FROM quiz_results WHERE userId = :userId")
    suspend fun getByUser(userId: String): List<QuizResult>
}
