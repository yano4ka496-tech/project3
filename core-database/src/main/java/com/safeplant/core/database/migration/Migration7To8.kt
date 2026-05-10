package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 7 до 8
 * Добавляет новые поля в таблицы QuizQuestion и QuizResult
 */
val Migration7To8 =
    object : Migration(7, 8) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Добавляем поле category в таблицу quiz_questions
            database.execSQL("ALTER TABLE quiz_questions ADD COLUMN category TEXT NOT NULL DEFAULT 'General'")

            // Добавляем поля correctAnswers, totalQuestions, passed в таблицу quiz_results
            database.execSQL("ALTER TABLE quiz_results ADD COLUMN correctAnswers INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE quiz_results ADD COLUMN totalQuestions INTEGER NOT NULL DEFAULT 0")
            database.execSQL("ALTER TABLE quiz_results ADD COLUMN passed INTEGER NOT NULL DEFAULT 0")
        }
    }
