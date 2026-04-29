package com.safeplant.core.database

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.core.database.dao.QrCodeDao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.safeplant.core.database.entity.AccessPass
import com.safeplant.core.database.entity.QrCodeMapping
import com.safeplant.core.database.entity.QuizResult
import com.safeplant.core.database.entity.TrainingVideo

@Database(
    entities = [AccessPass::class, QuizResult::class, TrainingVideo::class, QrCodeMapping::class],
    version = 1,
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

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "safeplant_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
