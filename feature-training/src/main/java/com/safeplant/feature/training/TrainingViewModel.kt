package com.safeplant.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.entity.TrainingVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для обучающего центра
 * Управляет воспроизведением видео и отображением обучающих материалов
 */
@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val trainingVideoDao: TrainingVideoDao
) : ViewModel() {

    // Состояние обучающего центра
    private val _trainingState = MutableStateFlow(TrainingState())
    val trainingState: StateFlow<TrainingState> = _trainingState

    // Список обучающих видео
    private val _trainingVideos = MutableStateFlow<List<TrainingVideo>>(emptyList())
    val trainingVideos: StateFlow<List<TrainingVideo>> = _trainingVideos

    // Текущее воспроизводимое видео
    private val _currentVideo = MutableStateFlow<TrainingVideo?>(null)
    val currentVideo: StateFlow<TrainingVideo?> = _currentVideo

    /**
     * Загрузить обучающие видео из базы данных
     */
    fun loadTrainingVideos() {
        viewModelScope.launch {
            _trainingVideos.value = trainingVideoDao.getAllTrainingVideos()
        }
    }

    /**
     * Выбрать видео для воспроизведения
     * @param video - видео для воспроизведения
     */
    fun selectVideo(video: TrainingVideo) {
        _currentVideo.value = video
        _trainingState.value = _trainingState.value.copy(
            isVideoPlaying = true
        )
    }

    /**
     * Остановить воспроизведение видео
     */
    fun stopVideo() {
        _currentVideo.value = null
        _trainingState.value = _trainingState.value.copy(
            isVideoPlaying = false
        )
    }

    /**
     * Состояние обучающего центра
     */
    data class TrainingState(
        val isVideoPlaying: Boolean = false,
        val selectedVideoIndex: Int = -1
    )
}