package com.safeplant.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(val route: String, val title: String) {
    object Map : Screen("map", "Карта")
    object Quiz : Screen("quiz", "Квиз")
    object Training : Screen("training", "Обучение")
    object QR : Screen("qr", "QR")
    object Profile : Screen("profile", "Профиль")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(
        Screen.Map,
        Screen.Quiz,
        Screen.Training,
        Screen.QR,
        Screen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { /* Иконки можно добавить позже */ },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
