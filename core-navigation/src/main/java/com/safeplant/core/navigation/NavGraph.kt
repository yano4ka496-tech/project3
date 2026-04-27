package com.safeplant.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.safeplant.feature.map.MapScreen
import com.safeplant.feature.quiz.QuizScreen
import com.safeplant.feature.training.TrainingScreen
import com.safeplant.feature.profile.ProfileScreen
import com.safeplant.feature.qr.QRScannerScreen

fun NavGraphBuilder.NavGraph(navController: NavController) {
    // Экран карты
    composable(NavigationDestinations.Map.route) { backStackEntry ->
        val zoneId = backStackEntry.arguments?.getString(NavigationDestinations.Map.zoneIdParam)
        MapScreen(
            lat = zoneId?.toDoubleOrNull() ?: 55.7558,
            lon = zoneId?.toDoubleOrNull() ?: 37.6173,
            onNavigateToQuiz = { navController.navigate(NavigationDestinations.Quiz.route) },
            onNavigateToTraining = { navController.navigate(NavigationDestinations.Training.route) },
            onNavigateToProfile = { navController.navigate(NavigationDestinations.Profile.route) }
        )
    }

    // Экран квиза
    composable(NavigationDestinations.Quiz.route) {
        QuizScreen(
            onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
        )
    }

    // Экран обучения
    composable(NavigationDestinations.Training.route) {
        TrainingScreen(
            onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
        )
    }

    // Экран профиля
    composable(NavigationDestinations.Profile.route) {
        ProfileScreen(
            onNavigateToMap = { navController.navigate(NavigationDestinations.Map.route) }
        )
    }

    // Экран QR-сканера
    composable(NavigationDestinations.QR.route) {
        QRScannerScreen(
            onNavigateToMap = { lat, lon ->
                navController.navigate("${NavigationDestinations.Map.route}?lat=$lat&lon=$lon")
            }
        )
    }

    // Экран ошибки "Зона не найдена"
    composable(NavigationDestinations.ZoneNotFound.route) {
        ErrorHandler.showErrorScreen("Зона не найдена")
    }

    // Экран ошибки "Доступ запрещен"
    composable(NavigationDestinations.AccessDenied.route) {
        ErrorHandler.showErrorScreen("Доступ запрещен")
    }
}