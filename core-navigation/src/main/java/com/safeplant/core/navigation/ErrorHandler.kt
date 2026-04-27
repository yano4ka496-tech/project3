package com.safeplant.core.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.MapObjectDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object ErrorHandler {
    
    @Composable
    fun showErrorScreen(message: String, navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.error
            )
            
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { navController.popBackStack() }
            ) {
                Text("Назад")
            }
        }
    }
    
    /**
     * Проверяет, существует ли зона с указанным ID
     */
    suspend fun isZoneExists(zoneId: String, mapObjectDao: MapObjectDao): Boolean {
        return mapObjectDao.getById(zoneId) != null
    }
    
    /**
     * Проверяет, действующий ли у пользователя допуск
     */
    suspend fun hasValidAccessPass(mapObjectDao: MapObjectDao, accessPassDao: AccessPassDao, zoneId: String): Boolean {
        val zone = mapObjectDao.getById(zoneId)
        if (zone == null) return false
        
        val accessPass = accessPassDao.getById(zoneId)
        if (accessPass == null) return false
        
        val currentTime = System.currentTimeMillis()
        return accessPass.expiryDate > currentTime
    }
    
    /**
     * Обрабатывает навигацию на карту с проверкой допуска
     */
    fun handleMapNavigation(
        zoneId: String?,
        navController: NavHostController,
        mapObjectDao: MapObjectDao,
        accessPassDao: AccessPassDao,
        onShowQuizDialog: () -> Unit
    ) {
        if (zoneId == null) {
            // Если zoneId не указан, показываем карту с центром по умолчанию
            navController.navigate(NavigationDestinations.Map.route)
            return
        }
        
        // Проверяем, существует ли зона
        val zoneExists = runBlocking {
            isZoneExists(zoneId, mapObjectDao)
        }
        
        if (!zoneExists) {
            // Если зона не найдена, показываем экран ошибки
            navController.navigate(NavigationDestinations.ZoneNotFound.route)
            return
        }
        
        // Проверяем действующий допуск
        val hasAccess = runBlocking {
            hasValidAccessPass(mapObjectDao, accessPassDao, zoneId)
        }
        
        if (!hasAccess) {
            // Если доступа нет, показываем диалог с предложением пройти квиз
            onShowQuizDialog()
        } else {
            // Если доступ есть, переходим на карту
            navController.navigate("${NavigationDestinations.Map.route}?${NavigationDestinations.Map.zoneIdParam}=$zoneId")
        }
    }
    
    /**
     * Обрабатывает deep link с немедленным переходом на карту
     */
    fun handleDeepLink(
        zoneId: String?,
        navController: NavHostController,
        mapObjectDao: MapObjectDao,
        accessPassDao: AccessPassDao,
        onShowQuizDialog: () -> Unit
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        
        // Если пользователь уже не на карте, немедленно переключаемся
        if (currentRoute != NavigationDestinations.Map.route) {
            handleMapNavigation(zoneId, navController, mapObjectDao, accessPassDao, onShowQuizDialog)
        }
        // Если пользователь уже на карте, просто обновляем параметры
        else if (zoneId != null) {
            navController.navigate("${NavigationDestinations.Map.route}?${NavigationDestinations.Map.zoneIdParam}=$zoneId") {
                popUpTo(NavigationDestinations.Map.route) { inclusive = true }
            }
        }
    }
}