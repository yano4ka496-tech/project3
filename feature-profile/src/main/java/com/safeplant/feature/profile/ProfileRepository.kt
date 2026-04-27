package com.safeplant.feature.profile

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с цифровым пропуском
 * Предоставляет доступ к данным пропуска из базы данных
 */
@Singleton
class ProfileRepository @Inject constructor(
    private val accessPassDao: AccessPassDao
) {

    /**
     * Получить все пропуски
     */
    fun getAllAccessPasses(): Flow<List<AccessPass>> {
        return accessPassDao.getAllAccessPasses()
    }

    /**
     * Получить действующий пропуск
     */
    fun getValidAccessPass(): Flow<AccessPass?> {
        return accessPassDao.getValidAccessPass()
    }

    /**
     * Сохранить пропуск
     */
    suspend fun saveAccessPass(pass: AccessPass) {
        accessPassDao.insertAccessPass(pass)
    }

    /**
     * Удалить все пропуски
     */
    suspend fun deleteAllAccessPasses() {
        accessPassDao.deleteAllAccessPasses()
    }
}