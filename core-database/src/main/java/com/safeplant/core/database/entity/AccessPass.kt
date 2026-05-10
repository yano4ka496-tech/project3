package com.safeplant.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_pass")
data class AccessPass(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val issuedAt: Long,
    val expiryDate: Long,
    var isValid: Boolean = true
)
