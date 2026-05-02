package com.safeplant.core.security

import android.content.Context
import com.scottyab.rootbeer.RootBeer
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Unit-тесты для RootDetector
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class RootDetectorTest {
    
    private lateinit var context: Context
    private lateinit var rootDetector: RootDetector
    
    @Before
    fun setUp() {
        context = mockk()
        rootDetector = RootDetector(context)
    }
    
    /**
     * Тестирование обнаружения root через RootBeer
     */
    @Test
    fun `isRooted should return true when RootBeer detects root`() {
        // Мокаем RootBeer для возврата true
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns true
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        assertTrue("Должен обнаружить root", detector.isRooted())
    }
    
    /**
     * Тестирование отсутствия root через RootBeer
     */
    @Test
    fun `isRooted should return false when RootBeer doesn't detect root`() {
        // Мокаем RootBeer для возврата false
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns false
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        assertFalse("Не должен обнаружить root", detector.isRooted())
    }
    
    /**
     * Тестирование обнаружения root через наличие su-файлов
     */
    @Test
    fun `isRooted should return true when su file exists`() {
        // Мокаем RootBeer для возврата false
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns false
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        // Мокаем файл, который существует
        val file = mockk<java.io.File>()
        every { file.exists() } returns true
        
        // Мокаем java.io.File для возврата нашего мока
        val fileField = RootDetector::class.java.getDeclaredField("paths")
        fileField.isAccessible = true
        fileField.set(detector, arrayOf("/system/bin/su"))
        
        // Мокаем java.io.File constructor
        val fileConstructor = java.io.File::class.java.getDeclaredConstructor(String::class.java)
        fileConstructor.isAccessible = true
        every { fileConstructor.newInstance(any<String>()) } returns file
        
        assertTrue("Должен обнаружить root через su-файл", detector.isRooted())
    }
    
    /**
     * Тестирование обнаружения root через подозрительные приложения
     */
    @Test
    fun `isRooted should return true when suspicious app is installed`() {
        // Мокаем RootBeer для возврата false
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns false
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        // Мок PackageManager для возврата информации о приложении
        val packageManager = mockk<android.content.pm.PackageManager>()
        every { context.packageManager } returns packageManager
        
        // Мок PackageInfo для возврата информации о приложении
        val packageInfo = mockk<android.content.pm.PackageInfo>()
        every { packageManager.getPackageInfo("com.noshufou.android.su", 0) } returns packageInfo
        
        assertTrue("Должен обнаружить root через подозрительное приложение", detector.isRooted())
    }
    
    /**
     * Тестирование отсутствия root при отсутствии подозрительных приложений
     */
    @Test
    fun `isRooted should return false when no suspicious apps are installed`() {
        // Мокаем RootBeer для возврата false
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns false
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        // Мок PackageManager для выброса исключения (приложение не установлено)
        val packageManager = mockk<android.content.pm.PackageManager>()
        every { context.packageManager } returns packageManager
        
        every { packageManager.getPackageInfo(any<String>(), 0) } 
            throws android.content.pm.PackageManager.NameNotFoundException()
        
        assertFalse("Не должен обнаружить root", detector.isRooted())
    }
    
    /**
     * Тестирование обнаружения root через su в PATH
     */
    @Test
    fun `isRooted should return true when su is in PATH`() {
        // Мокаем RootBeer для возврата false
        val rootBeer = mockk<RootBeer>()
        every { rootBeer.isRooted } returns false
        
        // Устанавливаем мокнутый RootDetector
        val detector = RootDetector(context)
        val rootBeerField = RootDetector::class.java.getDeclaredField("rootBeer")
        rootBeerField.isAccessible = true
        rootBeerField.set(detector, rootBeer)
        
        // Мокаем System.getenv для возврата PATH с su
        every { System.getenv("PATH") } returns "/system/bin:/system/xbin:/vendor/bin"
        
        // Мокаем файл, который существует
        val file = mockk<java.io.File>()
        every { file.exists() } returns true
        every { file.canExecute() } returns true
        
        // Мокаем java.io.File для возврата нашего мока
        val fileField = RootDetector::class.java.getDeclaredField("pathDirs")
        fileField.isAccessible = true
        fileField.set(detector, arrayOf("/system/bin", "/system/xbin", "/vendor/bin"))
        
        // Мокаем java.io.File constructor
        val fileConstructor = java.io.File::class.java.getDeclaredConstructor(String::class.java, String::class.java)
        fileConstructor.isAccessible = true
        every { fileConstructor.newInstance(any<String>(), any<String>()) } returns file
        
        assertTrue("Должен обнаружить root через su в PATH", detector.isRooted())
    }
}