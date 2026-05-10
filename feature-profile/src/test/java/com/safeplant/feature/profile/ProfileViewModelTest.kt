package com.safeplant.feature.profile

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

/**
 * Тесты для ProfileViewModel
 * Проверяют работу с допуском, сроком действия и обновлением версии
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class ProfileViewModelTest {
    
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mockContext: Context
    private lateinit var mockAccessPassDao: AccessPassDao
    private lateinit var mockSharedPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Создаем моки
        mockContext = mockk()
        mockAccessPassDao = mockk()
        mockSharedPreferences = mockk()
        mockEditor = mockk()
        
        // Настраиваем мок SharedPreferences
        every { mockSharedPreferences.edit() } returns mockEditor
        every { mockEditor.putInt(any(), any()) } returns mockEditor
        every { mockEditor.apply() } returns Unit
        
        // Настраиваем мок PackageManager
        val mockPackageManager = mockk<PackageManager>()
        val mockPackageInfo = mockk<PackageInfo>()
        every { mockPackageInfo.versionCode } returns 2
        every { mockPackageManager.getPackageInfo(any(), any()) } returns mockPackageInfo
        every { mockContext.packageManager } returns mockPackageManager
        every { mockContext.packageName } returns "com.safeplant.app"
        
        // Создаем ViewModel
        profileViewModel = ProfileViewModel(mockContext, mockAccessPassDao)
    }
    
    @Test
    fun testAccessStatusLoadingInitially() = runBlocking {
        assertEquals(ProfileViewModel.AccessStatus.Loading, profileViewModel.accessStatus.value)
    }
    
    @Test
    fun testAccessStatusAllowedWithValidPass() = runBlocking {
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.hasValidAccessPass(any(), any()) } returns true
        
        // Обновляем статус
        profileViewModel.updateAccessStatus()
        
        assertEquals(ProfileViewModel.AccessStatus.Allowed, profileViewModel.accessStatus.value)
    }
    
    @Test
    fun testAccessStatusDeniedWithExpiredPass() = runBlocking {
        // Настраиваем мок для возврата просроченного допуса
        every { mockAccessPassDao.hasValidAccessPass(any(), any()) } returns false
        
        // Обновляем статус
        profileViewModel.updateAccessStatus()
        
        assertEquals(ProfileViewModel.AccessStatus.Expired, profileViewModel.accessStatus.value)
    }
    
    @Test
    fun testIsAccessToDangerousZonesAllowed() = runBlocking {
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.hasValidAccessPass(any(), any()) } returns true
        
        // Обновляем статус
        profileViewModel.updateAccessStatus()
        
        assertTrue(profileViewModel.isAccessToDangerousZonesAllowed())
    }
    
    @Test
    fun testIsAccessToDangerousZonesDenied() = runBlocking {
        // Настраиваем мок для возврата просроченного допуса
        every { mockAccessPassDao.hasValidAccessPass(any(), any()) } returns false
        
        // Обновляем статус
        profileViewModel.updateAccessStatus()
        
        assertFalse(profileViewModel.isAccessToDangerousZonesAllowed())
    }
    
    @Test
    fun testCalculateRemainingTimeWithValidPass() = runBlocking {
        // Создаем тестовый действующий допуск
        val testPass = AccessPass(
            id = 1,
            userId = "default_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1),
            isValid = true
        )
        
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.getValidAccessPass(any(), any()) } returns testPass
        
        val remainingTime = profileViewModel.calculateRemainingTime()
        
        assertTrue(remainingTime > 0)
        assertTrue(remainingTime < TimeUnit.DAYS.toMillis(1))
    }
    
    @Test
    fun testCalculateRemainingTimeWithExpiredPass() = runBlocking {
        // Настраиваем мок для возврата null (нет действующего допуска)
        every { mockAccessPassDao.getValidAccessPass(any(), any()) } returns null
        
        val remainingTime = profileViewModel.calculateRemainingTime()
        
        assertEquals(0, remainingTime)
    }
    
    @Test
    fun testFormatRemainingTimeWithDays() = runBlocking {
        // Создаем тестовый действующий допуск с 2 днями
        val testPass = AccessPass(
            id = 1,
            userId = "default_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2),
            isValid = true
        )
        
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.getValidAccessPass(any(), any()) } returns testPass
        
        val formattedTime = profileViewModel.formatRemainingTime()
        
        assertTrue(formattedTime.contains("дн."))
    }
    
    @Test
    fun testFormatRemainingTimeWithHours() = runBlocking {
        // Создаем тестовый действующий допуск с 5 часами
        val testPass = AccessPass(
            id = 1,
            userId = "default_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5),
            isValid = true
        )
        
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.getValidAccessPass(any(), any()) } returns testPass
        
        val formattedTime = profileViewModel.formatRemainingTime()
        
        assertTrue(formattedTime.contains("ч."))
    }
    
    @Test
    fun testFormatRemainingTimeWithMinutes() = runBlocking {
        // Создаем тестовый действующий допуск с 30 минутами
        val testPass = AccessPass(
            id = 1,
            userId = "default_user",
            issuedAt = System.currentTimeMillis(),
            expiryDate = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30),
            isValid = true
        )
        
        // Настраиваем мок для возврата действующего допуска
        every { mockAccessPassDao.getValidAccessPass(any(), any()) } returns testPass
        
        val formattedTime = profileViewModel.formatRemainingTime()
        
        assertTrue(formattedTime.contains("мин."))
    }
    
    @Test
    fun testCheckVersionAndResetAccessIfNeededWithNewVersion() = runBlocking {
        // Настраиваем мок для возврата старой версии
        every { mockSharedPreferences.getInt("app_version", 0) } returns 1
        
        val versionChanged = profileViewModel.checkVersionAndResetAccessIfNeeded()
        
        assertTrue(versionChanged)
        assertTrue(profileViewModel.showVersionUpdateDialog.value)
    }
    
    @Test
    fun testCheckVersionAndResetAccessIfNeededWithSameVersion() = runBlocking {
        // Настраиваем мок для возврата той же версии
        every { mockSharedPreferences.getInt("app_version", 0) } returns 2
        
        val versionChanged = profileViewModel.checkVersionAndResetAccessIfNeeded()
        
        assertFalse(versionChanged)
        assertFalse(profileViewModel.showVersionUpdateDialog.value)
    }
    
    @Test
    fun testConfirmVersionUpdate() = runBlocking {
        // Настраиваем мок для возврата старой версии
        every { mockSharedPreferences.getInt("app_version", 0) } returns 1
        
        // Проверяем обновление версии
        profileViewModel.checkVersionAndResetAccessIfNeeded()
        
        // Подтверждаем обновление
        profileViewModel.confirmVersionUpdate()
        
        // Проверяем, что версия сохранена
        verify { mockEditor.putInt("app_version", 2) }
        verify { mockEditor.apply() }
        
        // Проверяем, что диалог закрыт
        assertFalse(profileViewModel.showVersionUpdateDialog.value)
    }
    
    @Test
    fun testCancelVersionUpdate() = runBlocking {
        // Настраиваем мок для возврата новой версии
        every { mockSharedPreferences.getInt("app_version", 0) } returns 1
        
        // Проверяем обновление версии
        profileViewModel.checkVersionAndResetAccessIfNeeded()
        
        // Отменяем обновление
        profileViewModel.cancelVersionUpdate()
        
        // Проверяем, что диалог закрыт
        assertFalse(profileViewModel.showVersionUpdateDialog.value)
    }
}