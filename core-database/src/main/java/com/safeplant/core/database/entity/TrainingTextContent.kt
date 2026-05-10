package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для представления текстового контента обучающего раздела
 * @param id Уникальный идентификатор контента
 * @param sectionId Идентификатор раздела, к которому относится контент
 * @param title Название контента
 * @param content Текст в формате Markdown
 * @paramOrder Порядок контента в разделе
 */
@Entity(
    tableName = "training_text_content",
    indices = [
        Index(value = ["sectionId"]),
        Index(value = ["order"]),
    ],
)
data class TrainingTextContent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sectionId: Long,
    val title: String,
    val content: String,
    val order: Int = 0,
)
