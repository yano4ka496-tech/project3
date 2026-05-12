package com.safeplant.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    // Перезагружаем данные каждый раз, когда экран появляется
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    val accessPass by viewModel.accessPass.collectAsState()
    val appVersion by viewModel.appVersion.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Профиль", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Версия приложения: $appVersion")
            Spacer(modifier = Modifier.height(8.dp))

            if (accessPass != null && accessPass!!.isValid) {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                val expiryText = dateFormat.format(Date(accessPass!!.expiryDate))
                Text("Допуск действителен до: $expiryText")
            } else {
                Text("Допуск отсутствует или истёк")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.resetAccessPass() }) {
                Text("Сбросить допуск")
            }

            Button(onClick = { viewModel.clearPosition() }) {
                Text("Очистить позицию")
            }
        }
    }
}
