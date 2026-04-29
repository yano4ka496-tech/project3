package com.safeplant.feature.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Экран квиза
 * @param onNavigateToMap Функция перехода на экран карты
 */
@Composable
fun QuizScreen(
    onNavigateToMap: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToMap) {
            Text("Вернуться на карту")
        }
    }
}