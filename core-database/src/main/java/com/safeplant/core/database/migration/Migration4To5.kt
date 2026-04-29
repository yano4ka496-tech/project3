package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 4 на 5
 * Подготовка к включению шифрования (пустая миграция)
 */
class Migration4To5 : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Пустая миграция - шифрование будет настроено в AppDatabase
        // Эта миграция нужна для увеличения версии базы данных до 5
    }
}