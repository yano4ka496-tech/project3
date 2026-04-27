package com.safeplant.core.database

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.dao.QuizDao
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.database.entity.QuizQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Инициализатор базы данных, загружающий данные из assets
 */
class DatabaseInitializer(
    private val context: Context,
    private val quizDao: QuizDao,
    private val qrCodeDao: QrCodeDao
) {
    
    companion object {
        private const val TAG = "DatabaseInitializer"
        private const val QUESTIONS_FILE = "quiz/questions.json"
        private const val QR_MAPPINGS_FILE = "qr_mappings.csv"
    }
    
    /**
     * Загружает данные из assets в базу данных
     */
    suspend fun initializeDatabase() = withContext(Dispatchers.IO) {
        try {
            // Загружаем вопросы квиза
            loadQuestionsFromAssets()
            
            // Загружаем сопоставления QR-кодов
            loadQrMappingsFromAssets()
            
            Log.d(TAG, "Инициализация базы данных завершена успешно")
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при инициализации базы данных", e)
        }
    }
    
    /**
     * Загружает вопросы квиза из JSON файла в assets
     */
    private suspend fun loadQuestionsFromAssets() {
        try {
            val jsonString = readAssetFile(QUESTIONS_FILE)
            if (jsonString == null) {
                Log.w(TAG, "Файл с вопросами не найден: $QUESTIONS_FILE")
                return
            }
            
            val gson = Gson()
            val questionType = object : TypeToken<List<QuizQuestionJson>>() {}.type
            val questionsJson = gson.fromJson<List<QuizQuestionJson>>(jsonString, questionType)
            
            // Преобразуем в сущности QuizQuestion
            val questions = questionsJson.map { json ->
                QuizQuestion(
                    id = json.id,
                    text = json.question,
                    options = gson.toJson(json.options),
                    correctAnswerIndex = json.options.indexOf(json.correctAnswer)
                )
            }
            
            // Вставляем в базу данных
            quizDao.insertAll(questions)
            Log.d(TAG, "Загружено ${questions.size} вопросов из assets")
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при загрузке вопросов из assets", e)
        }
    }
    
    /**
     * Загружает сопоставления QR-кодов из CSV файла в assets
     */
    private suspend fun loadQrMappingsFromAssets() {
        try {
            val csvString = readAssetFile(QR_MAPPINGS_FILE)
            if (csvString == null) {
                Log.w(TAG, "Файл с сопоставлениями QR-кодов не найден: $QR_MAPPINGS_FILE")
                return
            }
            
            val mappings = parseCsvToQrMappings(csvString)
            
            // Вставляем в базу данных
            qrCodeDao.insertAll(mappings)
            Log.d(TAG, "Загружено ${mappings.size} сопоставлений QR-кодов из assets")
        } catch (e: Exception) {
            Log.e(TAG, "Ошибка при загрузке сопоставлений QR-кодов из assets", e)
        }
    }
    
    /**
     * Читает файл из assets
     * @param filePath Путь к файлу в assets
     * @return Содержимое файла или null, если файл не найден
     */
    private fun readAssetFile(filePath: String): String? {
        return try {
            val inputStream = context.assets.open(filePath)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            
            bufferedReader.close()
            inputStream.close()
            
            stringBuilder.toString()
        } catch (e: IOException) {
            Log.w(TAG, "Файл не найден или поврежден: $filePath", e)
            null
        }
    }
    
    /**
     * Парсит CSV строку в список сопоставлений QR-кодов
     * Формат CSV: id,objectId,name,latitude,longitude
     */
    private fun parseCsvToQrMappings(csvString: String): List<QrCodeMapping> {
        val lines = csvString.split("\n")
        val mappings = mutableListOf<QrCodeMapping>()
        
        // Пропускаем заголовок, если он есть
        val startIndex = if (lines.first().split(",").size == 5) 1 else 0
        
        for (i in startIndex until lines.size) {
            val line = lines[i].trim()
            if (line.isEmpty()) continue
            
            try {
                val parts = line.split(",")
                if (parts.size == 5) {
                    val mapping = QrCodeMapping(
                        id = parts[0].trim(),
                        objectId = parts[1].trim(),
                        name = parts[2].trim(),
                        latitude = parts[3].trim().toDouble(),
                        longitude = parts[4].trim().toDouble()
                    )
                    mappings.add(mapping)
                }
            } catch (e: Exception) {
                Log.w(TAG, "Ошибка при парсинге строки CSV: $line", e)
            }
        }
        
        return mappings
    }
    
    /**
     * Вспомогательный класс для парсинга JSON с вопросами
     */
    private data class QuizQuestionJson(
        val id: String,
        val question: String,
        val options: List<String>,
        val correctAnswer: String
    )
}