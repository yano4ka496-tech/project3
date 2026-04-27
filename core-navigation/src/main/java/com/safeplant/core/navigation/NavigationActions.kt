package com.safeplant.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.MapObjectDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NavigationActions(private val navController: NavController) {
    
    fun navigateToMap(zoneId: String? = null) {
        val route = if (zoneId != null) {
            "${NavigationDestinations.Map.route}?${NavigationDestinations.Map.zoneIdParam}=$zoneId"
        } else {
            NavigationDestinations.Map.route
        }
        navController.navigate(route)
    }
    
    fun navigateToQuiz() {
        navController.navigate(NavigationDestinations.Quiz.route)
    }
    
    fun navigateToTraining() {
        navController.navigate(NavigationDestinations.Training.route)
    }
    
    fun navigateToProfile() {
        navController.navigate(NavigationDestinations.Profile.route)
    }
    
    fun navigateToQR() {
        navController.navigate(NavigationDestinations.QR.route)
    }
    
    fun navigateToZoneNotFound() {
        navController.navigate(NavigationDestinations.ZoneNotFound.route)
    }
    
    fun navigateToAccessDenied() {
        navController.navigate(NavigationDestinations.AccessDenied.route)
    }
    
    fun navigateBack() {
        navController.popBackStack()
    }
    
    /**
     * Обрабатывает навигацию на карту с проверкой допуска
     */
    fun navigateToMapWithAccessCheck(
        zoneId: String?,
        mapObjectDao: MapObjectDao,
        accessPassDao: AccessPassDao,
        onShowQuizDialog: () -> Unit
    ) {
        ErrorHandler.handleMapNavigation(
            zoneId = zoneId,
            navController = navController as NavHostController,
            mapObjectDao = mapObjectDao,
            accessPassDao = accessPassDao,
            onShowQuizDialog = onShowQuizDialog
        )
    }
    
    /**
     * Обрабатывает deep link с немедленным переходом на карту
     */
    fun handleDeepLink(
        zoneId: String?,
        mapObjectDao: MapObjectDao,
        accessPassDao: AccessPassDao,
        onShowQuizDialog: () -> Unit
    ) {
        ErrorHandler.handleDeepLink(
            zoneId = zoneId,
            navController = navController as NavHostController,
            mapObjectDao = mapObjectDao,
            accessPassDao = accessPassDao,
            onShowQuizDialog = onShowQuizDialog
        )
    }
}