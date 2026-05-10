package com.safeplant.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.BookmarkDao
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.SectionDao
import com.safeplant.core.database.dao.TrainingTextContentDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.dao.VideoPlaybackStateDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.Bookmark
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.database.entity.QuizResult
import com.safeplant.core.database.entity.Section
import com.safeplant.core.database.entity.TrainingTextContent
import com.safeplant.core.database.entity.TrainingVideo
import com.safeplant.core.database.entity.VideoPlaybackState
import com.safeplant.core.database.migration.Migration6To7
import com.safeplant.core.database.migration.Migration7To8

@Database(
    entities = [
        AccessPass::class,
        QuizResult::class,
        TrainingVideo::class,
        QrCodeMapping::class,
        Section::class,
        TrainingTextContent::class,
        Bookmark::class,
        VideoPlaybackState::class
    ],
    version = 8,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accessPassDao(): AccessPassDao

    abstract fun quizResultDao(): QuizResultDao

    abstract fun trainingVideoDao(): TrainingVideoDao

    abstract fun qrCodeDao(): QrCodeDao

    abstract fun sectionDao(): SectionDao

    abstract fun trainingTextContentDao(): TrainingTextContentDao

    abstract fun bookmarkDao(): BookmarkDao

    abstract fun videoPlaybackStateDao(): VideoPlaybackStateDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safeplant_database",
                )
                    .addMigrations(Migration6To7(), Migration7To8())
                    .build()
                this.instance = instance
                instance
            }
        }
    }
}