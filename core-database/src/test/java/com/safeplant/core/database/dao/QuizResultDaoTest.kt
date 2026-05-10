package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizResult
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Интеграционные тесты для QuizResultDao
 * Проверяет работу с базой данных: сохранение и получение результатов
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizResultDaoTest {

    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    /**
     * Тест: сохранение результата
     */
    @Test
    fun insertQuizResult_savesResult() = runBlocking {
        // Подготовка: создаем тестовый результат
        val result = QuizResult(
            timestamp = System.currentTimeMillis(),
            correctAnswers = 8,
            totalQuestions = 10,
            passed = true
        )

        // Действие: сохраняем результат
        database.quizResultDao().insert(result)

        // Проверка: результат сохранен
        val savedResult = database.quizResultDao().getAllResults().firstOrNull()
        assertEquals(result, savedResult)
    }

    /**
     * Тест: получение всех результатов
     */
    @Test
    fun getAllResults_returnsAllResults() = runBlocking {
        // Подготовка: создаем несколько результатов
        val results = listOf(
            QuizResult(
                timestamp = System.currentTimeMillis() - 2000,
                correctAnswers = 7,
                totalQuestions = 10,
                passed = false
            ),
            QuizResult(
                timestamp = System.currentTimeMillis() - 1000,
                correctAnswers = 9,
                totalQuestions = 10,
                passed = true
            ),
            QuizResult(
                timestamp = System.currentTimeMillis(),
                correctAnswers = 8,
                totalQuestions = 10,
                passed = true
            )
        )

        // Действие: сохраняем результаты
        results.forEach { database.quizResultDao().insert(it) }

        // Проверка: возвращаются все результаты
        val savedResults = database.quizResultDao().getAllResults()
        assertEquals(3, savedResults.size)
        assertTrue(savedResults.containsAll(results))
    }

    /**
     * Тест: сортировка результатов по времени (новые первыми)
     */
    @Test
    fun getAllResults_sortedByTimestampDescending() = runBlocking {
        // Подготовка: создаем результаты с разными временными метками
        val results = listOf(
            QuizResult(
                timestamp = System.currentTimeMillis() - 3000,
                correctAnswers = 7,
                totalQuestions = 10,
                passed = false
            ),
            QuizResult(
                timestamp = System.currentTimeMillis() - 2000,
                correctAnswers = 8,
                totalQuestions = 10,
                passed = true
            ),
            QuizResult(
                timestamp = System.currentTimeMillis() - 1000,
                correctAnswers = 9,
                totalQuestions = 10,
                passed = true
            )
        )

        // Действие: сохраняем результаты
        results.forEach { database.quizResultDao().insert(it) }

        // Проверка: результаты отсортированы по убыванию времени
        val savedResults = database.quizResultDao().getAllResults()
        assertEquals(results.reversed(), savedResults)
    }

    /**
     * Тест: сохранение нескольких результатов
     */
    @Test
    fun insertMultipleResults_savesAll() = runBlocking {
        // Подготовка: создаем несколько результатов
        val results = (1..5).map { index ->
            QuizResult(
                timestamp = System.currentTimeMillis() - index * 1000,
                correctAnswers = 5 + index,
                totalQuestions = 10,
                passed = (5 + index) >= 8
            )
        }

        // Действие: сохраняем результаты
        results.forEach { database.quizResultDao().insert(it) }

        // Проверка: все результаты сохранены
        val savedResults = database.quizResultDao().getAllResults()
        assertEquals(5, savedResults.size)
        assertTrue(savedResults.containsAll(results))
    }
}