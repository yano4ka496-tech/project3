package com.safeplant.core.security

import android.content.Context
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RootDetectorTest {

    private lateinit var rootDetector: RootDetector
    private lateinit var mockContext: Context

    @Before
    fun setup() {
        mockContext = mock(Context::class.java)
        rootDetector = RootDetector(mockContext)
    }

    @Test
    fun `isRooted with rooted device should return true`() {
        // Мокируем RootBeer для возврата true
        val mockRootBeer = mock(com.scottyab.rootbeer.RootBeer::class.java)
        `when`(mockRootBeer.isRooted).thenReturn(true)
        
        // Используем рефлексию для установки приватного поля
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(rootDetector, mockRootBeer)
        
        assertTrue(rootDetector.isRooted())
    }

    @Test
    fun `isRooted with non-rooted device should return false`() {
        // Мокируем RootBeer для возврата false
        val mockRootBeer = mock(com.scottyab.rootbeer.RootBeer::class.java)
        `when`(mockRootBeer.isRooted).thenReturn(false)
        
        // Используем рефлексию для установки приватного поля
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(rootDetector, mockRootBeer)
        
        assertFalse(rootDetector.isRooted())
    }

    @Test
    fun `isRooted with su file should return true`() {
        // Мокируем RootBeer для возврата false, но наличие файла su должно вернуть true
        val mockRootBeer = mock(com.scottyab.rootbeer.RootBeer::class.java)
        `when`(mockRootBeer.isRooted).thenReturn(false)
        
        // Используем рефлексию для установки приватного поля
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(rootDetector, mockRootBeer)
        
        // Мокируем файл su
        val mockFile = mock(java.io.File::class.java)
        `when`(mockFile.exists()).thenReturn(true)
        
        // Мокируем java.io.File для возврата true для пути /system/bin/su
        `when`(java.io.File("/system/bin/su")).thenReturn(mockFile)
        
        assertTrue(rootDetector.isRooted())
    }

    @Test
    fun `showRootWarningDialog with rooted device should return true`() {
        // Мокируем RootBeer для возврата true
        val mockRootBeer = mock(com.scottyab.rootbeer.RootBeer::class.java)
        `when`(mockRootBeer.isRooted).thenReturn(true)
        
        // Используем рефлексию для установки приватного поля
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(rootDetector, mockRootBeer)
        
        assertTrue(rootDetector.showRootWarningDialog())
    }

    @Test
    fun `showRootWarningDialog with non-rooted device should return false`() {
        // Мокируем RootBeer для возврата false
        val mockRootBeer = mock(com.scottyab.rootbeer.RootBeer::class.java)
        `when`(mockRootBeer.isRooted).thenReturn(false)
        
        // Используем рефлексию для установки приватного поля
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(rootDetector, mockRootBeer)
        
        assertFalse(rootDetector.showRootWarningDialog())
    }
}