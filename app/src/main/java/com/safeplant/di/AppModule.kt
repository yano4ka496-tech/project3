package com.safeplant.di

import android.content.Context
import androidx.room.Room
import com.safeplant.core.database.AppDatabase
import com.safeplant.core.database.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления зависимостей уровня приложения
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Предоставление контекста приложения
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    /**
     * Предоставление экземпляра базы данных
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        databaseModule: DatabaseModule
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "safeplant_db"
        )
        .addCallback(databaseModule.provideDatabaseCallback())
        .addMigrations(databaseModule.provideMigrations())
        .build()
    }
}