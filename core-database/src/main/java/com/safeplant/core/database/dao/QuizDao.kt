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
    /**
     * Вставка нового вопроса в базу данных
     */
    @Insert
    suspend fun insert(question: QuizQuestion)

    /**
     * Вставка списка вопросов в базу данных
     */
    @Insert
    suspend fun insertAll(questions: List<QuizQuestion>)

    /**
     * Получение всех вопросов из базы данных
     */
    @Query("SELECT * FROM quiz_questions")
    suspend fun getAllQuestions(): List<QuizQuestion>

    /**
     * Получение случайных 10 вопросов без повторений
     * Использует алгоритм Фишера-Йетса для перемешивания и берет первые 10
     */
    suspend fun getRandomQuestionsWithoutRepeats(): List<QuizQuestion> {
        // Загружаем все вопросы
        val allQuestions = getAllQuestions()

        // Если вопросов меньше 10, возвращаем все
        if (allQuestions.size <= 10) {
            return allQuestions
        }

        // Перемешиваем список
        val shuffledList = allQuestions.toMutableList()
        for (i in shuffledList.size - 1 downTo 1) {
            val j = Random.nextInt(i + 1)
            shuffledList[i] = shuffledList[j]
            shuffledList[j] = shuffledList[i]
        }

        // Берем первые 10
        return shuffledList.subList(0, 10)
    }

    /**
     * Получение случайных 10 вопросов с использованием SQLite RANDOM()
     * Может содержать дубликаты, но работает быстро
     */
    @Query("SELECT * FROM quiz_questions ORDER BY RANDOM() LIMIT 10")
    suspend fun getRandomQuestions(): List<QuizQuestion>

    /**
     * Получение вопроса по ID
     */
    @Query("SELECT * FROM quiz_questions WHERE id = :id")
    suspend fun getById(id: String): QuizQuestion?
}
