package com.safeplant.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
 * Отображает информацию о цифровом пропуске и статусе приложения
 */
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState by viewModel.profileState.collectAsState()
    val accessPass by viewModel.accessPass.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.headlineMedium
        )

        // Информация о пропуске
        if (profileState.hasValidPass && accessPass != null) {
            Text(
                text = "Цифровой пропуск действителен",
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Выдан: ${formatDate(accessPass!!.issuedDate)}"
            )

            Text(
                text = "Истекает: ${formatDate(accessPass!!.expiryDate)}"
            )

            Text(
                text = "Результат викторины: ${accessPass!!.score}/10"
            )
        } else {
            Text(
                text = "Цифровой пропуск отсутствует или недействителен",
                color = MaterialTheme.colorScheme.error
            )
        }

        // Информация о версии
        if (profileState.isVersionChanged) {
            Text(
                text = "Обнаружено обновление приложения. Пропуск сброшен.",
                color = MaterialTheme.colorScheme.error
            )
        }

        // Кнопка сброса пропуска
        Button(
            onClick = {
                viewModel.resetAccessPass()
            },
            enabled = profileState.hasValidPass,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сбросить пропуск")
        }
    }
}

/**
 * Форматирование даты для отображения
 */
private fun formatDate(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp
    return "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH) + 1}.${calendar.get(Calendar.YEAR)}"
}