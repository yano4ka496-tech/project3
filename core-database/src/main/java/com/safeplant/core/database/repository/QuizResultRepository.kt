package com.safeplant.core.database.repository

import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.QuizResult
import java.util.Date

/**
 * Репозиторий для работы с результатами прохождения квиза
 * Обеспечивает бизнес-логику сохранения и получения результатов квиза
 */
class QuizResultRepository(
    private val quizResultDao: QuizResultDao
) {
    
    /**
     * Сохранение результата прохождения квиза
     * @param userId Идентификатор пользователя
     * @param score Количество правильных ответов
     * @param passed Флаг успешного прохождения (true - успешно, false - неуспешно)
     * @return Сохраненный результат
     */
    suspend fun saveQuizResult(userId: String, score: Int, passed: Boolean): QuizResult {
        val timestamp = Date()
        val quizResult = QuizResult(
            userId = userId,
            score = score,
            passed = passed,
            timestamp = timestamp
        )
        
        quizResultDao.insert(quizResult)
        return quizResult
    }
    
    /**
     * Получение всех результатов для пользователя
     * @param userId Идентификатор пользователя
     * @return Список результатов, отсортированный по дате (новые первые)
     */
    suspend fun getUserQuizResults(userId: String): List<QuizResult> {
        return quizResultDao.getByUser(userId)
    }
    
    /**
     * Получение результатов за указанный период
     * @param userId Идентификатор пользователя
     * @param startDate Начало периода
     * @param endDate Конец периода
     * @return Список результатов за период
     */
    suspend fun getUserQuizResultsForPeriod(
        userId: String, 
        startDate: Date, 
        endDate: Date
    ): List<QuizResult> {
        return quizResultDao.getByUserForPeriod(userId, startDate, endDate)
    }
    
    /**
     * Проверка, успешно ли пользователь прошел квиз в последний раз
     * @param userId Идентификатор пользователя
     * @return true, если последний результат успешный, иначе false
     */
    suspend fun hasPassedQuizRecently(userId: String): Boolean {
        val results = getUserQuizResults(userId)
        return results.isNotEmpty() && results.first().passed
    }
}