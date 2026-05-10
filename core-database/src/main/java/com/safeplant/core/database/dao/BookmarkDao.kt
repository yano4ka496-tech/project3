package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.safeplant.core.database.entity.Bookmark
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей закладок
 */
@Dao
interface BookmarkDao {
    /**
     * Получение всех закладок
     */
    @Query("SELECT * FROM bookmark ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Bookmark>>

    /**
     * Получение закладок по идентификатору контента
     */
    @Query("SELECT * FROM bookmark WHERE contentId = :contentId")
    suspend fun getByContentId(contentId: Long): List<Bookmark>

    /**
     * Проверка, есть ли закладка для контента
     */
    @Query("SELECT COUNT(*) FROM bookmark WHERE contentId = :contentId")
    suspend fun hasBookmark(contentId: Long): Int

    /**
     * Вставка новой закладки
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: Bookmark): Long

    /**
     * Обновление закладки
     */
    @Update
    suspend fun update(bookmark: Bookmark)

    /**
     * Удаление закладки
     */
    @Query("DELETE FROM bookmark WHERE id = :id")
    suspend fun delete(id: Long)

    /**
     * Удаление всех закладок для контента
     */
    @Query("DELETE FROM bookmark WHERE contentId = :contentId")
    suspend fun deleteByContentId(contentId: Long)
}