package com.safeplant.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Экран профиля
 * @param onNavigateToMap Функция перехода на экран карты
 * @param onNavigateToQuiz Функция перехода на экран квиза
 */
@Composable
fun profileScreen(
    onNavigateToMap: () -> Unit = {},
    onNavigateToQuiz: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToMap) {
            Text("Перейти на карту")
        }
    }
}
