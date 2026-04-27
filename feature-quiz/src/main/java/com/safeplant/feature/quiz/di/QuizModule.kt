package com.safeplant.feature.quiz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.feature.quiz.QuizRepository
import javax.inject.Singleton

/**
 * Модуль Hilt для внедрения зависимостей в модуль викторины
 */
@Module
@InstallIn(ViewModelComponent::class)
object QuizModule {

    /**
     * Предоставление QuizRepository
     */
    @Provides
    fun provideQuizRepository(quizResultDao: QuizResultDao): QuizRepository {
        return QuizRepository(quizResultDao)
    }
}