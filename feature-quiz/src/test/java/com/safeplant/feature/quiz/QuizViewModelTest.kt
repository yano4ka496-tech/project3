package com.safeplant.feature.quiz

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.AccessPass
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Тесты для QuizViewModel
 */
class QuizViewModelTest {
    
    private lateinit var accessPassDao: AccessPassDao
    private lateinit var quizResultDao: QuizResultDao
    private lateinit var viewModel: QuizViewModel
    
    @Before
    fun setup() {
        accessPassDao = mockk()
        quizResultDao = mockk()
        viewModel = QuizViewModel(accessPassDao, quizResultDao)
    }
    
    @Test
    fun testStartQuiz() {
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Проверяем состояние
        assertEquals(QuizViewModel.QuizState.InProgress, viewModel.quizState.value)
        assertEquals(0, viewModel.currentQuestionIndex.value)
        assertEquals(0, viewModel.score.value)
        assertTrue(viewModel.selectedAnswers.value.isEmpty())
    }
    
    @Test
    fun testSelectAnswer() {
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Выбираем ответ
        viewModel.selectAnswer(0, 1)
        
        // Проверяем, что ответ выбран
        assertEquals(1, viewModel.selectedAnswers.value[0])
    }
    
    @Test
    fun testFinishQuizWithPass() = runBlocking {
        // Настраиваем мок для сохранения результата
        every { quizResultDao.insert(any()) } returns Unit
        
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Выбираем ответы (8 правильных из 10)
        for (i in 0..9) {
            viewModel.selectAnswer(i, if (i < 8) 1 else 0) // Первые 8 правильные
        }
        
        // Проверяем состояние после завершения
        assertEquals(QuizViewModel.QuizState.Passed, viewModel.quizState.value)
        assertEquals(8, viewModel.score.value)
    }
    
    @Test
    fun testFinishQuizWithFail() = runBlocking {
        // Настраиваем мок для сохранения результата
        every { quizResultDao.insert(any()) } returns Unit
        
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Выбираем ответы (7 правильных из 10)
        for (i in 0..9) {
            viewModel.selectAnswer(i, if (i < 7) 1 else 0) // Только 7 правильных
        }
        
        // Проверяем состояние после завершения
        assertEquals(QuizViewModel.QuizState.Failed, viewModel.quizState.value)
        assertEquals(7, viewModel.score.value)
    }
    
    @Test
    fun testCanRetakeQuizWithValidPass() = runBlocking {
        // Настраиваем мок
        every { accessPassDao.hasValidAccessPass(any(), any()) } returns true
        
        // Проверяем, можно ли пройти квиз повторно
        assertFalse(viewModel.canRetakeQuiz())
    }
    
    @Test
    fun testCanRetakeQuizWithExpiredPass() = runBlocking {
        // Настраиваем мок
        every { accessPassDao.hasValidAccessPass(any(), any()) } returns false
        
        // Проверяем, можно ли пройти квиз повторно
        assertTrue(viewModel.canRetakeQuiz())
    }
    
    @Test
    fun testUpdateAccessPass() = runBlocking {
        // Настраиваем мок
        every { accessPassDao.insertOrUpdate(any()) } returns Unit
        
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Выбираем ответы (8 правильных из 10)
        for (i in 0..9) {
            viewModel.selectAnswer(i, if (i < 8) 1 else 0) // Первые 8 правильные
        }
        
        // Проверяем, что допуск обновлен
        assertNotNull(viewModel.accessPass.value)
        assertTrue(viewModel.accessPass.value?.isValid ?: false)
    }
    
    @Test
    fun testFormatRemainingTime() {
        // Устанавливаем оставшееся время 5 минут
        viewModel._remainingTime.value = TimeUnit.MINUTES.toMillis(5)
        
        // Форматируем время
        val formattedTime = viewModel.formatRemainingTime()
        
        // Проверяем формат
        assertEquals("05:00", formattedTime)
    }
    
    @Test
    fun testUpdateRemainingTime() = runBlocking {
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Устанавливаем оставшееся время 1 секунду
        viewModel._remainingTime.value = 1000
        
        // Обновляем время
        viewModel.updateRemainingTime()
        
        // Проверяем, что время уменьшилось
        assertEquals(0, viewModel.remainingTime.value)
        assertEquals(QuizViewModel.QuizState.Failed, viewModel.quizState.value)
    }
    
    @Test
    fun testResetQuiz() {
        // Начинаем квиз
        viewModel.startQuiz()
        
        // Сбрасываем квиз
        viewModel.resetQuiz()
        
        // Проверяем состояние
        assertEquals(QuizViewModel.QuizState.NotStarted, viewModel.quizState.value)
        assertEquals(0, viewModel.currentQuestionIndex.value)
        assertEquals(0, viewModel.score.value)
        assertTrue(viewModel.selectedAnswers.value.isEmpty())
        assertEquals(0, viewModel.remainingTime.value)
    }
}