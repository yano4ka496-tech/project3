package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 2 на 3
 * Добавляет поле isValid в таблицу access_pass и сбрасывает все допуски
 */
class Migration2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавление поля isValid в таблицу access_pass
        database.execSQL("ALTER TABLE access_pass ADD COLUMN isValid INTEGER NOT NULL DEFAULT 1")

        // Сброс всех допусков (установка isValid = 0)
        database.execSQL("UPDATE access_pass SET isValid = 0")
    }
}
