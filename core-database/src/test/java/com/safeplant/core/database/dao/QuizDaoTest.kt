package com.safeplant.core.database.dao

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizQuestion
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Интеграционные тесты для QuizDao
 * Проверяет работу с базой данных: выбор случайных вопросов с детерминированным seed
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class QuizDaoTest {

    @get:Rule
    val migrationTestHelper = MigrationTestHelper(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    )

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
     * Тест: выбор 10 случайных вопросов с детерминированным seed
     */
    @Test
    fun getRandomQuestionsWithSeed_returnsDeterministicQuestions() = runBlocking {
        // Подготовка: создаем 20 вопросов
        val questions = createTestQuestions(20)
        database.quizDao().insertAll(questions)

        // Действие: выбираем вопросы с фиксированным seed
        val seed = 12345L
        val selectedQuestions = database.quizDao().getRandomQuestionsWithSeed(seed)

        // Проверка: возвращается ровно 10 вопросов
        assertEquals(10, selectedQuestions.size)

        // Проверка: при повторном вызове с тем же seed возвращаются те же вопросы
        val selectedQuestionsAgain = database.quizDao().getRandomQuestionsWithSeed(seed)
        assertEquals(selectedQuestions, selectedQuestionsAgain)
    }

    /**
     * Тест: выбор всех вопросов, если их меньше 10
     */
    @Test
    fun getRandomQuestionsWithSeed_returnsAllQuestionsWhenLessThan10() = runBlocking {
        // Подготовка: создаем 5 вопросов
        val questions = createTestQuestions(5)
        database.quizDao().insertAll(questions)

        // Действие: выбираем вопросы
        val selectedQuestions = database.quizDao().getRandomQuestionsWithSeed(12345L)

        // Проверка: возвращаются все 5 вопросов
        assertEquals(5, selectedQuestions.size)
        assertEquals(questions, selectedQuestions)
    }

    /**
     * Тест: выбор 10 вопросов из 15
     */
    @Test
    fun getRandomQuestionsWithSeed_selects10From15() = runBlocking {
        // Подготовка: создаем 15 вопросов
        val questions = createTestQuestions(15)
        database.quizDao().insertAll(questions)

        // Действие: выбираем вопросы
        val selectedQuestions = database.quizDao().getRandomQuestionsWithSeed(12345L)

        // Проверка: возвращается ровно 10 вопросов
        assertEquals(10, selectedQuestions.size)

        // Проверка: все выбранные вопросы из исходного списка
        assertTrue(questions.containsAll(selectedQuestions))
    }

    /**
     * Тест: выбор 10 вопросов из 10
     */
    @Test
    fun getRandomQuestionsWithSeed_selectsAllWhenExactly10() = runBlocking {
        // Подготовка: создаем 10 вопросов
        val questions = createTestQuestions(10)
        database.quizDao().insertAll(questions)

        // Действие: выбираем вопросы
        val selectedQuestions = database.quizDao().getRandomQuestionsWithSeed(12345L)

        // Проверка: возвращаются все 10 вопросов
        assertEquals(10, selectedQuestions.size)
        assertEquals(questions, selectedQuestions)
    }

    /**
     * Тест: выбор 10 вопросов без повторений
     */
    @Test
    fun getRandomQuestionsWithSeed_noDuplicates() = runBlocking {
        // Подготовка: создаем 20 вопросов
        val questions = createTestQuestions(20)
        database.quizDao().insertAll(questions)

        // Действие: выбираем вопросы
        val selectedQuestions = database.quizDao().getRandomQuestionsWithSeed(12345L)

        // Проверка: нет дубликатов
        assertEquals(10, selectedQuestions.size)
        assertEquals(selectedQuestions.toSet().size, selectedQuestions.size)
    }

    /**
     * Вспомогательная функция: создает тестовые вопросы
     * @param count количество вопросов
     * @return список тестовых вопросов
     */
    private fun createTestQuestions(count: Int): List<QuizQuestion> {
        return (1..count).map { index ->
            QuizQuestion(
                id = index.toLong(),
                questionText = "Вопрос $index",
                options = """["Вариант 1", "Вариант 2", "Вариант 3"]""",
                correctAnswerIndex = 0,
                category = "Тестовая категория"
            )
        }
    }
}