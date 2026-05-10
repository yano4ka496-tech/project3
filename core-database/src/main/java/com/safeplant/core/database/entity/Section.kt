package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для представления раздела обучающего контента
 * @param id Уникальный идентификатор раздела
 * @param title Название раздела
 * @param description Описание раздела
 * @param parentId Идентификатор родительского раздела (null для корневых)
 * @paramOrder Порядок отображения разделов
 */
@Entity(
    tableName = "section",
    indices = [
        Index(value = ["parentId"]),
        Index(value = ["order"]),
    ],
)
data class Section(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val parentId: Long? = null,
    val order: Int = 0,
)
