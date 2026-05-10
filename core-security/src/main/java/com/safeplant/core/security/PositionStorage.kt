package com.safeplant.core.security

import android.content.Context
import android.content.pm.PackageManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Хранилище координат пользователя с использованием EncryptedSharedPreferences
 * Сохраняет широту и долготу без ограничения срока хранения
 */
class PositionStorage(private val context: Context) {
    
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    
    private val encryptedPrefs = EncryptedSharedPreferences.create(
        context,
        "position_storage",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    
    /**
     * Сохраняет координаты пользователя
     * @param latitude Широта
     * @param longitude Долгота
     */
    fun savePosition(latitude: Double, longitude: Double) {
        encryptedPrefs.edit()
            .putString("lat", latitude.toString())
            .putString("lon", longitude.toString())
            .apply()
    }
    
    /**
     * Получает сохраненные координаты пользователя
     * @return Пара координат (широта, долгота) или null, если координаты не сохранены
     */
    fun getPosition(): Pair<Double, Double>? {
        return try {
            val latitude = encryptedPrefs.getString("lat", null)
            val longitude = encryptedPrefs.getString("lon", null)
            
            if (latitude != null && longitude != null) {
                Pair(latitude.toDouble(), longitude.toDouble())
            } else {
                null
            }
        } catch (e: Exception) {
            // В случае ошибки чтения возвращаем null
            null
        }
    }
    
    /**
     * Удаляет сохраненные координаты
     */
    fun clearPosition() {
        encryptedPrefs.edit()
            .remove("lat")
            .remove("lon")
            .apply()
    }
    
    /**
     * Сохраняет версию приложения
     * @param version Версия приложения (versionCode как строка)
     */
    fun saveAppVersion(version: String) {
        encryptedPrefs.edit()
            .putString("app_version", version)
            .apply()
    }
    
    /**
     * Получает сохраненную версию приложения
     * @return Версия приложения или null, если версия не сохранена
     */
    fun getAppVersion(): String? {
        return encryptedPrefs.getString("app_version", null)
    }
    
    /**
     * Проверяет, изменилась ли версия приложения
     * @param currentVersion Текущая версия приложения (versionCode как строка)
     * @return true, если версия изменилась
     */
    fun hasVersionChanged(currentVersion: String): Boolean {
        val savedVersion = getAppVersion()
        return savedVersion != null && savedVersion != currentVersion
    }
    
    /**
     * Получает текущую версию приложения из PackageManager
     * @return Версия приложения (versionCode как строка)
     */
    fun getCurrentVersion(): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionCode.toString()
    }
}