package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для хранения состояния воспроизведения видео
 * @param videoId Идентификатор видео (внешний ключ на TrainingVideo.id)
 * @param position Позиция воспроизведения в миллисекундах
 * @param lastPlayedDate Дата последнего воспроизведения в миллисекундах с 1970 года
 */
@Entity(
    tableName = "video_playback_state",
    foreignKeys = [
        ForeignKey(
            entity = TrainingVideo::class,
            parentColumns = ["id"],
            childColumns = ["videoId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["videoId"], unique = true),
    ],
)
data class VideoPlaybackState(
    @PrimaryKey(autoGenerate = false)
    val videoId: Long,
    val position: Long,
    val lastPlayedDate: Long,
)
