package com.safeplant.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MapScreen(lat: Double = 0.0, lon: Double = 0.0, onNavigateToQuiz: () -> Unit = {}, onNavigateToTraining: () -> Unit = {}, onNavigateToProfile: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Карта (заглушка)", fontSize = 24.sp)
    }
}
