package com.safeplant.feature.training

import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.entity.TrainingVideo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с обучающими видео
 * Предоставляет доступ к видео из базы данных
 */
@Singleton
class TrainingRepository @Inject constructor(
    private val trainingVideoDao: TrainingVideoDao
) {

    /**
     * Получить все обучающие видео
     */
    fun getAllTrainingVideos(): Flow<List<TrainingVideo>> {
        return trainingVideoDao.getAllTrainingVideos()
    }

    /**
     * Получить видео по ID
     */
    suspend fun getTrainingVideoById(id: Long): TrainingVideo? {
        return trainingVideoDao.getTrainingVideoById(id)
    }

    /**
     * Получить видео по названию
     */
    fun getTrainingVideoByTitle(title: String): Flow<List<TrainingVideo>> {
        return trainingVideoDao.getTrainingVideoByTitle(title)
    }
}