package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map_object")
data class MapObject(
    @PrimaryKey
    val objectId: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String? = null,
    val category: String? = null
)