package com.safeplant.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.safeplant.core.database.entity.TrainingVideo
import com.safeplant.core.database.entity.VideoPlaybackState

/**
 * DAO для работы с состоянием воспроизведения видео
 */
@Dao
interface VideoPlaybackStateDao {
    /**
     * Получить позицию воспроизведения для указанного видео
     * @param videoId Идентификатор видео
     * @return Позиция в миллисекундах или null, если состояние не найдено
     */
    @Query("SELECT position FROM video_playback_state WHERE videoId = :videoId")
    suspend fun getPosition(videoId: Long): Long?

    /**
     * Обновить позицию воспроизведения и дату последнего просмотра
     * @param videoId Идентификатор видео
     * @param position Новая позиция в миллисекундах
     * @param lastPlayedDate Дата последнего просмотра в миллисекундах
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePosition(
        videoId: Long,
        position: Long,
        lastPlayedDate: Long,
    )

    /**
     * Получить последнее просмотренное видео
     * @return TrainingVideo или null, если нет записей
     */
    @Query(
        """
        SELECT tv.* 
        FROM training_video tv
        INNER JOIN video_playback_state vps ON tv.id = vps.videoId
        ORDER BY vps.lastPlayedDate DESC
        LIMIT 1
    """,
    )
    suspend fun getLastPlayedVideo(): TrainingVideo?

    /**
     * Вставить или обновить состояние воспроизведения
     * @param videoPlaybackState Состояние воспроизведения
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(videoPlaybackState: VideoPlaybackState)
}
