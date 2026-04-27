package com.safeplant.core.database.util

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VersionResetHelperTest {
    private lateinit var mockContext: Context
    private lateinit var mockSharedPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor
    
    @Before
    fun setup() {
        mockContext = mockk<Context>()
        mockSharedPreferences = mockk<SharedPreferences>()
        mockEditor = mockk<SharedPreferences.Editor>()
        
        every { mockContext.getSharedPreferences(any(), any()) } returns mockSharedPreferences
        every { mockSharedPreferences.edit() } returns mockEditor
        every { mockEditor.putString(any(), any()) } returns mockEditor
        every { mockEditor.apply() } returns Unit
    }

    @Test
    fun resetVersionWhenChanged() {
        val versionResetHelper = VersionResetHelper(mockContext)
        
        // Симулируем изменение версии приложения
        every { mockSharedPreferences.getString("app_version", "1.0") } returns "1.0"
        every { mockContext.packageManager.getPackageInfo(any(), 0).versionName } returns "2.0"
        
        val shouldReset = versionResetHelper.shouldResetVersion()
        assertTrue(shouldReset)
        
        // Проверяем, что версия сохранена
        verify { mockEditor.putString("app_version", "2.0") }
    }

    @Test
    fun noResetWhenVersionSame() {
        val versionResetHelper = VersionResetHelper(mockContext)
        
        // Симулируем, что версия не изменилась
        every { mockSharedPreferences.getString("app_version", "1.0") } returns "1.0"
        every { mockContext.packageManager.getPackageInfo(any(), 0).versionName } returns "1.0"
        
        val shouldReset = versionResetHelper.shouldResetVersion()
        assertTrue(!shouldReset)
    }

    @Test
    fun resetWhenFirstLaunch() {
        val versionResetHelper = VersionResetHelper(mockContext)
        
        // Симулируем первый запуск (нет сохраненной версии)
        every { mockSharedPreferences.getString("app_version", "1.0") } returns null
        every { mockContext.packageManager.getPackageInfo(any(), 0).versionName } returns "1.0"
        
        val shouldReset = versionResetHelper.shouldResetVersion()
        assertTrue(shouldReset)
        
        // Проверяем, что версия сохранена
        verify { mockEditor.putString("app_version", "1.0") }
    }
}