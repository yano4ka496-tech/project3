package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.safeplant.core.database.entity.Section
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей разделов обучающего контента
 */
@Dao
interface SectionDao {
    /**
     * Получение всех разделов
     */
    @Query("SELECT * FROM section ORDER BY `order`")
    fun getAll(): Flow<List<Section>>

    /**
     * Получение корневых разделов (parentId IS NULL)
     */
    @Query("SELECT * FROM section WHERE parentId IS NULL ORDER BY `order`")
    fun getRootSections(): Flow<List<Section>>

    /**
     * Получение дочерних разделов по parentId
     */
    @Query("SELECT * FROM section WHERE parentId = :parentId ORDER BY `order`")
    fun getChildSections(parentId: Long): Flow<List<Section>>

    /**
     * Получение раздела по идентификатору
     */
    @Query("SELECT * FROM section WHERE id = :id")
    suspend fun getById(id: Long): Section?

    /**
     * Вставка нового раздела
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(section: Section): Long

    /**
     * Обновление информации о разделе
     */
    @Update
    suspend fun update(section: Section)

    /**
     * Удаление раздела
     */
    @Query("DELETE FROM section WHERE id = :id")
    suspend fun delete(id: Long)
}