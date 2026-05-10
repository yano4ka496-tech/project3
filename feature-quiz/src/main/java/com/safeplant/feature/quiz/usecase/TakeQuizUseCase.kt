package com.safeplant.feature.quiz.usecase

import com.safeplant.core.database.dao.QuizDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.QuizQuestion
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.util.Random

/**
 * Use case для прохождения квиза
 * @param quizDao DAO для работы с вопросами
 * @param quizResultDao DAO для сохранения результатов
 */
class TakeQuizUseCase(
    private val quizDao: QuizDao,
    private val quizResultDao: QuizResultDao
) {

    /**
     * Прохождение квиза
     * @param seed Значение для детерминированного выбора вопросов
     * @param userAnswers Список ответов пользователя (индексы выбранных вариантов, null если не ответил)
     * @return Результат прохождения квиза
     */
    suspend fun takeQuiz(seed: Long, userAnswers: List<Int?>): QuizResult = withContext(Dispatchers.IO) {
        // Получаем вопросы с детерминированным seed
        val questions = quizDao.getRandomQuestionsWithSeed(seed)
        
        // Проверяем, что количество вопросов совпадает с количеством ответов
        require(questions.size == userAnswers.size) {
            "Количество вопросов (${questions.size}) не совпадает с количеством ответов (${userAnswers.size})"
        }
        
        // Подсчитываем правильные ответы
        var correctAnswers = 0
        questions.forEachIndexed { index, question ->
            val userAnswer = userAnswers[index]
            
            // Если пользователь не ответил, считаем неправильным
            if (userAnswer != null) {
                // Парсим варианты ответов из JSON
                val options = parseOptions(question.options)
                
                // Проверяем, что индекс ответа в допустимом диапазоне
                require(userAnswer in 0 until options.size) {
                    "Индекс ответа $userAnswer выходит за пределы диапазона [0, ${options.size - 1}] для вопроса ${question.id}"
                }
                
                // Сравниваем с правильным ответом
                if (userAnswer == question.correctAnswerIndex) {
                    correctAnswers++
                }
            }
        }
        
        // Определяем, пройден ли тест (8 или более правильных ответов)
        val passed = correctAnswers >= 8
        
        // Сохраняем результат
        val quizResult = QuizResult(
            timestamp = System.currentTimeMillis(),
            correctAnswers = correctAnswers,
            totalQuestions = questions.size,
            passed = passed
        )
        
        quizResultDao.insert(quizResult)
        
        // Возвращаем результат
        quizResult
    }

    /**
     * Парсит JSON-массив вариантов ответов в список строк
     * @param optionsJson JSON-массив строк
     * @return Список вариантов ответов
     */
    private fun parseOptions(optionsJson: String): List<String> {
        val jsonArray = JSONArray(optionsJson)
        return List(jsonArray.length()) { index -> jsonArray.getString(index) }
    }
}