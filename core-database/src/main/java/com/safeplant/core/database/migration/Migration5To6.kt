package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных с версии 5 на 6
 * Добавление таблиц для обучающего контента: section, training_text_content, bookmark
 */
class Migration5To6 : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Создание таблицы разделов
        database.execSQL(
            """
            CREATE TABLE section (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                description TEXT,
                parentId INTEGER,
                `order` INTEGER NOT NULL,
                FOREIGN KEY (parentId) REFERENCES section(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )
        
        // Создание индексов для таблицы разделов
        database.execSQL("CREATE INDEX index_section_parentId ON section(parentId)")
        database.execSQL("CREATE INDEX index_section_order ON section(`order`)")
        
        // Создание таблицы текстового контента
        database.execSQL(
            """
            CREATE TABLE training_text_content (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                sectionId INTEGER NOT NULL,
                title TEXT NOT NULL,
                content TEXT NOT NULL,
                `order` INTEGER NOT NULL,
                FOREIGN KEY (sectionId) REFERENCES section(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )
        
        // Создание индексов для таблицы текстового контента
        database.execSQL("CREATE INDEX index_training_text_content_sectionId ON training_text_content(sectionId)")
        database.execSQL("CREATE INDEX index_training_text_content_order ON training_text_content(`order`)")
        
        // Создание таблицы закладок
        database.execSQL(
            """
            CREATE TABLE bookmark (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                contentId INTEGER NOT NULL,
                createdAt INTEGER NOT NULL,
                FOREIGN KEY (contentId) REFERENCES training_text_content(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )
        
        // Создание индексов для таблицы закладок
        database.execSQL("CREATE INDEX index_bookmark_contentId ON bookmark(contentId)")
        
        // Добавление корневого раздела по умолчанию
        database.execSQL(
            """
            INSERT INTO section (title, description, `order`) 
            VALUES ('Основные правила', 'Общие правила безопасности на предприятии', 0)
            """
        )
    }
}