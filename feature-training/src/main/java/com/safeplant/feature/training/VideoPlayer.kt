package com.safeplant.feature.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import java.util.concurrent.TimeUnit

/**
 * Компонент видеоплеера на основе ExoPlayer
 * @param videoPath Путь к видео файлу
 * @param videoId Идентификатор видео
 * @param onPositionChanged Колбэк при изменении позиции воспроизведения
 * @param onPlaybackStateChanged Колбэк при изменении состояния воспроизведения
 */
@Composable
fun VideoPlayer(
    videoPath: String?,
    videoId: Long,
    onPositionChanged: (Long) -> Unit,
    onPlaybackStateChanged: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var player by remember { mutableStateOf<ExoPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }
    
    // Создание и настройка ExoPlayer
    LaunchedEffect(videoPath) {
        if (videoPath != null) {
            player = ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(videoPath))
                prepare()
                
                // Добавляем слушатель для отслеживания состояния
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        isPlaying = when (playbackState) {
                            Player.STATE_READY, Player.STATE_BUFFERING -> player?.playWhenReady == true
                            else -> false
                        }
                        onPlaybackStateChanged(isPlaying)
                    }
                    
                    override fun onPositionChanged(position: Long) {
                        currentPosition = position
                        onPositionChanged(position)
                    }
                    
                    override fun onDurationChanged(newDuration: Long) {
                        duration = newDuration
                    }
                })
            }
        }
    }
    
    // Очистка плеера при удалении компонента
    DisposableEffect(Unit) {
        onDispose {
            player?.release()
            player = null
        }
    }
    
    // Отображение плеера
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        if (videoPath != null && player != null) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = this@VideoPlayer.player
                        useController = true
                        resizeMode = PlayerView.RESIZE_MODE_FIT
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Если видео не найдено, показываем сообщение
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Видео не найдено",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        
        // Индикатор прогресса
        if (duration > 0) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LinearProgressIndicator(
                    progress = currentPosition.toFloat() / duration,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Text(
                    text = formatTime(currentPosition) + " / " + formatTime(duration),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        }
    }
}

/**
 * Форматирует время в формате MM:SS
 */
private fun formatTime(timeMs: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMs)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMs) % 60
    return String.format("%02d:%02d", minutes, seconds)
}