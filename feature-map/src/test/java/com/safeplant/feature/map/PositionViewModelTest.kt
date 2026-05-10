package com.safeplant.feature.map

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.security.PositionStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

/**
 * Тесты для PositionViewModel
 * Проверяют работу с хранением и обновлением позиции
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PositionViewModelTest {

    private lateinit var positionStorage: PositionStorage
    private lateinit var mapObjectDao: MapObjectDao
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
        
        // Создаем мок PositionStorage
        positionStorage = mock(PositionStorage::class.java)
        
        // Создаем мок MapObjectDao
        mapObjectDao = mock(MapObjectDao::class.java)
    }

    @Test
    fun `initial position should be null when no position saved`() = runTest {
        // Настраиваем мок, чтобы возвращать null
        `when`(positionStorage.getPosition()).thenReturn(null)
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        assertNull(viewModel.position.value)
    }

    @Test
    fun `initial position should load from storage when available`() = runTest {
        val testPosition = Pair(55.7558, 37.6173)
        
        // Настраиваем мок, чтобы возвращать тестовую позицию
        `when`(positionStorage.getPosition()).thenReturn(testPosition)
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        assertEquals(testPosition, viewModel.position.value)
    }

    @Test
    fun `updatePosition should save to storage and update state`() = runTest {
        val testPosition = Pair(59.9343, 30.3351)
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Проверяем начальное состояние
        assertNull(viewModel.position.value)
        
        // Обновляем позицию
        viewModel.updatePosition(testPosition.first, testPosition.second)
        
        // Проверяем, что позиция обновилась
        assertEquals(testPosition, viewModel.position.value)
        
        // Проверяем, что мок был вызван с правильными параметрами
        `when`(positionStorage.getPosition()).thenReturn(testPosition)
        assertEquals(testPosition, positionStorage.getPosition())
    }

    @Test
    fun `clearPosition should remove from storage and update state`() = runTest {
        val testPosition = Pair(56.8389, 60.6057)
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Сначала устанавливаем позицию
        viewModel.updatePosition(testPosition.first, testPosition.second)
        assertEquals(testPosition, viewModel.position.value)
        
        // Очищаем позицию
        viewModel.clearPosition()
        
        // Проверяем, что позиция стала null
        assertNull(viewModel.position.value)
        
        // Проверяем, что мок был вызван для очистки
        `when`(positionStorage.getPosition()).thenReturn(null)
        assertNull(positionStorage.getPosition())
    }

    @Test
    fun `multiple position updates should work correctly`() = runTest {
        val firstPosition = Pair(55.7558, 37.6173)
        val secondPosition = Pair(59.9343, 30.3351)
        val thirdPosition = Pair(56.8389, 60.6057)
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Первое обновление
        viewModel.updatePosition(firstPosition.first, firstPosition.second)
        assertEquals(firstPosition, viewModel.position.value)
        
        // Второе обновление
        viewModel.updatePosition(secondPosition.first, secondPosition.second)
        assertEquals(secondPosition, viewModel.position.value)
        
        // Третье обновление
        viewModel.updatePosition(thirdPosition.first, thirdPosition.second)
        assertEquals(thirdPosition, viewModel.position.value)
        
        // Проверяем, что мок возвращает последнюю позицию
        `when`(positionStorage.getPosition()).thenReturn(thirdPosition)
        assertEquals(thirdPosition, positionStorage.getPosition())
    }

    @Test
    fun `isPositionInDangerousZone should return true for dangerous zone`() = runTest {
        val dangerousZone = com.safeplant.core.database.entity.MapObject(
            objectId = "danger1",
            name = "Опасная зона 1",
            latitude = 55.7558,
            longitude = 37.6173,
            category = "dangerous_zone"
        )
        
        // Настраиваем мок, чтобы возвращать опасную зону
        `when`(mapObjectDao.getAllMapObjects()).thenReturn(listOf(dangerousZone))
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Проверяем, что позиция в опасной зоне
        assertTrue(viewModel.isPositionInDangerousZone(55.7558, 37.6173))
    }

    @Test
    fun `isPositionInDangerousZone should return false for safe zone`() = runTest {
        val safeZone = com.safeplant.core.database.entity.MapObject(
            objectId = "safe1",
            name = "Безопасная зона 1",
            latitude = 55.7558,
            longitude = 37.6173,
            category = "safe_zone"
        )
        
        // Настраиваем мок, чтобы возвращать безопасную зону
        `when`(mapObjectDao.getAllMapObjects()).thenReturn(listOf(safeZone))
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Проверяем, что позиция не в опасной зоне
        assertFalse(viewModel.isPositionInDangerousZone(55.7558, 37.6173))
    }

    @Test
    fun `isPositionInDangerousZone should return false when no dangerous zones`() = runTest {
        val safeZone = com.safeplant.core.database.entity.MapObject(
            objectId = "safe1",
            name = "Безопасная зона 1",
            latitude = 55.7558,
            longitude = 37.6173,
            category = "safe_zone"
        )
        
        // Настраиваем мок, чтобы возвращать только безопасные зоны
        `when`(mapObjectDao.getAllMapObjects()).thenReturn(listOf(safeZone))
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Проверяем, что позиция не в опасной зоне
        assertFalse(viewModel.isPositionInDangerousZone(55.7558, 37.6173))
    }

    @Test
    fun `updatePosition should not update position in dangerous zone`() = runTest {
        val dangerousZone = com.safeplant.core.database.entity.MapObject(
            objectId = "danger1",
            name = "Опасная зона 1",
            latitude = 55.7558,
            longitude = 37.6173,
            category = "dangerous_zone"
        )
        
        // Настраиваем мок, чтобы возвращать опасную зону
        `when`(mapObjectDao.getAllMapObjects()).thenReturn(listOf(dangerousZone))
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Пытаемся обновить позицию в опасной зоне
        viewModel.updatePosition(55.7558, 37.6173)
        
        // Проверяем, что позиция не обновилась
        assertNull(viewModel.position.value)
        
        // Проверяем, что ошибка установлена
        assertNotNull(viewModel.error.value)
        assertEquals("Выбранная позиция находится в запрещенной зоне", viewModel.error.value)
    }

    @Test
    fun `updatePosition should update position in safe zone`() = runTest {
        val safeZone = com.safeplant.core.database.entity.MapObject(
            objectId = "safe1",
            name = "Безопасная зона 1",
            latitude = 55.7558,
            longitude = 37.6173,
            category = "safe_zone"
        )
        
        // Настраиваем мок, чтобы возвращать безопасную зону
        `when`(mapObjectDao.getAllMapObjects()).thenReturn(listOf(safeZone))
        
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Обновляем позицию в безопасной зоне
        viewModel.updatePosition(55.7558, 37.6173)
        
        // Проверяем, что позиция обновилась
        assertEquals(Pair(55.7558, 37.6173), viewModel.position.value)
        
        // Проверяем, что ошибки нет
        assertNull(viewModel.error.value)
    }

    @Test
    fun `calculateDistance should return correct distance between two points`() = runTest {
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Расстояние между Москвой и Санкт-Петербургом примерно 634 км
        val distance = viewModel.calculateDistance(55.7558, 37.6173, 59.9343, 30.3351)
        
        // Проверяем, что расстояние примерно равно 634 км (с некоторой погрешностью)
        assertTrue(distance > 630000 && distance < 640000)
    }

    @Test
    fun `calculateDistance should return zero for same point`() = runTest {
        val viewModel = PositionViewModel(positionStorage, mapObjectDao)
        
        // Расстояние между одной и той же точкой должно быть 0
        val distance = viewModel.calculateDistance(55.7558, 37.6173, 55.7558, 37.6173)
        
        // Проверяем, что расстояние равно 0
        assertEquals(0.0, distance, 0.001)
    }
}