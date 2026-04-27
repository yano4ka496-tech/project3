package com.safeplant.feature.quiz

import android.content.Context
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Репозиторий для работы с вопросами квиза
 */
class QuizRepository(
    private val context: Context,
    private val database: AppDatabase
) {
    
    /**
     * Загружает вопросы из assets
     * @return Список всех вопросов
     */
    suspend fun loadQuestions(): List<QuizQuestion> = withContext(Dispatchers.IO) {
        val questions = mutableListOf<QuizQuestion>()
        
        try {
            val inputStream = context.assets.open("quiz/questions.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()
            reader.close()
            
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonQuestion = jsonArray.getJSONObject(i)
                val question = QuizQuestion(
                    id = jsonQuestion.getString("id"),
                    question = jsonQuestion.getString("question"),
                    options = jsonQuestion.getJSONArray("options").let { array ->
                        List(array.length()) { index -> array.getString(index) }
                    },
                    correctAnswer = jsonQuestion.getString("correctAnswer")
                )
                questions.add(question)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        questions
    }
    
    /**
     * Сохраняет результат квиза в базу данных
     * @param quizResult Результат квиза
     */
    suspend fun saveQuizResult(quizResult: QuizResult) {
        database.quizResultDao().insert(quizResult)
    }
    
    /**
     * Получает результат квиза по ID
     * @param id ID результата
     * @return Результат квиза или null
     */
    suspend fun getQuizResult(id: String): QuizResult? {
        return database.quizResultDao().getById(id)
    }
}

/**
 * Данные вопроса квиза
 */
data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)