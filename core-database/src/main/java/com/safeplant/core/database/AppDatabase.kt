package com.safeplant.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportFactory
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.database.entity.QuizResult
import com.safeplant.core.database.entity.TrainingVideo
import com.safeplant.core.database.migration.Migration1To2
import com.safeplant.core.database.migration.Migration2To3
import com.safeplant.core.database.migration.Migration3To4

/**
 * Основная база данных приложения SafePlant
 * Версия 4: добавлена миграция для сброса допусков при обновлении версии приложения
 */
@Database(
    entities = [
        AccessPass::class,
        QuizResult::class,
        TrainingVideo::class,
        QrCodeMapping::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accessPassDao(): AccessPassDao
    abstract fun quizResultDao(): QuizResultDao
    abstract fun trainingVideoDao(): TrainingVideoDao
    abstract fun qrCodeDao(): QrCodeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, keyManager: DatabaseKeyManager): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val factory = SupportFactory(keyManager.getKey())
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safeplant_database"
                )
                .openHelperFactory(factory)
                .addMigrations(
                    Migration1To2(),
                    Migration2To3(),
                    Migration3To4()
                )
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}