package com.safeplant.feature.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.safeplant.core.navigation.NavigationDestinations
import kotlinx.coroutines.launch

/**
 * Экран обучения с видеоплеером и плейлистом
 * @param navController Навигационный контроллер для переходов
 * @param onNavigateToMap Функция перехода на экран карты
 */
@Composable
fun TrainingScreen(
    navController: NavController,
    onNavigateToMap: () -> Unit = {},
    viewModel: TrainingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    
    // Обработка ошибок через Snackbar
    LaunchedEffect(uiState) {
        when (uiState) {
            is TrainingUiState.Error -> {
                val errorMessage = (uiState as TrainingUiState.Error).message
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }
            else -> {}
        }
    }
    
    Scaffold(
        topBar = {
            Text(
                text = "Обучение",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is TrainingUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                
                is TrainingUiState.Error -> {
                    val errorMessage = (uiState as TrainingUiState.Error).message
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                
                is TrainingUiState.Ready -> {
                    val state = uiState as TrainingUiState.Ready
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Плейлист видео
                        VideoPlaylist(
                            videos = state.videos,
                            selectedVideo = state.selectedVideo,
                            onVideoSelected = { video ->
                                viewModel.selectVideo(video)
                            }
                        )
                        
                        // Видеоплеер
                        state.selectedVideo?.let { video ->
                            VideoPlayer(
                                videoPath = viewModel.getVideoPath(video.fileName),
                                videoId = video.id,
                                onPositionChanged = { position ->
                                    viewModel.updatePlaybackPosition(video.id, position)
                                },
                                onPlaybackStateChanged = { isPlaying ->
                                    viewModel.updatePlaybackState(video.id, isPlaying)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}