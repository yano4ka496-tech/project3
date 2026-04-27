package com.safeplant.feature.qr

import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

/**
 * Экран сканера QR-кодов
 * Предоставляет интерфейс для сканирования QR-кодов и отображения результатов
 */
@Composable
fun QRScannerScreen(
    viewModel: QRScannerViewModel = hiltViewModel(),
    onQRScanned: (Double, Double) -> Unit
) {
    val scannerState by viewModel.scannerState.collectAsState()
    val mapObject by viewModel.mapObject.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.startScanning()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Сканер QR-кодов",
            style = MaterialTheme.typography.headlineMedium
        )

        // Предупреждение о root-доступе
        if (scannerState.errorMessage?.contains("root") == true) {
            Text(
                text = "Предупреждение: обнаружен root-доступ на устройстве",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Состояние сканера
        when (scannerState.scanResult) {
            QRScannerViewModel.ScanResult.IDLE -> {
                Text("Подготовка камеры...")
            }
            QRScannerViewModel.ScanResult.SUCCESS -> {
                Text("QR-код успешно отсканирован!")
                
                mapObject?.let { obj ->
                    Text("Объект: ${obj.name}")
                    Text("Координаты: ${obj.latitude}, ${obj.longitude}")
                    
                    Button(
                        onClick = {
                            onQRScanned(obj.latitude, obj.longitude)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Перейти к объекту на карте")
                    }
                }
            }
            QRScannerViewModel.ScanResult.ERROR -> {
                Text(
                    text = scannerState.errorMessage ?: "Ошибка сканирования",
                    color = MaterialTheme.colorScheme.error
                )
                
                Button(
                    onClick = {
                        viewModel.resetScanner()
                        viewModel.startScanning()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Попробовать снова")
                }
            }
        }

        // Предпросмотр камеры
        if (scannerState.isScanning) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            
            PreviewView(
                modifier = Modifier.fillMaxSize()
            ) { previewView ->
                LaunchedEffect(cameraProviderFuture) {
                    val cameraProvider = cameraProviderFuture.get()
                    
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                    
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            context as androidx.lifecycle.LifecycleOwner,
                            cameraSelector,
                            preview
                        )
                    } catch (exc: Exception) {
                        viewModel.scannerState.value = viewModel.scannerState.value.copy(
                            scanResult = QRScannerViewModel.ScanResult.ERROR,
                            errorMessage = "Ошибка инициализации камеры: ${exc.message}"
                        )
                    }
                }
            }
        }
    }
}