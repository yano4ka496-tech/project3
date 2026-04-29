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
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.security.RootDetector
import com.safeplant.feature.map.MapScreen
import com.safeplant.feature.quiz.QuizScreen
import com.safeplant.feature.profile.ProfileScreen
import com.safeplant.feature.qr.QRScannerScreen
import com.safeplant.feature.training.TrainingScreen
import com.safeplant.feature.map.MapViewModel
import com.safeplant.feature.profile.ProfileViewModel
import com.safeplant.feature.qr.QRScannerViewModel
import com.safeplant.feature.quiz.QuizViewModel
import com.safeplant.feature.training.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Основной навигационный граф приложения
 * Управляет переходами между экранами и проверкой допусков
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val rootDetector = RootDetector(navController.context)
    
    // Состояние для root-доступа
    var isRooted by remember { mutableStateOf(false) }
    
    // Проверка root-доступа при запуске
    LaunchedEffect(Unit) {
        isRooted = rootDetector.isRooted()
    }
    
    NavHost(
        navController = navController,
        startDestination = if (isRooted) NavigationDestinations.Profile.route else NavigationDestinations.Map.route
    ) {
        // Экран карты
        composable(NavigationDestinations.Map.route) {
            val mapViewModel: MapViewModel = hiltViewModel()
            MapScreen(
                onNavigateToQuiz = { navController.navigate(NavigationDestinations.Quiz.route) },
                onNavigateToTraining = { navController.navigate(NavigationDestinations.Training.route) },
                onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) }
            )
        }
        
        // Экран квиза
        composable(NavigationDestinations.Quiz.route) {
            val quizViewModel: QuizViewModel = hiltViewModel()
            QuizScreen(
                onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
            )
        }
        
        // Экран обучения
        composable(NavigationDestinations.Training.route) {
            val trainingViewModel: TrainingViewModel = hiltViewModel()
            TrainingScreen(
                onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
            )
        }
        
        // Экран профиля
        composable(NavigationDestinations.Profile.route) {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) },
                onNavigateToQuiz = { navController.navigate(NavigationDestinations.Quiz.route) }
            )
        }
        
        // Экран QR-сканера
        composable(NavigationDestinations.QR.route) {
            val qrScannerViewModel: QRScannerViewModel = hiltViewModel()
            QRScannerScreen(
                onNavigateToMap = { lat, lon ->
                    navController.navigate("${NavigationDestinations.Map.route}?lat=$lat&lon=$lon")
                },
                onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) }
            )
        }
        
        // Экран ошибки: зона не найдена
        composable(NavigationDestinations.ZoneNotFound.route) {
            // В реальном приложении здесь будет компонент диалога
        }
        
        // Экран ошибки: доступ запрещен
        composable(NavigationDestinations.AccessDenied.route) {
            // В реальном приложении здесь будет компонент диалога
        }
    }
}