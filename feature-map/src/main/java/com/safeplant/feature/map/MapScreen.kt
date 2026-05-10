package com.safeplant.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.safeplant.core.mapping.MapController
import com.safeplant.core.mapping.MapView
import com.safeplant.core.mapping.layers.HazardsLayer
import com.safeplant.core.mapping.layers.PopupInfo
import com.safeplant.core.navigation.NavigationDestinations
import com.safeplant.core.security.PositionStorage
import com.safeplant.feature.profile.ProfileViewModel
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style

/**
 * Экран карты с отображением позиции пользователя и управлением маршрутами
 * @param onNavigateToQuiz Функция перехода на экран квиза
 * @param onNavigateToTraining Функция перехода на экран обучения
 * @param onNavigateToProfile Функция перехода на экран профиля
 * @param profileViewModel ViewModel для проверки доступа к опасным зонам
 * @param onVersionUpdateDetected Функция вызова при обнаружении обновления версии
 */
@Composable
fun MapScreen(
    onNavigateToQuiz: () -> Unit = {},
    onNavigateToTraining: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    profileViewModel: ProfileViewModel,
    onVersionUpdateDetected: () -> Unit = {}
) {
    val positionViewModel: PositionViewModel = hiltViewModel()
    val routeSelectionViewModel: RouteSelectionViewModel = hiltViewModel()
    val position by positionViewModel.position.collectAsState()
    val error by positionViewModel.error.collectAsState()
    val selectedRoute by routeSelectionViewModel.selectedRoute.collectAsState()
    val accessResult by routeSelectionViewModel.accessResult.collectAsState()
    val accessPass by routeSelectionViewModel.accessPass.collectAsState()
    val mapController = rememberMapController()
    
    // При изменении позиции анимируем камеру
    LaunchedEffect(position) {
        position?.let { (lat, lon) ->
            mapController.animateCamera(lat, lon, duration = 500)
        }
    }
    
    // При изменении ошибки показываем предупреждение
    LaunchedEffect(error) {
        error?.let { errorMessage ->
            // В реальном приложении здесь будет показ диалога или тоста
            println("Предупреждение: $errorMessage")
        }
    }
    
    // При выборе маршрута проверяем доступ
    LaunchedEffect(selectedRoute) {
        selectedRoute?.let { route ->
            // Проверяем результат доступа
            accessResult?.let { result ->
                if (result == RouteAccessManager.RouteAccessResult.Denied) {
                    // При отсутствии допуска перенаправляем на экран квиза
                    onNavigateToQuiz()
                }
            }
        }
    }
    
    // При запуске загружаем опасные зоны и проверяем версию
    LaunchedEffect(Unit) {
        // В реальном приложении здесь будет загрузка опасных зон из HazardsLayer
        // Для заглушки используем пустой список
        routeSelectionViewModel.setHazardZones(emptyList())
        routeSelectionViewModel.setUserId("default_user")
        routeSelectionViewModel.loadAccessPass()
        
        // Проверяем версию приложения и сбрасываем данные при необходимости
        val versionChanged = profileViewModel.checkVersionAndResetAccessIfNeeded()
        if (versionChanged) {
            onVersionUpdateDetected()
        }
    }
    
    // При изменении выбранного маршрута (когда он становится null) очищаем слой
    LaunchedEffect(selectedRoute) {
        if (selectedRoute == null) {
            // Очищаем слой маршрута
            // В реальном приложении здесь будет вызов метода очистки слоя в MapRenderer
            println("Очистка слоя маршрута")
        }
    }
    
    // Проверяем срок действия допуска и показываем уведомление при истечении
    LaunchedEffect(Unit) {
        val remainingTime = profileViewModel.calculateRemainingTime()
        if (remainingTime > 0 && remainingTime < 60 * 60 * 1000) { // Менее часа
            // Показываем уведомление о скором истечении срока
            println("Внимание: срок действия допуска истекает менее чем через час")
        }
    }
    
    // Проверяем истечение срока допуска и блокируем доступ к опасным зонам
    LaunchedEffect(Unit) {
        val remainingTime = profileViewModel.calculateRemainingTime()
        if (remainingTime <= 0) {
            // Срок действия допуска истек - показываем диалоговое окно
            // В реальном приложении здесь будет показ диалога
            println("Срок действия допуска истек. Пройдите квиз для получения нового допуска.")
            
            // Блокируем доступ к опасным зонам
            // В реальном приложении здесь будет изменение отображения опасных зон
            println("Доступ к опасным зонам заблокирован")
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Карта
        MapView(
            modifier = Modifier.fillMaxSize(),
            onMapReady = { _, controller ->
                mapController.setController(controller)
            },
            onMapClick = { lat, lon ->
                // При клике на карту обновляем позицию
                positionViewModel.updatePosition(lat, lon)
            }
        )
        
        // Панель статуса допуска в верхней части экрана
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Статус допуска
                    val accessAllowed = profileViewModel.isAccessToDangerousZonesAllowed()
                    Text(
                        text = if (accessAllowed) "Допуск действителен" else "Допуск недействителен",
                        color = if (accessAllowed) Color.Green else Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    // Оставшееся время
                    val remainingTime = profileViewModel.calculateRemainingTime()
                    if (remainingTime > 0) {
                        val formattedTime = profileViewModel.formatRemainingTime()
                        Text(
                            text = "Осталось: $formattedTime",
                            color = if (remainingTime < 60 * 60 * 1000) Color.Red else Color.Green,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        Text(
                            text = "Срок действия допуска истек",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
        
        // Кнопка выбора позиции
        Button(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            onClick = {
                // Кнопка для активации режима выбора позиции
                // В реальном приложении здесь может быть изменение состояния карты
                println("Режим выбора позиции активирован")
            }
        ) {
            Text("Выбрать место на карте")
        }
        
        // Кнопка выбора маршрута
        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            onClick = {
                // В реальном приложении здесь будет открытие диалога выбора маршрута
                // Для заглушки выбираем тестовый маршрут
                val testRoute = RouteModel(
                    id = "test_route",
                    name = "Тестовый маршрут",
                    description = "Маршрут для тестирования",
                    coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560)
                )
                routeSelectionViewModel.selectRoute(testRoute)
            }
        ) {
            Text("Выбрать маршрут")
        }
        
        // Кнопка сброса маршрута
        if (selectedRoute != null) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                onClick = {
                    // Сбрасываем выбранный маршрут
                    routeSelectionViewModel.clearSelection()
                }
            ) {
                Text("Сбросить маршрут")
            }
        }
        
        // Кнопка при отсутствии позиции
        if (position == null) {
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    // Переход на экран сканера QR
                    // В реальном приложении здесь будет навигация
                    // Для заглушки просто обновим позицию на центр карты
                    positionViewModel.updatePosition(55.7558, 37.6173) // Москва
                }
            ) {
                Text("Отсканируйте QR или выберите место")
            }
        }
        
        // Маркер позиции пользователя
        position?.let { (lat, lon) ->
            // В реальном приложении здесь будет добавление маркера на карту
            // Для текущей реализации используем заглушку
            println("Маркер позиции: $lat, $lon")
        }
        
        // Информация о выбранном маршруте
        selectedRoute?.let { route ->
            Text(
                text = "Выбран маршрут: ${route.name}",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp),
                color = Color.Blue
            )
        }
        
        // Информация о статусе доступа
        accessResult?.let { result ->
            val statusText = when (result) {
                RouteAccessManager.RouteAccessResult.Allowed -> "Доступ разрешен"
                RouteAccessManager.RouteAccessResult.Denied -> "Доступ запрещен"
            }
            
            Text(
                text = statusText,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                color = if (result == RouteAccessManager.RouteAccessResult.Allowed) Color.Green else Color.Red
            )
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
        
        // Диалог истечения срока действия допуска
        val remainingTime = profileViewModel.calculateRemainingTime()
        if (remainingTime <= 0) {
            AccessExpiredDialog(
                onConfirm = {
                    onNavigateToQuiz()
                },
                onDismiss = {
                    // Просто закрываем диалог, но блокируем доступ к опасным зонам
                }
            )
        }
    }
}

/**
 * Композиция для запоминания MapController
 */
@Composable
private fun rememberMapController(): MapController {
    return remember { MapController() }
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

/**
 * Диалоговое окно истечения срока действия допуска
 */
@Composable
private fun AccessExpiredDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { androidx.compose.material3.Text(text = "Срок действия допуска истек") },
        text = { 
            androidx.compose.material3.Text(
                text = "Срок действия вашего допуска истек. Для продолжения работы с опасными зонами необходимо пройти квиз."
            ) 
        },
        confirmButton = {
            androidx.compose.material3.Button(onClick = onConfirm) {
                androidx.compose.material3.Text("Пройти квиз")
            }
        },
        dismissButton = {
            androidx.compose.material3.Button(onClick = onDismiss) {
                androidx.compose.material3.Text("Отмена")
            }
        }
    )
}