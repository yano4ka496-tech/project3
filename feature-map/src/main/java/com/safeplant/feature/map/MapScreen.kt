package com.safeplant.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.mapping.MapRenderer
import org.maplibre.gl.MapView
import org.maplibre.gl.geometry.LatLng

/**
 * Экран карты с поддержкой отображения MBTiles и QR-позиционирования
 * @param lat Широта для позиционирования (опционально)
 * @param lon Долгота для позиционирования (опционально)
 * @param onNavigateToQuiz Функция перехода на экран квиза
 * @param onNavigateToTraining Функция перехода на экран обучения
 * @param onNavigateToProfile Функция перехода на экран профиля
 */
@Composable
fun MapScreen(
    lat: Double = 0.0,
    lon: Double = 0.0,
    onNavigateToQuiz: () -> Unit = {},
    onNavigateToTraining: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val mapView = remember { MapView(context) }
    val mapRenderer = remember { MapRenderer(context, mapView, database) }
    
    // Состояние для карты
    var map by remember { mutableStateOf<MapLibreMap?>(null) }
    
    // Инициализация карты
    LaunchedEffect(Unit) {
        mapRenderer.initializeMap { mapLibreMap ->
            map = mapLibreMap
        }
    }
    
    // Позиционирование по QR-коду
    LaunchedEffect(lat, lon) {
        if (lat != 0.0 && lon != 0.0) {
            mapRenderer.positionOnCoordinates(lat, lon)
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        androidx.compose.ui.viewinterop.AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                view.onResume()
            },
            onRelease = { view ->
                view.onPause()
                view.onDestroy()
            }
        )
    }
}