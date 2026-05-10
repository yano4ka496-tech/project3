package com.safeplant.feature.training

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.dao.VideoPlaybackStateDao
import com.safeplant.core.database.entity.TrainingVideo
import com.safeplant.core.database.entity.VideoPlaybackState
import com.safeplant.core.storage.VideoCopier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана обучения
 * Управляет копированием видео и состоянием воспроизведения
 */
@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val context: Context,
    private val trainingVideoDao: TrainingVideoDao,
    private val videoPlaybackStateDao: VideoPlaybackStateDao,
    private val videoCopier: VideoCopier
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TrainingUiState>(TrainingUiState.Loading)
    val uiState: StateFlow<TrainingUiState> = _uiState.asStateFlow()
    
    private val _selectedVideo = MutableStateFlow<TrainingVideo?>(null)
    val selectedVideo: StateFlow<TrainingVideo?> = _selectedVideo.asStateFlow()
    
    init {
        loadTrainingVideos()
    }
    
    /**
     * Загружает список обучающих видео из базы данных
     */
    private fun loadTrainingVideos() {
        viewModelScope.launch {
            try {
                // Получаем видео из базы данных
                val videos = trainingVideoDao.getAll()
                
                if (videos.isEmpty()) {
                    // Если видео нет в базе, добавляем их
                    initializeVideos()
                    _uiState.value = TrainingUiState.Ready(emptyList(), null)
                } else {
                    // Копируем видео при необходимости
                    copyVideosIfNeeded()
                    
                    // Устанавливаем состояние готовности
                    _uiState.value = TrainingUiState.Ready(videos, null)
                }
            } catch (e: Exception) {
                _uiState.value = TrainingUiState.Error("Ошибка загрузки видео: ${e.message}")
            }
        }
    }
    
    /**
     * Инициализирует видео в базе данных
     */
    private suspend fun initializeVideos() {
        // В реальном приложении здесь будет добавление видео из конфигурации
        // Для примера добавляем тестовое видео
        val testVideo = TrainingVideo(
            title = "Обучение технике безопасности",
            fileName = "safety_training.mp4",
            duration = 300 // 5 минут
        )
        trainingVideoDao.insert(testVideo)
    }
    
    /**
     * Копирует видео из assets во внутреннее хранилище при необходимости
     */
    private fun copyVideosIfNeeded() {
        viewModelScope.launch {
            try {
                val result = videoCopier.copyAllVideos()
                
                if (!result.success) {
                    // Если копирование не удалось, показываем ошибки
                    val errorMessage = if (result.errorMessages.isNotEmpty()) {
                        "Ошибка копирования видео:\n${result.errorMessages.joinToString("\n")}"
                    } else {
                        "Не удалось скопировать видео"
                    }
                    _uiState.value = TrainingUiState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _uiState.value = TrainingUiState.Error("Ошибка копирования видео: ${e.message}")
            }
        }
    }
    
    /**
     * Выбирает видео для воспроизведения
     */
    fun selectVideo(video: TrainingVideo) {
        _selectedVideo.value = video
        
        // Загружаем состояние воспроизведения для этого видео
        viewModelScope.launch {
            val playbackState = videoPlaybackStateDao.getPosition(video.id)
            // В реальном приложении здесь будет обновление состояния плеера
        }
    }
    
    /**
     * Обновляет позицию воспроизведения видео
     */
    fun updatePlaybackPosition(videoId: Long, position: Long) {
        viewModelScope.launch {
            try {
                videoPlaybackStateDao.updatePosition(
                    VideoPlaybackState(
                        videoId = videoId,
                        position = position,
                        lastPlayedDate = System.currentTimeMillis()
                    )
                )
            } catch (e: Exception) {
                // Обработка ошибки сохранения позиции
            }
        }
    }
    
    /**
     * Обновляет состояние воспроизведения (играет/пауза)
     */
    fun updatePlaybackState(videoId: Long, isPlaying: Boolean) {
        // В реальном приложении здесь будет обновление состояния плеера
        viewModelScope.launch {
            try {
                val currentState = videoPlaybackStateDao.getPosition(videoId)
                if (currentState != null) {
                    videoPlaybackStateDao.updatePosition(
                        currentState.copy(
                            lastPlayedDate = System.currentTimeMillis()
                        )
                    )
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }
    
    /**
     * Получает путь к видео файлу
     */
    fun getVideoPath(fileName: String): String? {
        return videoCopier.getVideoPath(fileName)
    }
}

/**
 * Состояние UI для экрана обучения
 */
sealed class TrainingUiState {
    object Loading : TrainingUiState()
    
    data class Error(val message: String) : TrainingUiState()
    
    data class Ready(
        val videos: List<TrainingVideo>,
        val selectedVideo: TrainingVideo?
    ) : TrainingUiState()
}