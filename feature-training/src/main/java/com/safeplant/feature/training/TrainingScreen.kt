package com.safeplant.feature.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * Экран обучающего центра
 * Отображает список обучающих видео и позволяет их воспроизводить
 */
@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel()
) {
    val trainingState by viewModel.trainingState.collectAsState()
    val trainingVideos by viewModel.trainingVideos.collectAsState()
    val currentVideo by viewModel.currentVideo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Обучающий центр",
            style = MaterialTheme.typography.headlineMedium
        )

        // Список видео
        if (trainingVideos.isEmpty()) {
            Text("Обучающие видео не найдены")
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                trainingVideos.forEach { video ->
                    Button(
                        onClick = {
                            viewModel.selectVideo(video)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(video.title)
                    }
                }
            }
        }

        // Воспроизведение видео
        currentVideo?.let { video ->
            Text(
                text = "Воспроизведение: ${video.title}",
                style = MaterialTheme.typography.bodyLarge
            )

            // Здесь должен быть PlayerView для ExoPlayer
            // PlayerView(
            //     player = viewModel.player,
            //     modifier = Modifier.fillMaxWidth()
            // )
            
            Button(
                onClick = {
                    viewModel.stopVideo()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Остановить воспроизведение")
            }
        }
    }
}