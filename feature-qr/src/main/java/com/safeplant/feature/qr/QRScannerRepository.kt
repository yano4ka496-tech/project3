package com.safeplant.feature.qr

import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.database.entity.MapObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с QR-кодами
 * Предоставляет доступ к данным QR-кодов и их валидации
 */
@Singleton
class QRScannerRepository @Inject constructor(
    private val mapObjectDao: MapObjectDao
) {

    /**
     * Получить объект по ID из QR-кода
     */
    suspend fun getObjectByQRCodeId(objectId: Long): MapObject? {
        return mapObjectDao.getMapObjectById(objectId)
    }

    /**
     * Проверить существование объекта по ID
     */
    suspend fun isObjectExists(objectId: Long): Boolean {
        return mapObjectDao.getMapObjectById(objectId) != null
    }
}