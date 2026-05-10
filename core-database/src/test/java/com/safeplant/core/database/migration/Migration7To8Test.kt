package com.safeplant.core.database.migration

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.entity.QuizQuestion
import com.safeplant.core.database.entity.QuizResult
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Тест миграции базы данных с версии 7 до 8
 * Проверяет, что новые поля добавлены корректно и данные сохранены
 */
@RunWith(AndroidJUnit4::class)
class Migration7To8Test {
    @get:Rule
    val migrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        null
    )

    @Test
    @Throws(IOException::class)
    fun migrate7To8() {
        // Создаем базу данных версии 7
        val db = migrationTestHelper.createDatabase(TEST_DB, 7)
        
        // Вставляем тестовые данные в таблицу quiz_questions
        db.execSQL(
            "INSERT INTO quiz_questions (id, questionText, options, correctAnswerIndex) " +
            "VALUES (1, 'Тестовый вопрос?', '[\"Вариант 1\", \"Вариант 2\", \"Вариант 3\"]', 1)"
        )
        
        // Вставляем тестовые данные в таблицу quiz_results
        db.execSQL(
            "INSERT INTO quiz_results (id, timestamp) " +
            "VALUES (1, 1234567890)"
        )
        
        // Закрываем базу данных
        db.close()
        
        // Выполняем миграцию до версии 8
        val migratedDb = migrationTestHelper.runMigrationsAndValidate(TEST_DB, 8, true, Migration7To8)
        
        // Проверяем, что поле category добавлено в таблицу quiz_questions
        val questionCursor = migratedDb.query(
            "SELECT category FROM quiz_questions WHERE id = 1"
        )
        questionCursor.use {
            assert(it.moveToFirst())
            val category = it.getString(it.getColumnIndex("category"))
            assert(category == "General") // Значение по умолчанию
        }
        
        // Проверяем, что поля correctAnswers, totalQuestions, passed добавлены в таблицу quiz_results
        val resultCursor = migratedDb.query(
            "SELECT correctAnswers, totalQuestions, passed FROM quiz_results WHERE id = 1"
        )
        resultCursor.use {
            assert(it.moveToFirst())
            val correctAnswers = it.getInt(it.getColumnIndex("correctAnswers"))
            val totalQuestions = it.getInt(it.getColumnIndex("totalQuestions"))
            val passed = it.getInt(it.getColumnIndex("passed"))
            
            assert(correctAnswers == 0) // Значение по умолчанию
            assert(totalQuestions == 0) // Значение по умолчанию
            assert(passed == 0) // Значение по умолчанию (false)
        }
    }

    companion object {
        private const val TEST_DB = "migration-test"
    }
}