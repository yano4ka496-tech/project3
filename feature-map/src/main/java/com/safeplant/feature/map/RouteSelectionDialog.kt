package com.safeplant.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Диалог выбора маршрута
 * Показывает список доступных маршрутов и позволяет выбрать один
 */
@Composable
fun RouteSelectionDialog(
    onRouteSelected: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
    viewModel: RouteSelectionViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите маршрут") },
        text = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Тестовые маршруты
                Button(
                    onClick = {
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
                    onClick = {
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
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}