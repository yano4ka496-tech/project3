package com.safeplant.feature.training.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.feature.training.TrainingRepository
import javax.inject.Singleton

/**
 * Модуль Hilt для внедрения зависимостей в модуль обучения
 */
@Module
@InstallIn(ViewModelComponent::class)
object TrainingModule {

    /**
     * Предоставление TrainingRepository
     */
    @Provides
    fun provideTrainingRepository(trainingVideoDao: TrainingVideoDao): TrainingRepository {
        return TrainingRepository(trainingVideoDao)
    }
}