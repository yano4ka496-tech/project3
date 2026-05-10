package com.safeplant.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.safeplant.core.security.RootDetector
import com.safeplant.feature.map.MapScreen
import com.safeplant.feature.profile.ProfileScreen
import com.safeplant.feature.profile.ProfileViewModel
import com.safeplant.feature.qr.QRScannerScreen
import com.safeplant.feature.quiz.QuizScreen
import com.safeplant.feature.training.TrainingScreen
import com.safeplant.feature.map.MapViewModel
import com.safeplant.feature.qr.QRScannerViewModel
import com.safeplant.feature.quiz.QuizViewModel
import com.safeplant.feature.training.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Основной навигационный граф приложения
 * Управляет переходами между экранами и проверкой состояний безопасности
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val rootDetector = RootDetector(navController.context)
    
    // Состояния безопасности
    var isRooted by remember { mutableStateOf(false) }
    var hasValidAccess by remember { mutableStateOf(false) }
    var showVersionUpdateDialog by remember { mutableStateOf(false) }
    
    // ViewModel для проверки состояний
    val profileViewModel: ProfileViewModel = hiltViewModel()
    
    // Проверка состояний при запуске
    LaunchedEffect(Unit) {
        // Проверка root-доступа
        isRooted = rootDetector.isRooted()
        
        // Проверка версии приложения и сброс доступа при необходимости
        profileViewModel.checkVersionAndResetAccessIfNeeded()
        
        // Проверка действующего допуска
        hasValidAccess = profileViewModel.isAccessToDangerousZonesAllowed()
        
        // Определение начального экрана
        val startDestination = when {
            isRooted -> NavigationDestinations.Profile.route
            !hasValidAccess -> NavigationDestinations.Profile.route
            profileViewModel.versionChanged.value -> NavigationDestinations.Profile.route
            else -> NavigationDestinations.Map.route
        }
        
        // Установка начального экрана
        navController.graph.startDestination = startDestination
        
        // Если версия изменилась, показываем диалог
        if (profileViewModel.versionChanged.value) {
            showVersionUpdateDialog = true
        }
    }
    
    // Обработка навигации с проверкой состояний
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.Map.route // Будет переопределено в LaunchedEffect
    ) {
        // Экран карты
        composable(NavigationDestinations.Map.route) {
            val mapViewModel: MapViewModel = hiltViewModel()
            
            // Проверка доступа к карте
            LaunchedEffect(Unit) {
                if (!profileViewModel.isAccessToDangerousZonesAllowed()) {
                    navController.navigate(NavigationDestinations.Profile.route) {
                        popUpTo(NavigationDestinations.Map.route) { inclusive = true }
                    }
                }
            }
            
            MapScreen(
                onNavigateToQuiz = { navController.navigate(NavigationDestinations.Quiz.route) },
                onNavigateToTraining = { navController.navigate(NavigationDestinations.Training.route) },
                onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) },
                profileViewModel = profileViewModel
            )
        }
        
        // Экран квиза
        composable(NavigationDestinations.Quiz.route) {
            val quizViewModel: QuizViewModel = hiltViewModel()
            
            // Проверка доступа к квизу
            LaunchedEffect(Unit) {
                if (isRooted) {
                    navController.navigate(NavigationDestinations.Profile.route) {
                        popUpTo(NavigationDestinations.Quiz.route) { inclusive = true }
                    }
                }
            }
            
            QuizScreen(
                onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) },
                onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) }
            )
        }
        
        // Экран обучения
        composable(NavigationDestinations.Training.route) {
            val trainingViewModel: TrainingViewModel = hiltViewModel()
            
            // Проверка доступа к обучению
            LaunchedEffect(Unit) {
                if (isRooted) {
                    navController.navigate(NavigationDestinations.Profile.route) {
                        popUpTo(NavigationDestinations.Training.route) { inclusive = true }
                    }
                }
            }
            
            TrainingScreen(
                onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
            )
        }
        
        // Экран профиля
        composable(NavigationDestinations.Profile.route) {
            ProfileScreen(
                onNavigateToMap = { 
                    // Проверяем доступ перед переходом
                    if (profileViewModel.isAccessToDangerousZonesAllowed()) {
                        navController.navigate(NavigationDestinations.Map.route)
                    }
                },
                onNavigateToQuiz = { 
                    // Проверяем доступ перед переходом
                    if (!isRooted) {
                        navController.navigate(NavigationDestinations.Quiz.route)
                    }
                }
            )
        }
        
        // Экран QR-сканера
        composable(NavigationDestinations.QR.route) {
            val qrScannerViewModel: QRScannerViewModel = hiltViewModel()
            
            QRScannerScreen(
                onNavigateToMap = { lat, lon ->
                    // Проверяем доступ перед переходом
                    if (profileViewModel.isAccessToDangerousZonesAllowed()) {
                        navController.navigate("${NavigationDestinations.Map.route}?lat=$lat&lon=$lon")
                    } else {
                        navController.navigate(NavigationDestinations.Profile.route)
                    }
                },
                onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) }
            )
        }
        
        // Экран ошибки: доступ запрещен
        composable(NavigationDestinations.AccessDenied.route) {
            // В реальном приложении здесь будет компонент диалога
            // Пока просто возвращаемся на профиль
            navController.navigate(NavigationDestinations.Profile.route)
        }
        
        // Экран ошибки: зона не найдена
        composable(NavigationDestinations.ZoneNotFound.route) {
            // В реальном приложении здесь будет компонент диалога
            // Пока просто возвращаемся на карту
            navController.navigate(NavigationDestinations.Map.route)
        }
    }
    
    // Диалог обновления версии
    if (showVersionUpdateDialog) {
        VersionUpdateDialog(
            onConfirm = {
                profileViewModel.confirmVersionUpdate()
                showVersionUpdateDialog = false
            },
            onDismiss = {
                profileViewModel.cancelVersionUpdate()
                showVersionUpdateDialog = false
            }
        )
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