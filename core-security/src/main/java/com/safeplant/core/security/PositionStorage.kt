package com.safeplant.core.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Хранилище координат пользователя и закладок с использованием EncryptedSharedPreferences
 */
class PositionStorage(private val context: Context) {
    private val masterKey =
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    private val encryptedPrefs =
        EncryptedSharedPreferences.create(
            context,
            "position_storage",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )

    // Обычные SharedPreferences для закладок (не требуют шифрования)
    private val regularPrefs = context.getSharedPreferences("safeplant_regular", Context.MODE_PRIVATE)

    /**
     * Сохраняет координаты пользователя
     */
    fun savePosition(latitude: Double, longitude: Double) {
        encryptedPrefs.edit()
            .putString("lat", latitude.toString())
            .putString("lon", longitude.toString())
            .apply()
    }

    /**
     * Получает сохраненные координаты пользователя
     */
    fun getPosition(): Pair<Double, Double>? {
        return try {
            val lat = encryptedPrefs.getString("lat", null)
            val lon = encryptedPrefs.getString("lon", null)
            if (lat != null && lon != null) Pair(lat.toDouble(), lon.toDouble()) else null
        } catch (e: Exception) { null }
    }

    /**
     * Удаляет сохраненные координаты
     */
    fun clearPosition() {
        encryptedPrefs.edit().remove("lat").remove("lon").apply()
    }

    /**
     * Сохраняет версию приложения
     */
    fun saveAppVersion(version: String) {
        encryptedPrefs.edit().putString("app_version", version).apply()
    }

    /**
     * Получает сохраненную версию приложения
     */
    fun getAppVersion(): String? = encryptedPrefs.getString("app_version", null)

    /**
     * Проверяет, изменилась ли версия приложения
     */
    fun hasVersionChanged(currentVersion: String): Boolean {
        val savedVersion = getAppVersion()
        return savedVersion != null && savedVersion != currentVersion
    }

    /**
     * Получает текущую версию приложения из PackageManager
     */
    fun getCurrentVersion(): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionCode.toString()
    }

    // ========== Закладки (сохраняются в обычные SharedPreferences) ==========

    suspend fun saveFavorites(favorites: List<String>) {
        withContext(Dispatchers.IO) {
            regularPrefs.edit().putString("favorites", favorites.joinToString(",")).apply()
        }
    }

    suspend fun getFavorites(): List<String>? {
        return withContext(Dispatchers.IO) {
            val str = regularPrefs.getString("favorites", null) ?: return@withContext null
            if (str.isBlank()) emptyList() else str.split(",")
        }
    }
}
