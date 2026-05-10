package com.safeplant.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Миграция базы данных из версии 6 в 7
 * Добавляет таблицу video_playback_state для хранения состояния воспроизведения видео
 */
class Migration6To7 : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Создаем таблицу video_playback_state
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `video_playback_state` (
                `videoId` INTEGER NOT NULL, 
                `position` INTEGER NOT NULL, 
                `lastPlayedDate` INTEGER NOT NULL, 
                PRIMARY KEY(`videoId`), 
                FOREIGN KEY(`videoId`) REFERENCES `training_video`(`id`) ON DELETE CASCADE
            )
            """,
        )

        // Создаем уникальный индекс для videoId
        database.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_video_playback_state_videoId` ON `video_playback_state` (`videoId`)",
        )
    }
}
