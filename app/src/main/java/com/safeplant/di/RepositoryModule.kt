package com.safeplant.di

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.database.dao.QuizResultDao
import com.safeplant.core.database.dao.TrainingVideoDao
import com.safeplant.feature.quiz.QuizRepository
import com.safeplant.feature.map.MapRepository
import com.safeplant.feature.training.TrainingRepository
import com.safeplant.feature.qr.QRScannerRepository
import com.safeplant.feature.profile.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления репозиториев
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Предоставление репозитория викторины
     */
    @Provides
    @Singleton
    fun provideQuizRepository(
        quizResultDao: QuizResultDao
    ): QuizRepository = QuizRepository(quizResultDao)

    /**
     * Предоставление репозитория карты
     */
    @Provides
    @Singleton
    fun provideMapRepository(
        mapObjectDao: MapObjectDao
    ): MapRepository = MapRepository(mapObjectDao)

    /**
     * Предоставление репозитория обучения
     */
    @Provides
    @Singleton
    fun provideTrainingRepository(
        trainingVideoDao: TrainingVideoDao
    ): TrainingRepository = TrainingRepository(trainingVideoDao)

    /**
     * Предоставление репозитория QR-сканера
     */
    @Provides
    @Singleton
    fun provideQRScannerRepository(
        accessPassDao: AccessPassDao
    ): QRScannerRepository = QRScannerRepository(accessPassDao)

    /**
     * Предоставление репозитория профиля
     */
    @Provides
    @Singleton
    fun provideProfileRepository(
        accessPassDao: AccessPassDao
    ): ProfileRepository = ProfileRepository(accessPassDao)
}