package com.safeplant.feature.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

/**
 * ViewModel для экрана квиза
 */
class QuizViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val accessPassDao = database.accessPassDao()
    private val quizResultDao = database.quizResultDao()
    
    // Состояние квиза
    private val _quizState = MutableStateFlow<QuizState>(QuizState.NotStarted)
    val quizState: StateFlow<QuizState> = _quizState
    
    // Текущий вопрос
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex
    
    // Текущий вопрос
    private val _currentQuestion = MutableStateFlow<QuizQuestion?>(null)
    val currentQuestion: StateFlow<QuizQuestion?> = _currentQuestion
    
    // Выбранные ответы
    private val _selectedAnswers = MutableStateFlow<List<Int>>(emptyList())
    val selectedAnswers: StateFlow<List<Int>> = _selectedAnswers
    
    // Результат квиза
    private val _quizResult = MutableStateFlow<QuizResult?>(null)
    val quizResult: StateFlow<QuizResult?> = _quizResult
    
    // Сообщение об ошибке
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    /**
     * Состояния квиза
     */
    sealed class QuizState {
        object NotStarted : QuizState()
        object Loading : QuizState()
        object InProgress : QuizState()
        object Completed : QuizState()
        object Error : QuizState()
    }
    
    /**
     * Модель вопроса квиза
     */
    data class QuizQuestion(
        val id: String,
        val question: String,
        val options: List<String>,
        val correctAnswer: String
    )
    
    /**
     * Начать квиз
     */
    fun startQuiz() {
        viewModelScope.launch {
            _quizState.value = QuizState.Loading
            
            try {
                // Загружаем вопросы из assets
                val questions = loadQuestionsFromAssets()
                
                if (questions.isEmpty()) {
                    _quizState.value = QuizState.Error
                    _errorMessage.value = "Не удалось загрузить вопросы квиза"
                    return@launch
                }
                
                // Инициализируем состояние квиза
                _currentQuestionIndex.value = 0
                _selectedAnswers.value = List(questions.size) { -1 }
                _currentQuestion.value = questions.first()
                _quizState.value = QuizState.InProgress
                
            } catch (e: Exception) {
                _quizState.value = QuizState.Error
                _errorMessage.value = "Ошибка при загрузке вопросов: ${e.message}"
            }
        }
    }
    
    /**
     * Выбрать ответ на текущий вопрос
     */
    fun selectAnswer(answerIndex: Int) {
        if (_quizState.value != QuizState.InProgress) return
        
        val currentAnswers = _selectedAnswers.value.toMutableList()
        currentAnswers[_currentQuestionIndex.value] = answerIndex
        _selectedAnswers.value = currentAnswers
    }
    
    /**
     * Перейти к следующему вопросу
     */
    fun nextQuestion() {
        if (_quizState.value != QuizState.InProgress) return
        
        val currentIndex = _currentQuestionIndex.value
        val questions = loadQuestionsFromAssets()
        
        if (currentIndex < questions.size - 1) {
            _currentQuestionIndex.value = currentIndex + 1
            _currentQuestion.value = questions[currentIndex + 1]
        } else {
            // Завершаем квиз
            finishQuiz()
        }
    }
    
    /**
     * Перейти к предыдущему вопросу
     */
    fun previousQuestion() {
        if (_quizState.value != QuizState.InProgress) return
        
        val currentIndex = _currentQuestionIndex.value
        val questions = loadQuestionsFromAssets()
        
        if (currentIndex > 0) {
            _currentQuestionIndex.value = currentIndex - 1
            _currentQuestion.value = questions[currentIndex - 1]
        }
    }
    
    /**
     * Завершить квиз и рассчитать результат
     */
    private fun finishQuiz() {
        viewModelScope.launch {
            try {
                val questions = loadQuestionsFromAssets()
                val answers = _selectedAnswers.value
                
                // Рассчитываем количество правильных ответов
                var correctCount = 0
                questions.forEachIndexed { index, question ->
                    if (answers[index] >= 0 && question.options[answers[index]] == question.correctAnswer) {
                        correctCount++
                    }
                }
                
                // Создаем результат квиза
                val quizResult = QuizResult(
                    id = 0,
                    userId = "default_user", // В реальном приложении ID пользователя
                    score = correctCount,
                    totalQuestions = questions.size,
                    passed = correctCount >= 8, // Требование: 8/10 правильных ответов
                    completedAt = System.currentTimeMillis()
                )
                
                // Сохраняем результат
                quizResultDao.insert(quizResult)
                _quizResult.value = quizResult
                
                // Если квиз пройден, создаем цифровой допуск
                if (quizResult.passed) {
                    createAccessPass()
                }
                
                _quizState.value = QuizState.Completed
                
            } catch (e: Exception) {
                _quizState.value = QuizState.Error
                _errorMessage.value = "Ошибка при завершении квиза: ${e.message}"
            }
        }
    }
    
    /**
     * Создать цифровой допуск
     */
    private fun createAccessPass() {
        viewModelScope.launch {
            try {
                // Рассчитываем дату истечения (30 дней с текущей даты)
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, 30)
                val expiryDate = calendar.timeInMillis
                
                // Создаем допуск
                val accessPass = AccessPass(
                    userId = "default_user", // В реальном приложении ID пользователя
                    issuedAt = System.currentTimeMillis(),
                    expiryDate = expiryDate,
                    isValid = true
                )
                
                // Сохраняем допуск
                accessPassDao.insertOrUpdate(accessPass)
                
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка при создании допуска: ${e.message}"
            }
        }
    }
    
    /**
     * Сбросить квиз
     */
    fun resetQuiz() {
        _quizState.value = QuizState.NotStarted
        _currentQuestionIndex.value = 0
        _selectedAnswers.value = emptyList()
        _currentQuestion.value = null
        _quizResult.value = null
        _errorMessage.value = null
    }
    
    /**
     * Проверить наличие действующего допуска
     */
    fun hasValidAccessPass(): Boolean {
        return viewModelScope.runBlocking {
            val currentTime = System.currentTimeMillis()
            accessPassDao.hasValidAccessPass("default_user", currentTime)
        }
    }
    
    /**
     * Сбросить все допуски (при обновлении версии приложения)
     */
    fun resetAllAccessPasses() {
        viewModelScope.launch {
            accessPassDao.deleteAll()
            resetQuiz()
        }
    }
    
    /**
     * Загрузить вопросы из assets
     */
    private fun loadQuestionsFromAssets(): List<QuizQuestion> {
        val context = getApplication<Application>().applicationContext
        val jsonString = context.assets.open("quiz/questions.json").bufferedReader().use { it.readText() }
        
        val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
        val questions = json.decodeFromString<List<QuizQuestionData>>(jsonString)
        
        return questions.map { questionData ->
            QuizQuestion(
                id = questionData.id,
                question = questionData.question,
                options = questionData.options,
                correctAnswer = questionData.correctAnswer
            )
        }
    }
    
    /**
     * Модель данных для вопроса квиза (для десериализации)
     */
    @kotlinx.serialization.Serializable
    data class QuizQuestionData(
        val id: String,
        val question: String,
        val options: List<String>,
        val correctAnswer: String
    )
}