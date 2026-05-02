package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 1 на 2
 * Добавляет таблицу для обучающих видео
 */
class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Создание таблицы для обучающих видео
        database.execSQL(
            """
            CREATE TABLE training_video (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                fileName TEXT NOT NULL,
                duration INTEGER NOT NULL
            )
            """.trimIndent(),
        )
    }
}
