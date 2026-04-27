package com.safeplant.core.database.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.entity.QrCodeMapping
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Утилита для валидации данных квиза и QR-кодов
 * Проверяет целостность файлов и наличие данных в БД
 */
class QuizValidator(
    private val context: Context,
    private val qrCodeDao: QrCodeDao
) {
    
    /**
     * Проверяет наличие и целостность файла с вопросами
     * @return true, если файл существует и корректен, иначе false
     */
    fun validateQuestionsFile(): Boolean {
        return try {
            val inputStream = context.assets.open("quiz/questions.json")
            val reader = InputStreamReader(inputStream)
            val gson = Gson()
            val type = TypeToken<List<Map<String, Any>>>().type
            gson.fromJson<List<Map<String, Any>>>(reader, type)
            true
        } catch (e: IOException) {
            false
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Проверяет наличие objectId в БД
     * @param objectId Идентификатор объекта из QR-кода
     * @return true, если objectId найден, иначе false
     */
    suspend fun validateObjectId(objectId: String): Boolean {
        return qrCodeDao.getByObjectId(objectId) != null
    }
    
    /**
     * Получает сопоставление по objectId
     * @param objectId Идентификатор объекта из QR-кода
     * @return Сопоставление или null, если objectId не найден
     */
    suspend fun getQrCodeMapping(objectId: String): QrCodeMapping? {
        return qrCodeDao.getByObjectId(objectId)
    }
    
    /**
     * Проверяет, есть ли у objectId предустановленный маршрут
     * @param objectId Идентификатор объекта из QR-кода
     * @return true, если маршрут есть, иначе false
     */
    suspend fun hasRouteForObjectId(objectId: String): Boolean {
        val mapping = qrCodeDao.getByObjectId(objectId)
        // В реальном приложении здесь будет проверка наличия маршрута
        // Для примера считаем, что у каждого objectId есть маршрут
        return mapping != null
    }
}