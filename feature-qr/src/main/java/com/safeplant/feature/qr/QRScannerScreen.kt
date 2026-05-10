package com.safeplant.feature.qr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.safeplant.core.navigation.NavigationDestinations

/**
 * Экран сканера QR-кодов
 * Позволяет сканировать QR-коды и отображает результаты сканирования
 */
@Composable
fun QRScannerScreen(
    onNavigateToMap: (Double, Double) -> Unit = { _, _ -> },
    onNavigateToProfile: () -> Unit = {},
    viewModel: QRScannerViewModel = hiltViewModel()
) {
    val scanResult by viewModel.scanResult.collectAsState()
    val isScanning by viewModel.isScanning.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Сканирование QR-кода",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Кнопка для тестирования сканирования
            Button(
                onClick = {
                    // Тестовый QR-код для проверки
                    viewModel.processQRCode("123|Цех А")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Тест: QR-код объекта")
            }
            
            // Кнопка для тестирования ошибки
            Button(
                onClick = {
                    // Тестовый QR-код для проверки ошибки
                    viewModel.processQRCode("999|Несуществующий объект")
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Тест: Несуществующий объект")
            }
            
            // Кнопка для тестирования неверного формата
            Button(
                onClick = {
                    // Тестовый QR-код с неверным форматом
                    viewModel.processQRCode("некорректный|формат")
                }
            ) {
                Text("Тест: Неверный формат")
            }
        }
        
        // Результат сканирования
        scanResult?.let { result ->
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                result.error?.let { error ->
                    Text(
                        text = "Ошибка: $error",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                result.warning?.let { warning ->
                    Text(
                        text = "Предупреждение: $warning",
                        color = Color.Yellow,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        
        // Индикатор сканирования
        if (isScanning) {
            Text(
                text = "Сканирование...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }
        
        // Диалог ошибки при сканировании несуществующего QR-кода
        scanResult?.let { result ->
            if (result.error != null) {
                ErrorDialog(
                    message = result.error,
                    onDismiss = {
                        viewModel.clearResult()
                    },
                    onRetry = {
                        viewModel.clearResult()
                        // В реальном приложении здесь будет активация сканера
                    }
                )
            }
        }
    }
}

/**
 * Диалоговое окно ошибки сканирования
 */
@Composable
private fun ErrorDialog(
    message: String,
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Ошибка сканирования") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onRetry) {
                Text("Повторить сканирование")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}