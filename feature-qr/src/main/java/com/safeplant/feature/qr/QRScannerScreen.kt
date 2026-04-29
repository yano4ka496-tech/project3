package com.safeplant.feature.qr

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.feature.qr.QRValidator
import org.maplibre.gl.MapLibreMap
import org.maplibre.gl.MapView
import org.maplibre.gl.geometry.LatLng

/**
 * Экран сканирования QR-кодов
 * @param onNavigateToMap Функция перехода на экран карты с координатами
 * @param onNavigateToProfile Функция перехода на экран профиля (при обнаружении root)
 */
@Composable
fun QRScannerScreen(
    onNavigateToMap: (Double, Double) -> Unit = { _, _ -> },
    onNavigateToProfile: () -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val database = remember { AppDatabase.getDatabase(context) }
    val qrCodeDao = remember { database.qrCodeDao() }
    val validator = remember { QRValidator() }
    
    // Состояние для камеры
    var hasCameraPermission by remember { mutableStateOf(false) }
    var qrCodeFound by remember { mutableStateOf<String?>(null) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    // Запрос разрешения на использование камеры
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }
    
    // Проверка разрешения при запуске
    LaunchedEffect(Unit) {
        hasCameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    // Обработка QR-кода
    LaunchedEffect(qrCodeFound) {
        qrCodeFound?.let { qrCode ->
            // Валидация QR-кода
            if (!validator.validate(qrCode)) {
                errorMessage = "Некорректный формат QR-кода. Ожидается: objectId|name"
                showErrorDialog = true
                return@LaunchedEffect
            }
            
            // Извлечение objectId
            val objectId = validator.extractObjectId(qrCode)
            
            // Поиск в базе данных
            val mapping = qrCodeDao.getByObjectId(objectId)
            
            if (mapping != null) {
                // Переход на карту с координатами
                onNavigateToMap(mapping.latitude, mapping.longitude)
            } else {
                errorMessage = "QR-код не найден в базе данных. Попробуйте снова."
                showErrorDialog = true
            }
        }
    }
    
    // Диалог ошибки
    if (showErrorDialog) {
        // В реальном приложении здесь будет компонент диалога
        // Для простоты используем кнопку
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                showErrorDialog = false
                qrCodeFound = null
            }) {
                Text("Повторить сканирование")
            }
        }
    }
    
    // Отображение камеры, если есть разрешение
    if (hasCameraPermission) {
        CameraPreview(
            onQrCodeDetected = { qrCode ->
                qrCodeFound = qrCode
            }
        )
    } else {
        // Запрос разрешения, если его нет
        LaunchedEffect(Unit) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
        
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Запрашивается разрешение на использование камеры...")
        }
    }
}

/**
 * Компонент для отображения камеры и сканирования QR-кодов
 */
@Composable
private fun CameraPreview(
    onQrCodeDetected: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    
    LaunchedEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            
            // Настройка превью
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            
            // Настройка анализа изображений
            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                        // Здесь должна быть логика сканирования QR-кода
                        // Для примера, мы будем имитировать обнаружение QR-кода
                        // В реальном приложении здесь будет библиотека для распознавания QR-кодов
                        // Например, ML Kit или ZXing
                        
                        // Имитация: через 3 секунды "обнаруживаем" QR-код
                        Thread.sleep(3000)
                        onQrCodeDetected("123|Цех А")
                        
                        imageProxy.close()
                    }
                }
            
            // Привязка камеры
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageAnalyzer
            )
        }, ContextCompat.getMainExecutor(context))
    }
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.compose.ui.viewinterop.AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
    }
}