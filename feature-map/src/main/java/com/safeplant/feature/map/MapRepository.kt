package com.safeplant.feature.map

import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.database.entity.MapObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с данными карты
 * Предоставляет доступ к объектам на карте из базы данных
 */
@Singleton
class MapRepository @Inject constructor(
    private val mapObjectDao: MapObjectDao
) {

    /**
     * Получить все объекты на карте
     */
    fun getAllMapObjects(): Flow<List<MapObject>> {
        return mapObjectDao.getAllMapObjects()
    }

    /**
     * Получить объект по ID
     */
    suspend fun getMapObjectById(id: Long): MapObject? {
        return mapObjectDao.getMapObjectById(id)
    }

    /**
     * Получить опасные зоны
     */
    fun getHazardZones(): Flow<List<MapObject>> {
        return mapObjectDao.getHazardZones()
    }

    /**
     * Получить зоны эвакуации
     */
    fun getEvacuationZones(): Flow<List<MapObject>> {
        return mapObjectDao.getEvacuationZones()
    }

    /**
     * Получить предустановленные маршруты
     */
    fun getRoutes(): Flow<List<MapObject>> {
        return mapObjectDao.getRoutes()
    }
}