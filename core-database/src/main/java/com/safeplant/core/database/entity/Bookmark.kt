package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Сущность для представления закладки пользователя
 * @param id Уникальный идентификатор закладки
 * @param contentId Идентификатор контента, который пользователь добавил в закладки
 * @param createdAt Дата создания закладки
 */
@Entity(
    tableName = "bookmark",
    indices = [
        Index(value = ["contentId"]),
    ],
)
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val contentId: Long,
    val createdAt: Long = System.currentTimeMillis(),
)
