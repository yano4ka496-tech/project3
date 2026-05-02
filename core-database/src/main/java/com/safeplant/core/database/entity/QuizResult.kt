package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.safeplant.core.database.Converters

@Entity(tableName = "quiz_results")
@TypeConverters(Converters::class)
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val score: Int,
    val passed: Boolean,
    val timestamp: Long,
)
