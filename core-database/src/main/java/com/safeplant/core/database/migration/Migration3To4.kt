package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 3 на 4
 * Добавляет столбец version в таблицу access_pass и аннулирует все допуски при изменении версии приложения
 */
class Migration3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавление столбца version в таблицу access_pass
        database.execSQL(
            """
            ALTER TABLE access_pass ADD COLUMN version TEXT NOT NULL DEFAULT '1.0'
            """.trimIndent()
        )
        
        // Аннулирование всех допусков при изменении версии приложения
        database.execSQL(
            """
            UPDATE access_pass SET isValid = 0
            """.trimIndent()
        )
    }
}