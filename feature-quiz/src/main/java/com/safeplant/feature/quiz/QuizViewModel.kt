package com.safeplant.feature.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * ViewModel для экрана квиза
 * Управляет прохождением теста, проверкой результатов и обновлением допуска
 */
class QuizViewModel(
    private val accessPassDao: AccessPassDao,
    private val quizResultDao: QuizResultDao
) : ViewModel() {
    
    private val _quizState = MutableStateFlow<QuizState>(QuizState.NotStarted)
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()
    
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()
    
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()
    
    private val _questions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val questions: StateFlow<List<QuizQuestion>> = _questions.asStateFlow()
    
    private val _selectedAnswers = MutableStateFlow<MutableMap<Int, Int>>(mutableMapOf())
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()
    
    private val _remainingTime = MutableStateFlow<Long>(0)
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()
    
    private val _accessPass = MutableStateFlow<AccessPass?>(null)
    val accessPass: StateFlow<AccessPass?> = _accessPass.asStateFlow()
    
    /**
     * Состояние квиза
     */
    sealed class QuizState {
        object NotStarted : QuizState()
        object InProgress : QuizState()
        object Completed : QuizState()
        object Passed : QuizState()
        object Failed : QuizState()
    }
    
    /**
     * Инициализация при создании ViewModel
     */
    init {
        loadQuestions()
        loadAccessPass()
    }
    
    /**
     * Загружает вопросы из базы данных
     */
    private fun loadQuestions() {
        viewModelScope.launch {
            // В реальном приложении здесь будет загрузка из базы данных
            // Для примера используем тестовые вопросы
            val testQuestions = listOf(
                QuizQuestion(
                    id = 1,
                    question = "Что делать при обнаружении пожара?",
                    options = listOf("Паниковать", "Немедленно сообщить", "Игнорировать", "Попробовать потушить самому"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 2,
                    question = "Какие средства индивидуальной защиты обязательны при работе в цехе?",
                    options = listOf("Респиратор", "Каска", "Перчатки", "Все перечисленные"),
                    correctAnswer = 3
                ),
                QuizQuestion(
                    id = 3,
                    question = "Как часто проходить инструктаж по технике безопасности?",
                    options = listOf("Раз в месяц", "Раз в квартал", "Раз в полгода", "Перед началом работы"),
                    correctAnswer = 3
                ),
                QuizQuestion(
                    id = 4,
                    question = "Что делать при обнаружении неисправного оборудования?",
                    options = listOf("Продолжить работу", "Сообщить мастеру", "Попробовать починить самому", "Игнорировать"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 5,
                    question = "Какие действия запрещены в цехе?",
                    options = listOf("Использовать средства защиты", "Есть и пить", "Следовать инструкциям", "Сообщать о проблемах"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 6,
                    question = "Что делать при получении травмы?",
                    options = listOf("Продолжить работу", "Немедленно обратиться в медпункт", "Потушить кровь", "Игнорировать"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 7,
                    question = "Какие зоны считаются опасными?",
                    options = listOf("Только цех А", "Все зоны с работающим оборудованием", "Только склад", "Только открытые площадки"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 8,
                    question = "Какие документы нужно иметь при входе в опасную зону?",
                    options = listOf("Паспорт", "Допуск", "Медицинскую книжку", "Все перечисленные"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 9,
                    question = "Что делать при эвакуации?",
                    options = listOf("Оставаться на месте", "Следовать указаниям", "Брать с собой вещи", "Спускаться на лифте"),
                    correctAnswer = 1
                ),
                QuizQuestion(
                    id = 10,
                    question = "Какие действия запрещены при работе с химикатами?",
                    options = listOf("Использовать средства защиты", "Есть и пить", "Следовать инструкциям", "Сообщать о проблемах"),
                    correctAnswer = 1
                )
            )
            _questions.value = testQuestions
        }
    }
    
    /**
     * Загружает действующий допуск
     */
    private fun loadAccessPass() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            _accessPass.value = accessPassDao.getValidAccessPass("default_user", currentTime)
        }
    }
    
    /**
     * Начинает прохождение квиза
     */
    fun startQuiz() {
        _quizState.value = QuizState.InProgress
        _currentQuestionIndex.value = 0
        _score.value = 0
        _selectedAnswers.value = mutableMapOf()
        _remainingTime.value = TimeUnit.MINUTES.toMillis(10) // 10 минут на прохождение
    }
    
    /**
     * Выбирает ответ на текущий вопрос
     */
    fun selectAnswer(questionIndex: Int, answerIndex: Int) {
        if (_quizState.value != QuizState.InProgress) return
        
        _selectedAnswers.value[questionIndex] = answerIndex
        
        // Проверяем, все ли вопросы отвечены
        if (_selectedAnswers.value.size == _questions.value.size) {
            finishQuiz()
        }
    }
    
    /**
     * Завершает квиз и проверяет результат
     */
    private fun finishQuiz() {
        _quizState.value = QuizState.Completed
        
        // Подсчитываем правильные ответы
        var correctCount = 0
        _questions.value.forEachIndexed { index, question ->
            val selectedAnswer = _selectedAnswers.value[index]
            if (selectedAnswer == question.correctAnswer) {
                correctCount++
            }
        }
        
        _score.value = correctCount
        
        // Проверяем, прошел ли тест (8 из 10)
        if (correctCount >= 8) {
            _quizState.value = QuizState.Passed
            updateAccessPass()
        } else {
            _quizState.value = QuizState.Failed
        }
        
        // Сохраняем результат
        saveQuizResult(correctCount)
    }
    
    /**
     * Обновляет срок действия допуска при успешном прохождении
     */
    private fun updateAccessPass() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val expiryDate = currentTime + TimeUnit.DAYS.toMillis(30) // 30 дней
            
            // Создаем новый допуск
            val newAccessPass = AccessPass(
                userId = "default_user",
                issuedAt = currentTime,
                expiryDate = expiryDate,
                isValid = true
            )
            
            // Сохраняем в базу данных
            accessPassDao.insertOrUpdate(newAccessPass)
            
            // Обновляем состояние
            _accessPass.value = newAccessPass
        }
    }
    
    /**
     * Сохраняет результат прохождения квиза
     */
    private fun saveQuizResult(correctCount: Int) {
        viewModelScope.launch {
            val quizResult = QuizResult(
                timestamp = System.currentTimeMillis(),
                correctAnswers = correctCount,
                totalQuestions = _questions.value.size,
                passed = correctCount >= 8
            )
            
            quizResultDao.insert(quizResult)
        }
    }
    
    /**
     * Проверяет, можно ли начать повторное прохождение квиза
     */
    fun canRetakeQuiz(): Boolean {
        val currentTime = System.currentTimeMillis()
        val hasValidPass = accessPassDao.hasValidAccessPass("default_user", currentTime)
        
        // Разрешаем повторное прохождение, если нет действующего допуска
        return !hasValidPass
    }
    
    /**
     * Форматирует оставшееся время в читаемом формате
     */
    fun formatRemainingTime(): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(_remainingTime.value)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(_remainingTime.value) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
    
    /**
     * Обновляет оставшееся время
     */
    fun updateRemainingTime() {
        viewModelScope.launch {
            if (_quizState.value == QuizState.InProgress) {
                _remainingTime.value -= 1000 // Уменьшаем на 1 секунду
                
                // Если время вышло, завершаем тест
                if (_remainingTime.value <= 0) {
                    _remainingTime.value = 0
                    finishQuiz()
                }
            }
        }
    }
    
    /**
     * Сбрасывает состояние квиза
     */
    fun resetQuiz() {
        _quizState.value = QuizState.NotStarted
        _currentQuestionIndex.value = 0
        _score.value = 0
        _selectedAnswers.value = mutableMapOf()
        _remainingTime.value = 0
    }
}

/**
 * Модель вопроса квиза
 */
data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)