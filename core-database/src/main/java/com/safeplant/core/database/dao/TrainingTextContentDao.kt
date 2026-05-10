package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.safeplant.core.database.entity.TrainingTextContent
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей текстового контента обучающих разделов
 */
@Dao
interface TrainingTextContentDao {
    /**
     * Получение всего контента
     */
    @Query("SELECT * FROM training_text_content ORDER BY `order`")
    fun getAll(): Flow<List<TrainingTextContent>>

    /**
     * Получение контента по идентификатору раздела
     */
    @Query("SELECT * FROM training_text_content WHERE sectionId = :sectionId ORDER BY `order`")
    fun getBySectionId(sectionId: Long): Flow<List<TrainingTextContent>>

    /**
     * Получение контента по идентификатору
     */
    @Query("SELECT * FROM training_text_content WHERE id = :id")
    suspend fun getById(id: Long): TrainingTextContent?

    /**
     * Вставка нового контента
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: TrainingTextContent): Long

    /**
     * Обновление контента
     */
    @Update
    suspend fun update(content: TrainingTextContent)

    /**
     * Удаление контента
     */
    @Query("DELETE FROM training_text_content WHERE id = :id")
    suspend fun delete(id: Long)
}