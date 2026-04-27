package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавление нового столбца isPassValid в таблицу access_pass
        database.execSQL("ALTER TABLE access_pass ADD COLUMN isPassValid INTEGER NOT NULL DEFAULT 1")
    }
}