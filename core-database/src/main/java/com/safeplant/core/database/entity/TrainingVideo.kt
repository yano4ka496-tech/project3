package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Информация об обучающих видео
 * @param id Уникальный идентификатор видео
 * @param title Название видео
 * @param fileName Имя файла видео в хранилище
 * @param duration Длительность видео в секундах
 */
@Entity(tableName = "training_video")
data class TrainingVideo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val fileName: String,
    val duration: Int,
)
