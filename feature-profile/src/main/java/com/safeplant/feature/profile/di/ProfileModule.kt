package com.safeplant.feature.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.feature.profile.ProfileRepository
import javax.inject.Singleton

/**
 * Модуль Hilt для внедрения зависимостей в модуль профиля
 */
@Module
@InstallIn(ViewModelComponent::class)
object ProfileModule {

    /**
     * Предоставление ProfileRepository
     */
    @Provides
    fun provideProfileRepository(accessPassDao: AccessPassDao): ProfileRepository {
        return ProfileRepository(accessPassDao)
    }
}