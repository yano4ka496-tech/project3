package com.safeplant.core.database.dao

import androidx.room.*
import com.safeplant.core.database.entity.TrainingVideo

/**
 * DAO для работы с таблицей обучающих видео
 */
@Dao
interface TrainingVideoDao {
    
    /**
     * Получение всех видео
     */
    @Query("SELECT * FROM training_video")
    suspend fun getAll(): List<TrainingVideo>
    
    /**
     * Получение видео по идентификатору
     */
    @Query("SELECT * FROM training_video WHERE id = :id")
    suspend fun getById(id: Long): TrainingVideo?
    
    /**
     * Вставка нового видео
     */
    @Insert
    suspend fun insert(trainingVideo: TrainingVideo): Long
    
    /**
     * Обновление информации о видео
     */
    @Update
    suspend fun update(trainingVideo: TrainingVideo)
    
    /**
     * Удаление видео
     */
    @Delete
    suspend fun delete(trainingVideo: TrainingVideo)
}