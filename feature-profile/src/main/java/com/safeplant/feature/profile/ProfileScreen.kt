package com.safeplant.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Экран профиля пользователя
 * Отображает статус допуска и версию приложения
 */
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToMap: () -> Unit = {},
    onNavigateToQuiz: () -> Unit = {}
) {
    val appVersion by viewModel.appVersion.collectAsState()
    val hasValidAccess by viewModel.hasValidAccess.collectAsState()
    val versionChanged by viewModel.versionChanged.collectAsState()
    val showResetDialog by viewModel.showResetDialog.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Версия приложения: ${appVersion ?: "неизвестна"}"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (hasValidAccess) "Допуск действителен" else "Допуск недействителен",
            color = if (hasValidAccess) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
        )

        if (versionChanged) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Обнаружено обновление версии приложения. Допуски будут сброшены.",
                color = androidx.compose.ui.graphics.Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToMap) {
            Text("На карту")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigateToQuiz) {
            Text("Пройти квиз")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка сброса допуска
        Button(
            onClick = { viewModel.resetAccessManually() },
            enabled = hasValidAccess
        ) {
            Text("Сбросить допуск")
        }
    }

    // Диалог подтверждения сброса допуска
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.cancelManualReset() },
            title = { Text("Подтверждение сброса") },
            text = { 
                Text(
                    "Вы уверены, что хотите сбросить действующий допуск? " +
                    "После сброса вам потребуется пройти квиз заново."
                ) 
            },
            confirmButton = {
                Button(onClick = { viewModel.confirmManualReset() }) {
                    Text("Подтвердить")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.cancelManualReset() }) {
                    Text("Отмена")
                }
            }
        )
    }
}