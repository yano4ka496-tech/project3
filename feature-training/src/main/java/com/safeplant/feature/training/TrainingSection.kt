package com.safeplant.feature.training

data class TrainingSection(
    val id: String,
    val title: String,
    val content: String,
    val parentId: String? = null,
    val children: List<TrainingSection> = emptyList()
)
