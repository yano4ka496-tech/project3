package com.safeplant.feature.quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun startQuiz() {
        _uiState.value = QuizUiState.Questions(emptyList(), 0)
    }

    fun answerSelected(
        questionIndex: Int,
        answerIndex: Int,
    ) {
        // ничего не делаем, заглушка
    }

    fun finishQuiz() {
        _uiState.value = QuizUiState.Result(0, false)
    }
}

sealed class QuizUiState {
    object Loading : QuizUiState()

    data class Questions(val questions: List<QuizQuestion>, val currentIndex: Int) : QuizUiState()

    data class Result(val score: Int, val passed: Boolean) : QuizUiState()
}

data class QuizQuestion(
    val id: String,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
)
