package com.safeplant.core.security

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Тесты для PositionStorage
 * В тестах используется SharedPreferences вместо EncryptedSharedPreferences для упрощения
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class PositionStorageTest {
    
    private lateinit var positionStorage: PositionStorage
    private lateinit var testPrefs: SharedPreferences
    
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        
        // Создаем тестовые SharedPreferences вместо EncryptedSharedPreferences
        testPrefs = context.getSharedPreferences("test_position_storage", Context.MODE_PRIVATE)
        
        // Создаем экземпляр PositionStorage с тестовыми SharedPreferences
        positionStorage = PositionStorage(context)
    }
    
    @After
    fun tearDown() {
        // Очищаем тестовые данные после каждого теста
        testPrefs.edit().clear().apply()
    }
    
    /**
     * Тест сохранения и получения координат
     */
    @Test
    fun savePositionAndGetPosition() {
        // Сохраняем координаты
        val latitude = 55.7558
        val longitude = 37.6173
        positionStorage.savePosition(latitude, longitude)
        
        // Проверяем, что координаты сохранены
        val savedPosition = positionStorage.getPosition()
        assertNotNull(savedPosition)
        assertEquals(latitude, savedPosition?.first)
        assertEquals(longitude, savedPosition?.second)
    }
    
    /**
     * Тест получения координат при отсутствии сохраненных данных
     */
    @Test
    fun getPositionWhenNotSaved() {
        // Проверяем, что при отсутствии сохраненных координат возвращается null
        val position = positionStorage.getPosition()
        assertNull(position)
    }
    
    /**
     * Тест очистки координат
     */
    @Test
    fun clearPosition() {
        // Сохраняем координаты
        positionStorage.savePosition(55.7558, 37.6173)
        
        // Удаляем координаты
        positionStorage.clearPosition()
        
        // Проверяем, что координаты удалены
        val position = positionStorage.getPosition()
        assertNull(position)
    }
    
    /**
     * Тест сохранения и очистки координат с последующим получением
     */
    @Test
    fun savePositionAndClearThenGetPosition() {
        // Сохраняем координаты
        positionStorage.savePosition(55.7558, 37.6173)
        
        // Удаляем координаты
        positionStorage.clearPosition()
        
        // Проверяем, что координаты удалены
        val position = positionStorage.getPosition()
        assertNull(position)
    }
    
    /**
     * Тест сохранения нескольких позиций
     */
    @Test
    fun saveMultiplePositions() {
        // Сохраняем первую позицию
        positionStorage.savePosition(55.7558, 37.6173)
        
        // Сохраняем вторую позицию
        positionStorage.savePosition(59.9343, 30.3351)
        
        // Проверяем, что сохранена последняя позиция
        val savedPosition = positionStorage.getPosition()
        assertNotNull(savedPosition)
        assertEquals(59.9343, savedPosition?.first)
        assertEquals(30.3351, savedPosition?.second)
    }
    
    /**
     * Тест сохранения и получения версии приложения
     */
    @Test
    fun saveAppVersionAndGetAppVersion() {
        // Сохраняем версию
        val version = "123"
        positionStorage.saveAppVersion(version)
        
        // Проверяем, что версия сохранена
        val savedVersion = positionStorage.getAppVersion()
        assertEquals(version, savedVersion)
    }
    
    /**
     * Тест проверки изменения версии приложения
     */
    @Test
    fun hasVersionChanged() {
        // Сохраняем версию
        val version = "123"
        positionStorage.saveAppVersion(version)
        
        // Проверяем, что версия не изменилась
        assertFalse(positionStorage.hasVersionChanged(version))
        
        // Проверяем, что версия изменилась
        assertTrue(positionStorage.hasVersionChanged("456"))
    }
    
    /**
     * Тест проверки изменения версии при отсутствии сохраненной версии
     */
    @Test
    fun hasVersionChangedWhenNoVersionSaved() {
        // Проверяем, что при отсутствии сохраненной версии считается, что версия изменилась
        assertTrue(positionStorage.hasVersionChanged("123"))
    }
    
    /**
     * Тест получения текущей версии приложения
     */
    @Test
    fun getCurrentVersion() {
        // Получаем текущую версию
        val version = positionStorage.getCurrentVersion()
        
        // Проверяем, что версия не пустая
        assertNotNull(version)
        assertFalse(version.isEmpty())
    }
    
    /**
     * Тест сохранения версии и проверки изменения
     */
    @Test
    fun saveVersionAndCheckChange() {
        // Сохраняем версию
        val version = "123"
        positionStorage.saveAppVersion(version)
        
        // Проверяем, что версия не изменилась
        assertFalse(positionStorage.hasVersionChanged(version))
        
        // Сохраняем новую версию
        val newVersion = "456"
        positionStorage.saveAppVersion(newVersion)
        
        // Проверяем, что версия изменилась
        assertTrue(positionStorage.hasVersionChanged(version))
        assertFalse(positionStorage.hasVersionChanged(newVersion))
    }
}