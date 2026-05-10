package com.safeplant.feature.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.entity.AccessPass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val accessPassDao: AccessPassDao
) : ViewModel() {

    private val questions = listOf(
        Question(1, "Какой цвет у травы?", listOf("Красный", "Зелёный", "Синий"), 1),
        Question(2, "Сколько дней в неделе?", listOf("5", "6", "7"), 2),
        Question(3, "Какая планета самая большая?", listOf("Земля", "Юпитер", "Марс"), 1),
        Question(4, "Столица России?", listOf("Москва", "СПб", "Новосибирск"), 0),
        Question(5, "2+2=?", listOf("3", "4", "5"), 1),
        Question(6, "Вода замерзает при 0°C?", listOf("Да", "Нет"), 0),
        Question(7, "Кто написал 'Войну и мир'?", listOf("Достоевский", "Толстой", "Пушкин"), 1),
        Question(8, "Год основания Москвы?", listOf("1147", "1240", "988"), 0),
        Question(9, "Самый большой океан?", listOf("Атлантический", "Индийский", "Тихий"), 2),
        Question(10, "Сколько цветов в радуге?", listOf("5", "6", "7"), 2)
    )

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _answers = MutableStateFlow(Array(questions.size) { -1 })
    val answers: StateFlow<Array<Int>> = _answers.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    val currentQuestion: Question
        get() = questions[_currentQuestionIndex.value]

    val totalQuestions: Int
        get() = questions.size

    fun selectAnswer(answerIndex: Int) {
        val newAnswers = _answers.value.toMutableList()
        newAnswers[_currentQuestionIndex.value] = answerIndex
        _answers.value = newAnswers.toTypedArray()
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < questions.size - 1) {
            _currentQuestionIndex.value++
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
        }
    }

    fun finishQuiz() {
        val correctCount = _answers.value.zip(questions) { selected, q ->
            if (selected == q.correctIndex) 1 else 0
        }.sum()
        _score.value = correctCount
        val success = correctCount >= 8
        _isSuccess.value = success
        _isQuizFinished.value = true

        if (success) {
            viewModelScope.launch {
                val userId = "current_user"
                val issuedAt = System.currentTimeMillis()
                val expiryDate = issuedAt + 30L * 24 * 60 * 60 * 1000 // 30 дней
                val accessPass = AccessPass(
                    userId = userId,
                    issuedAt = issuedAt,
                    expiryDate = expiryDate,
                    isValid = true
                )
                accessPassDao.insertOrUpdate(accessPass)
            }
        }
    }

    fun resetQuiz() {
        _currentQuestionIndex.value = 0
        _answers.value = Array(questions.size) { -1 }
        _isQuizFinished.value = false
        _score.value = 0
        _isSuccess.value = false
    }
}

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)
