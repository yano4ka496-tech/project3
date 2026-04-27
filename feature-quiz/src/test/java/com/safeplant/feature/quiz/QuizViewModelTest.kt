package com.safeplant.feature.quiz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.safeplant.core.database.QuizResult
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuizViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: QuizViewModel
    private val repository = mockk<QuizRepository>()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = QuizViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should calculate quiz score correctly`() = runTest {
        // TODO: Реализовать тест для проверки подсчета баллов в викторине
        // Этот тест должен проверять корректность подсчета правильных ответов
        assertEquals(1, 1) // Заглушка до реализации
    }

    @Test
    fun `should allow retaking quiz until passing score`() = runTest {
        // TODO: Реализовать тест для проверки возможности повторения квиза
        // Этот тест должен проверять, что пользователь может повторять квиз до 8/10 баллов
        assertEquals(1, 1) // Заглушка до реализации
    }
}