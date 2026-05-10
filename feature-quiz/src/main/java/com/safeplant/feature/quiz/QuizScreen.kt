package com.safeplant.feature.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.safeplant.core.navigation.NavigationDestinations
import com.safeplant.feature.profile.ProfileViewModel

/**
 * Экран квиза
 * @param onNavigateToMap Функция перехода на экран карты
 * @param onNavigateToProfile Функция перехода на экран профиля
 * @param onVersionUpdateDetected Функция вызова при обнаружении обновления версии
 */
@Composable
fun QuizScreen(
    onNavigateToMap: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onVersionUpdateDetected: () -> Unit = {}
) {
    val quizViewModel: QuizViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    
    // Проверка версии приложения при запуске
    LaunchedEffect(Unit) {
        val versionChanged = profileViewModel.checkVersionAndResetAccessIfNeeded()
        if (versionChanged) {
            onVersionUpdateDetected()
        }
    }
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Экран квиза",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Кнопка для тестирования успешного прохождения квиза
            Button(
                onClick = {
                    // При успешном прохождении квиза обновляем допуск
                    quizViewModel.onQuizSuccess()
                    // Переходим на карту
                    onNavigateToMap()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Пройти квиз (успешно)")
            }
            
            // Кнопка для тестирования провала квиза
            Button(
                onClick = {
                    // При провале квиза просто переходим на карту
                    onNavigateToMap()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Пройти квиз (неудачно)")
            }
            
            // Кнопка для перехода на карту
            Button(
                onClick = onNavigateToMap,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Перейти на карту")
            }
            
            // Кнопка для перехода на профиль
            Button(
                onClick = onNavigateToProfile
            ) {
                Text("Перейти в профиль")
            }
        }
        
        // Диалог обновления версии
        if (profileViewModel.showVersionUpdateDialog.collectAsState().value) {
            VersionUpdateDialog(
                onConfirm = {
                    profileViewModel.confirmVersionUpdate()
                },
                onDismiss = {
                    profileViewModel.cancelVersionUpdate()
                }
            )
        }
    }
}

/**
 * Диалоговое окно обновления версии приложения
 */
@Composable
private fun VersionUpdateDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { androidx.compose.material3.Text(text = "Обновление версии приложения") },
        text = { 
            androidx.compose.material3.Text(
                text = "Обнаружено обновление версии приложения. Все данные о допусках будут сброшены. Пройдите квиз для получения нового допуска."
            ) 
        },
        confirmButton = {
            androidx.compose.material3.Button(onClick = onConfirm) {
                androidx.compose.material3.Text("Принять")
            }
        },
        dismissButton = {
            androidx.compose.material3.Button(onClick = onDismiss) {
                androidx.compose.material3.Text("Отмена")
            }
        }
    )
}