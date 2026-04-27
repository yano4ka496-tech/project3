package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 2 на 3
 * Добавляет таблицы для допусков, результатов квиза, обучающих видео и сопоставлений QR-кодов
 */
class Migration2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Создание таблицы для цифровых допусков
        database.execSQL(
            """
            CREATE TABLE access_pass (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId TEXT NOT NULL,
                issuedAt INTEGER NOT NULL,
                expiryDate INTEGER NOT NULL,
                isValid INTEGER NOT NULL DEFAULT 1
            )
            """.trimIndent()
        )
        
        // Создание таблицы для результатов квиза
        database.execSQL(
            """
            CREATE TABLE quiz_result (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId TEXT NOT NULL,
                score INTEGER NOT NULL,
                passed INTEGER NOT NULL,
                timestamp INTEGER NOT NULL
            )
            """.trimIndent()
        )
        
        // Создание таблицы для обучающих видео
        database.execSQL(
            """
            CREATE TABLE training_video (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                fileName TEXT NOT NULL,
                duration INTEGER NOT NULL
            )
            """.trimIndent()
        )
        
        // Создание таблицы для сопоставлений QR-кодов
        database.execSQL(
            """
            CREATE TABLE qr_code_mappings (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                objectId TEXT NOT NULL,
                name TEXT NOT NULL,
                latitude REAL NOT NULL,
                longitude REAL NOT NULL
            )
            """.trimIndent()
        )
        
        // Создание индекса для ускорения поиска по objectId
        database.execSQL(
            """
            CREATE INDEX index_qr_code_mappings_objectId ON qr_code_mappings (objectId)
            """.trimIndent()
        )
    }
}