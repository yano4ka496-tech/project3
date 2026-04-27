package com.safeplant

import android.content.Context
import android.content.pm.PackageManager
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.safeplant.core.database.dao.AccessPassDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Класс для проверки версии приложения и сброса цифрового пропуска при изменении версии
 */
@Singleton
class VersionChecker @Inject constructor(
    private val context: Context,
    private val accessPassDao: AccessPassDao
) {
    
    /**
     * Проверяет версию приложения и сбрасывает цифровой пропуск при изменении версии
     */
    suspend fun checkVersionAndResetPassIfNeeded() = withContext(Dispatchers.IO) {
        try {
            val currentVersion = getCurrentAppVersion()
            val storedVersion = getStoredAppVersion()
            
            if (currentVersion != storedVersion) {
                // Версия изменилась, сбрасываем цифровой пропуск
                accessPassDao.deleteAllAccessPasses()
                storeAppVersion(currentVersion)
            }
        } catch (e: Exception) {
            // В случае ошибки просто продолжаем работу без сброса
            // Это может произойти, например, при первом запуске приложения
        }
    }
    
    /**
     * Получает текущую версию приложения
     */
    private fun getCurrentAppVersion(): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
        return packageInfo.versionName ?: "1.0"
    }
    
    /**
     * Получает сохраненную версию приложения из EncryptedSharedPreferences
     */
    private fun getStoredAppVersion(): String {
        return try {
            val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
            val encryptedPrefs = EncryptedSharedPreferences.create(
                context,
                "app_version_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            
            encryptedPrefs.getString("app_version", "") ?: ""
        } catch (e: Exception) {
            // В случае ошибки возвращаем пустую строку
            ""
        }
    }
    
    /**
     * Сохраняет версию приложения в EncryptedSharedPreferences
     */
    private fun storeAppVersion(version: String) {
        try {
            val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
            val encryptedPrefs = EncryptedSharedPreferences.create(
                context,
                "app_version_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            
            encryptedPrefs.edit().putString("app_version", version).apply()
        } catch (e: Exception) {
            // В случае ошибки просто продолжаем работу
        }
    }
}