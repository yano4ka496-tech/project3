package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 3 на 4
 * Добавляет индекс по координатам для ускорения поиска QR-кодов
 */
class Migration3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавление индекса по координатам для ускорения поиска
        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_qr_code_coordinates 
            ON qr_code_mappings (latitude, longitude)
            """.trimIndent()
        )
    }
}