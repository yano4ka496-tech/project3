package com.safeplant.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.safeplant.core.navigation.NavigationDestinations

/**
 * Экран выбора маршрута
 * Позволяет пользователю выбрать предустановленный маршрут
 */
@Composable
fun RouteSelectionScreen(
    onRouteSelected: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
    viewModel: RouteSelectionViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Выберите маршрут",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Кнопки для выбора тестовых маршрутов
        Button(
            modifier = Modifier.padding(bottom = 8.dp),
            onClick = {
                // Выбираем тестовый маршрут 1
                val testRoute1 = RouteModel(
                    id = "route_1",
                    name = "Маршрут 1",
                    description = "Основной маршрут через цех А",
                    coordinates = listOf(37.6173, 55.7558, 37.6175, 55.7560, 37.6177, 55.7562)
                )
                viewModel.selectRoute(testRoute1)
                onRouteSelected("route_1")
            }
        ) {
            Text("Маршрут 1")
        }
        
        Button(
            modifier = Modifier.padding(bottom = 8.dp),
            onClick = {
                // Выбираем тестовый маршрут 2
                val testRoute2 = RouteModel(
                    id = "route_2",
                    name = "Маршрут 2",
                    description = "Альтернативный маршрут через цех Б",
                    coordinates = listOf(37.6175, 55.7558, 37.6177, 55.7560, 37.6179, 55.7562)
                )
                viewModel.selectRoute(testRoute2)
                onRouteSelected("route_2")
            }
        ) {
            Text("Маршрут 2")
        }
        
        Button(
            onClick = {
                // Выбираем несуществующий маршрут для теста ошибки
                val invalidRoute = RouteModel(
                    id = "invalid_route",
                    name = "Несуществующий маршрут",
                    description = "Этот маршрут не предустановлен",
                    coordinates = listOf(37.6180, 55.7558, 37.6182, 55.7560)
                )
                viewModel.selectRoute(invalidRoute)
                onRouteSelected("invalid_route")
            }
        ) {
            Text("Несуществующий маршрут")
        }
        
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onDismiss
        ) {
            Text("Отмена")
        }
    }
}