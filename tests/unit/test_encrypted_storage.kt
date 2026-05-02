package com.safeplant.core.security

import android.content.Context
import androidx.security.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Unit-тесты для EncryptedStorage
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class EncryptedStorageTest {
    
    private lateinit var context: Context
    private lateinit var masterKey: MasterKey
    private lateinit var encryptedPrefs: EncryptedSharedPreferences
    private lateinit var encryptedStorage: EncryptedStorage
    
    @Before
    fun setUp() {
        context = mockk()
        masterKey = mockk()
        encryptedPrefs = mockk()
        
        // Мокаем создание EncryptedSharedPreferences
        every { EncryptedSharedPreferences.create(
            context,
            "safeplant_encrypted_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) } returns encryptedPrefs
        
        // Мокаем MasterKey.Builder
        val masterKeyBuilder = mockk<MasterKey.Builder>()
        every { MasterKey.Builder(context) } returns masterKeyBuilder
        every { masterKeyBuilder.setKeyScheme(MasterKey.KeyScheme.AES256_GCM) } returns masterKeyBuilder
        every { masterKeyBuilder.build() } returns masterKey
        
        encryptedStorage = EncryptedStorage(context)
    }
    
    /**
     * Тестирование сохранения и получения даты истечения допуска
     */
    @Test
    fun `saveAccessPassExpiryDate and getAccessPassExpiryDate should work correctly`() {
        val expiryDate = System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L // 30 дней
        
        // Мокаем редактирование SharedPreferences
        val editor = mockk<androidx.security.EncryptedSharedPreferences.Editor>()
        every { encryptedPrefs.edit() } returns editor
        every { editor.putLong(any<String>(), any<Long>()) } returns editor
        every { editor.apply() } returns Unit
        
        // Мокаем проверку наличия ключа
        every { encryptedPrefs.contains(any<String>()) } returns false
        
        // Сохраняем дату
        encryptedStorage.saveAccessPassExpiryDate(expiryDate)
        
        // Мокаем получение даты
        every { encryptedPrefs.getLong(any<String>(), any<Long>()) } returns expiryDate
        
        // Получаем дату
        val retrievedDate = encryptedStorage.getAccessPassExpiryDate()
        
        assertEquals("Сохраненная и полученная дата должны совпадать", expiryDate, retrievedDate)
    }
    
    /**
     * Тестирование получения даты истечения при отсутствии данных
     */
    @Test
    fun `getAccessPassExpiryDate should return null when no data exists`() {
        // Мокаем проверку отсутствия ключа
        every { encryptedPrefs.contains(any<String>()) } returns false
        
        assertNull("Должно возвращать null при отсутствии данных", 
            encryptedStorage.getAccessPassExpiryDate())
    }
    
    /**
     * Тестирование сохранения и получения версии приложения
     */
    @Test
    fun `saveAppVersion and getAppVersion should work correctly`() {
        val version = "1.0.0"
        
        // Мокаем редактирование SharedPreferences
        val editor = mockk<androidx.security.EncryptedSharedPreferences.Editor>()
        every { encryptedPrefs.edit() } returns editor
        every { editor.putString(any<String>(), any<String>()) } returns editor
        every { editor.apply() } returns Unit
        
        // Сохраняем версию
        encryptedStorage.saveAppVersion(version)
        
        // Мокаем получение версии
        every { encryptedPrefs.getString(any<String>(), any<String>()) } returns version
        
        // Получаем версию
        val retrievedVersion = encryptedStorage.getAppVersion()
        
        assertEquals("Сохраненная и полученная версия должны совпадать", version, retrievedVersion)
    }
    
    /**
     * Тестирование получения версии при отсутствии данных
     */
    @Test
    fun `getAppVersion should return null when no data exists`() {
        // Мокаем получение версии с null
        every { encryptedPrefs.getString(any<String>(), any<String>()) } returns null
        
        assertNull("Должно возвращать null при отсутствии данных", 
            encryptedStorage.getAppVersion())
    }
    
    /**
     * Тестирование очистки данных
     */
    @Test
    fun `clear should remove all data`() {
        // Мокаем редактирование SharedPreferences
        val editor = mockk<androidx.security.EncryptedSharedPreferences.Editor>()
        every { encryptedPrefs.edit() } returns editor
        every { editor.clear() } returns editor
        every { editor.apply() } returns Unit
        
        // Очищаем данные
        encryptedStorage.clear()
        
        // Проверяем, что данные очищены
        every { encryptedPrefs.contains(any<String>()) } returns false
        assertNull("После очистки должно возвращать null", 
            encryptedStorage.getAccessPassExpiryDate())
        assertNull("После очистки должно возвращать null", 
            encryptedStorage.getAppVersion())
    }
    
    /**
     * Тестирование проверки действительности допуска
     */
    @Test
    fun `isAccessPassValid should return true when pass is valid`() {
        val expiryDate = System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L // 30 дней
        
        // Мокаем наличие данных
        every { encryptedPrefs.contains(any<String>()) } returns true
        every { encryptedPrefs.getLong(any<String>(), any<Long>()) } returns expiryDate
        
        assertTrue("Должно возвращать true для действующего допуска", 
            encryptedStorage.isAccessPassValid())
    }
    
    /**
     * Тестирование проверки действительности допуска при истечении срока
     */
    @Test
    fun `isAccessPassValid should return false when pass is expired`() {
        val expiryDate = System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000L // 30 дней назад
        
        // Мокаем наличие данных
        every { encryptedPrefs.contains(any<String>()) } returns true
        every { encryptedPrefs.getLong(any<String>(), any<Long>()) } returns expiryDate
        
        assertFalse("Должно возвращать false для истекшего допуска", 
            encryptedStorage.isAccessPassValid())
    }
    
    /**
     * Тестирование проверки действительности допуска при отсутствии данных
     */
    @Test
    fun `isAccessPassValid should return false when no data exists`() {
        // Мокаем отсутствие данных
        every { encryptedPrefs.contains(any<String>()) } returns false
        
        assertFalse("Должно возвращать false при отсутствии данных", 
            encryptedStorage.isAccessPassValid())
    }
}