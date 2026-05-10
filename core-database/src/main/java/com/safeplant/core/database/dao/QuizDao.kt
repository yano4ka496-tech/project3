package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.safeplant.core.database.entity.QuizQuestion
import kotlin.random.Random

/**
 * DAO для работы с вопросами квиза
 */
@Dao
interface QuizDao {
    @Insert
    suspend fun insert(question: QuizQuestion)

    @Insert
    suspend fun insertAll(questions: List<QuizQuestion>)

    @Query("SELECT * FROM quiz_questions")
    suspend fun getAllQuestions(): List<QuizQuestion>

    suspend fun getRandomQuestionsWithSeed(seed: Long): List<QuizQuestion> {
        val allQuestions = getAllQuestions()
        if (allQuestions.size <= 10) return allQuestions
        val random = Random(seed)
        val shuffledList = allQuestions.toMutableList()
        for (i in shuffledList.size - 1 downTo 1) {
            val j = random.nextInt(shuffledList.size)
            val temp = shuffledList[i]
            shuffledList[i] = shuffledList[j]
            shuffledList[j] = temp
        }
        return shuffledList.subList(0, 10)
    }

    suspend fun getRandomQuestionsWithoutRepeats(): List<QuizQuestion> {
        val allQuestions = getAllQuestions()
        if (allQuestions.size <= 10) return allQuestions
        val random = Random
        val shuffledList = allQuestions.toMutableList()
        for (i in shuffledList.size - 1 downTo 1) {
            val j = random.nextInt(shuffledList.size)
            val temp = shuffledList[i]
            shuffledList[i] = shuffledList[j]
            shuffledList[j] = temp
        }
        return shuffledList.subList(0, 10)
    }

    @Query("SELECT * FROM quiz_questions WHERE id = :id")
    suspend fun getById(id: Long): QuizQuestion?
}
