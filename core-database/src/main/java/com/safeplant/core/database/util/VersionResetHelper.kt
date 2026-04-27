package com.safeplant.core.database.util

import android.content.Context
import android.content.pm.PackageManager
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.dao.QrCodeDao
import java.util.Date

/**
 * Утилита для сброса данных при обновлении версии приложения
 * Сбрасывает только допуски, сохраняя остальные данные
 */
class VersionResetHelper(
    private val context: Context,
    private val accessPassDao: AccessPassDao,
    private val quizResultDao: QuizResultDao,
    private val trainingVideoDao: TrainingVideoDao,
    private val qrCodeDao: QrCodeDao
) {
    
    /**
     * Проверяет, было ли обновление версии приложения
     * @return true, если версия изменилась, иначе false
     */
    suspend fun isVersionChanged(): Boolean {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(
            context.packageName, 
            PackageManager.GET_META_DATA
        )
        
        val currentVersion = packageInfo.versionName
        val savedVersion = getSavedVersion()
        
        return currentVersion != savedVersion
    }
    
    /**
     * Сбрасывает допуски при обновлении версии
     * Сохраняет версию приложения для будущих проверок
     */
    suspend fun resetAccessPassesOnVersionChange() {
        if (isVersionChanged()) {
            // Сбрасываем допуски
            accessPassDao.deleteAll()
            
            // Сохраняем текущую версию
            saveCurrentVersion()
        }
    }
    
    /**
     * Получает сохраненную версию приложения
     * @return Сохраненная версия или null, если сохраненной версии нет
     */
    private fun getSavedVersion(): String? {
        // В реальном приложении здесь будет использоваться EncryptedSharedPreferences
        // Для примера используем простое хранение
        return context.getSharedPreferences("app_version", Context.MODE_PRIVATE)
            .getString("saved_version", null)
    }
    
    /**
     * Сохраняет текущую версию приложения
     */
    private fun saveCurrentVersion() {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(
            context.packageName, 
            PackageManager.GET_META_DATA
        )
        
        val currentVersion = packageInfo.versionName
        
        context.getSharedPreferences("app_version", Context.MODE_PRIVATE)
            .edit()
            .putString("saved_version", currentVersion)
            .apply()
    }
}