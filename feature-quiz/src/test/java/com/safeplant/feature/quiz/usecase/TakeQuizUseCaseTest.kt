package com.safeplant.feature.quiz.usecase

import com.safeplant.core.database.dao.QuizDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.entity.QuizQuestion
import com.safeplant.core.database.entity.QuizResult
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TakeQuizUseCaseTest {

    private lateinit var quizDao: QuizDao
    private lateinit var quizResultDao: QuizResultDao
    private lateinit var takeQuizUseCase: TakeQuizUseCase

    @Before
    fun setUp() {
        quizDao = mockk()
        quizResultDao = mockk()
        takeQuizUseCase = TakeQuizUseCase(quizDao, quizResultDao)
    }

    @Test
    fun `takeQuiz with 10 questions and all correct answers should return passed result`() = runBlocking {
        // Создаем тестовые вопросы
        val questions = listOf(
            createTestQuestion(1, 0),
            createTestQuestion(2, 1),
            createTestQuestion(3, 0),
            createTestQuestion(4, 2),
            createTestQuestion(5, 1),
            createTestQuestion(6, 0),
            createTestQuestion(7, 2),
            createTestQuestion(8, 1),
            createTestQuestion(9, 0),
            createTestQuestion(10, 2)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Настраиваем мок для quizResultDao
        every { quizResultDao.insert(any()) } answers { call ->
            val result = call.invocation.args.first() as QuizResult
            assertEquals(10, result.totalQuestions)
            assertEquals(10, result.correctAnswers)
            assertTrue(result.passed)
        }
        
        // Вызываем use case с правильными ответами
        val userAnswers = listOf(0, 1, 0, 2, 1, 0, 2, 1, 0, 2)
        val result = takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
        
        // Проверяем результат
        assertEquals(10, result.totalQuestions)
        assertEquals(10, result.correctAnswers)
        assertTrue(result.passed)
        
        // Проверяем, что результат сохранен
        verify(exactly = 1) { quizResultDao.insert(any()) }
    }

    @Test
    fun `takeQuiz with 10 questions and 7 correct answers should return not passed result`() = runBlocking {
        // Создаем тестовые вопросы
        val questions = listOf(
            createTestQuestion(1, 0),
            createTestQuestion(2, 1),
            createTestQuestion(3, 0),
            createTestQuestion(4, 2),
            createTestQuestion(5, 1),
            createTestQuestion(6, 0),
            createTestQuestion(7, 2),
            createTestQuestion(8, 1),
            createTestQuestion(9, 0),
            createTestQuestion(10, 2)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Настраиваем мок для quizResultDao
        every { quizResultDao.insert(any()) } answers { call ->
            val result = call.invocation.args.first() as QuizResult
            assertEquals(10, result.totalQuestions)
            assertEquals(7, result.correctAnswers)
            assertTrue(!result.passed)
        }
        
        // Вызываем use case с 7 правильными ответами
        val userAnswers = listOf(0, 1, 0, 2, 1, 0, 2, 1, 0, 1) // Последний ответ неправильный
        val result = takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
        
        // Проверяем результат
        assertEquals(10, result.totalQuestions)
        assertEquals(7, result.correctAnswers)
        assertTrue(!result.passed)
        
        // Проверяем, что результат сохранен
        verify(exactly = 1) { quizResultDao.insert(any()) }
    }

    @Test
    fun `takeQuiz with 5 questions and all correct answers should return passed result`() = runBlocking {
        // Создаем тестовые вопросы (меньше 10)
        val questions = listOf(
            createTestQuestion(1, 0),
            createTestQuestion(2, 1),
            createTestQuestion(3, 0),
            createTestQuestion(4, 2),
            createTestQuestion(5, 1)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Настраиваем мок для quizResultDao
        every { quizResultDao.insert(any()) } answers { call ->
            val result = call.invocation.args.first() as QuizResult
            assertEquals(5, result.totalQuestions)
            assertEquals(5, result.correctAnswers)
            assertTrue(result.passed)
        }
        
        // Вызываем use case с правильными ответами
        val userAnswers = listOf(0, 1, 0, 2, 1)
        val result = takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
        
        // Проверяем результат
        assertEquals(5, result.totalQuestions)
        assertEquals(5, result.correctAnswers)
        assertTrue(result.passed)
        
        // Проверяем, что результат сохранен
        verify(exactly = 1) { quizResultDao.insert(any()) }
    }

    @Test
    fun `takeQuiz with unanswered questions should count them as incorrect`() = runBlocking {
        // Создаем тестовые вопросы
        val questions = listOf(
            createTestQuestion(1, 0),
            createTestQuestion(2, 1),
            createTestQuestion(3, 0)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Настраиваем мок для quizResultDao
        every { quizResultDao.insert(any()) } answers { call ->
            val result = call.invocation.args.first() as QuizResult
            assertEquals(3, result.totalQuestions)
            assertEquals(1, result.correctAnswers) // Только один правильный ответ
            assertTrue(!result.passed)
        }
        
        // Вызываем use case с одним правильным ответом и двумя неотвеченными
        val userAnswers = listOf(0, null, null)
        val result = takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
        
        // Проверяем результат
        assertEquals(3, result.totalQuestions)
        assertEquals(1, result.correctAnswers)
        assertTrue(!result.passed)
        
        // Проверяем, что результат сохранен
        verify(exactly = 1) { quizResultDao.insert(any()) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `takeQuiz with mismatched question and answer count should throw exception`() = runBlocking {
        // Создаем тестовые вопросы
        val questions = listOf(
            createTestQuestion(1, 0),
            createTestQuestion(2, 1)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Вызываем use case с неправильным количеством ответов
        val userAnswers = listOf(0) // Только один ответ, а должно быть два
        takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `takeQuiz with invalid answer index should throw exception`() = runBlocking {
        // Создаем тестовые вопросы
        val questions = listOf(
            createTestQuestion(1, 0)
        )
        
        // Настраиваем мок для quizDao
        every { quizDao.getRandomQuestionsWithSeed(any()) } returns questions
        
        // Вызываем use case с неверным индексом ответа
        val userAnswers = listOf(5) // Индекс 5, а варианты ответа только 0 и 1
        takeQuizUseCase.takeQuiz(seed = 123, userAnswers = userAnswers)
    }

    /**
     * Вспомогательная функция для создания тестового вопроса
     */
    private fun createTestQuestion(id: Long, correctAnswerIndex: Int): QuizQuestion {
        return QuizQuestion(
            id = id,
            questionText = "Текст вопроса $id",
            options = "[\"Вариант 0\", \"Вариант 1\", \"Вариант 2\"]",
            correctAnswerIndex = correctAnswerIndex,
            category = "Тестовая категория"
        )
    }
}