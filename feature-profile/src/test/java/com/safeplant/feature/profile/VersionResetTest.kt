package com.safeplant.feature.profile

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

class VersionResetTest {

    private lateinit var profileRepository: ProfileRepository
    private lateinit var mockPackageManager: PackageManager
    private lateinit var mockContext: Context
    private lateinit var encryptedPrefs: EncryptedSharedPreferences

    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        mockPackageManager = mock(PackageManager::class.java)
        
        // Создаем EncryptedSharedPreferences для тестов
        encryptedPrefs = mock(EncryptedSharedPreferences::class.java)
        
        // Мокируем получение preferences
        `when`(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(encryptedPrefs)
        
        profileRepository = ProfileRepository(mockContext)
    }

    @Test
    fun `resetAccessPass when version changed should reset access pass`() {
        // Текущая версия приложения
        val currentVersionCode = 2
        `when`(mockPackageManager.getPackageInfo(anyString(), anyInt()).versionCode).thenReturn(currentVersionCode)
        
        // Сохраненная версия
        val savedVersionCode = 1
        `when`(encryptedPrefs.getInt("app_version", 0)).thenReturn(savedVersionCode)
        
        // Мокируем редактирование preferences
        val mockEditor = mock(android.content.SharedPreferences.Editor::class.java)
        `when`(encryptedPrefs.edit()).thenReturn(mockEditor)
        `when`(mockEditor.putInt(anyString(), anyInt())).thenReturn(mockEditor)
        
        // Вызываем метод
        profileRepository.resetAccessPassIfVersionChanged()
        
        // Проверяем, что версия была обновлена
        verify(mockEditor).putInt("app_version", currentVersionCode)
        verify(mockEditor).apply()
    }

    @Test
    fun `resetAccessPass when version not changed should not reset access pass`() {
        // Текущая версия приложения
        val currentVersionCode = 1
        `when`(mockPackageManager.getPackageInfo(anyString(), anyInt()).versionCode).thenReturn(currentVersionCode)
        
        // Сохраненная версия
        val savedVersionCode = 1
        `when`(encryptedPrefs.getInt("app_version", 0)).thenReturn(savedVersionCode)
        
        // Мокируем редактирование preferences
        val mockEditor = mock(android.content.SharedPreferences.Editor::class.java)
        `when`(encryptedPrefs.edit()).thenReturn(mockEditor)
        
        // Вызываем метод
        profileRepository.resetAccessPassIfVersionChanged()
        
        // Проверяем, что редактирование не происходило
        verify(mockEditor, never()).putInt(anyString(), anyInt())
        verify(mockEditor, never()).apply()
    }

    @Test
    fun `resetAccessPass when no saved version should reset access pass`() {
        // Текущая версия приложения
        val currentVersionCode = 1
        `when`(mockPackageManager.getPackageInfo(anyString(), anyInt()).versionCode).thenReturn(currentVersionCode)
        
        // Нет сохраненной версии
        `when`(encryptedPrefs.getInt("app_version", 0)).thenReturn(0)
        
        // Мокируем редактирование preferences
        val mockEditor = mock(android.content.SharedPreferences.Editor::class.java)
        `when`(encryptedPrefs.edit()).thenReturn(mockEditor)
        `when`(mockEditor.putInt(anyString(), anyInt())).thenReturn(mockEditor)
        
        // Вызываем метод
        profileRepository.resetAccessPassIfVersionChanged()
        
        // Проверяем, что версия была обновлена
        verify(mockEditor).putInt("app_version", currentVersionCode)
        verify(mockEditor).apply()
    }

    @Test
    fun `resetAccessPass when PackageManager throws exception should not crash`() {
        // Мокируем исключение
        `when`(mockPackageManager.getPackageInfo(anyString(), anyInt()))
            .thenThrow(NameNotFoundException())
        
        // Мокируем редактирование preferences
        val mockEditor = mock(android.content.SharedPreferences.Editor::class.java)
        `when`(encryptedPrefs.edit()).thenReturn(mockEditor)
        
        // Вызываем метод - не должно выбрасывать исключение
        profileRepository.resetAccessPassIfVersionChanged()
        
        // Проверяем, что редактирование не происходило
        verify(mockEditor, never()).putInt(anyString(), anyInt())
        verify(mockEditor, never()).apply()
    }
}