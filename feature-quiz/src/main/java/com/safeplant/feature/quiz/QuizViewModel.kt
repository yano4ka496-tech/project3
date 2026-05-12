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
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val accessPassDao: AccessPassDao
) : ViewModel() {

    private val _isQuizStarted = MutableStateFlow(false)
    val isQuizStarted: StateFlow<Boolean> = _isQuizStarted.asStateFlow()

    private val _currentQuestions = MutableStateFlow<List<Question>>(emptyList())
    val currentQuestions: StateFlow<List<Question>> = _currentQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _answers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val answers: StateFlow<Map<Int, Int>> = _answers.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    init {
        prepareNewQuiz()
    }

    fun startQuiz() {
        _isQuizStarted.value = true
    }

    private fun prepareNewQuiz() {
        val pool = QuizQuestionPool.allQuestions
        val numberOfQuestions = minOf(10, pool.size)
        val randomQuestions = pool.shuffled().take(numberOfQuestions)
        _currentQuestions.value = randomQuestions
        _currentQuestionIndex.value = 0
        _answers.value = emptyMap()
        _isQuizFinished.value = false
        _score.value = 0
        _isSuccess.value = false
        _isQuizStarted.value = false
    }

    fun selectAnswer(answerIndex: Int) {
        val currentIndex = _currentQuestionIndex.value
        val newAnswers = _answers.value.toMutableMap()
        newAnswers[currentIndex] = answerIndex
        _answers.value = newAnswers
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _currentQuestions.value.size - 1) {
            _currentQuestionIndex.value++
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
        }
    }

    fun finishQuiz() {
        val questions = _currentQuestions.value
        val answerMap = _answers.value
        var correctCount = 0
        for ((index, question) in questions.withIndex()) {
            val selected = answerMap[index]
            if (selected != null && selected == question.correctIndex) {
                correctCount++
            }
        }
        _score.value = correctCount
        val success = correctCount >= 8
        _isSuccess.value = success
        _isQuizFinished.value = true

        if (success) {
            viewModelScope.launch {
                val userId = "current_user"
                val issuedAt = System.currentTimeMillis()
                val expiryDate = issuedAt + 30L * 24 * 60 * 60 * 1000
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
        prepareNewQuiz()
    }
}
