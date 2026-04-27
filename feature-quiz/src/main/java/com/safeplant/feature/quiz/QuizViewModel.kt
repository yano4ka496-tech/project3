package com.safeplant.feature.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * ViewModel для управления состоянием квиза
 */
class QuizViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: QuizRepository
    private val database: AppDatabase
    
    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>> = _questions
    
    private val _currentQuestionIndex = MutableLiveData<Int>()
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex
    
    private val _selectedAnswer = MutableLiveData<String>()
    val selectedAnswer: LiveData<String> = _selectedAnswer
    
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score
    
    private val _quizCompleted = MutableLiveData<Boolean>()
    val quizCompleted: LiveData<Boolean> = _quizCompleted
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private lateinit var allQuestions: List<QuizQuestion>
    private lateinit var selectedQuestions: List<QuizQuestion>
    
    init {
        database = AppDatabase.getDatabase(application)
        repository = QuizRepository(application, database)
        _currentQuestionIndex.value = 0
        _score.value = 0
        _quizCompleted.value = false
        _isLoading.value = true
        
        viewModelScope.launch {
            loadQuestions()
        }
    }
    
    /**
     * Загружает вопросы из assets
     */
    private suspend fun loadQuestions() {
        withContext(Dispatchers.IO) {
            allQuestions = repository.loadQuestions()
            selectedQuestions = selectRandomQuestions(allQuestions, 10)
            _questions.postValue(selectedQuestions)
            _isLoading.postValue(false)
        }
    }
    
    /**
     * Выбирает случайные вопросы
     * @param questions Все вопросы
     * @param count Количество вопросов для выбора
     * @return Список выбранных вопросов
     */
    private fun selectRandomQuestions(questions: List<QuizQuestion>, count: Int): List<QuizQuestion> {
        return questions.shuffled().take(count)
    }
    
    /**
     * Обновляет выбранный ответ
     * @param answer Выбранный ответ
     */
    fun selectAnswer(answer: String) {
        _selectedAnswer.value = answer
    }
    
    /**
     * Переходит к следующему вопросу
     */
    fun nextQuestion() {
        val currentIndex = _currentQuestionIndex.value ?: 0
        if (currentIndex < selectedQuestions.size - 1) {
            _currentQuestionIndex.value = currentIndex + 1
            _selectedAnswer.value = null
        } else {
            completeQuiz()
        }
    }
    
    /**
     * Завершает квиз и сохраняет результат
     */
    private fun completeQuiz() {
        val finalScore = calculateScore()
        _score.value = finalScore
        _quizCompleted.value = true
        
        // Сохраняем результат в базу данных
        viewModelScope.launch {
            val quizResult = QuizResult(
                id = System.currentTimeMillis().toString(),
                userId = "default_user", // В реальном приложении ID пользователя
                score = finalScore,
                passed = finalScore >= 8,
                timestamp = System.currentTimeMillis()
            )
            repository.saveQuizResult(quizResult)
        }
    }
    
    /**
     * Рассчитывает количество правильных ответов
     * @return Количество правильных ответов
     */
    private fun calculateScore(): Int {
        var correctCount = 0
        val currentIndex = _currentQuestionIndex.value ?: 0
        
        for (i in 0..currentIndex) {
            val question = selectedQuestions[i]
            if (_selectedAnswer.value == question.correctAnswer) {
                correctCount++
            }
        }
        
        return correctCount
    }
    
    /**
     * Сбрасывает состояние квиза
     */
    fun resetQuiz() {
        _currentQuestionIndex.value = 0
        _selectedAnswer.value = null
        _score.value = 0
        _quizCompleted.value = false
        
        viewModelScope.launch {
            selectedQuestions = selectRandomQuestions(allQuestions, 10)
            _questions.postValue(selectedQuestions)
        }
    }
}