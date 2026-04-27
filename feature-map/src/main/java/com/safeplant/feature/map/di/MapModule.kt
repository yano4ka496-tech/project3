package com.safeplant.feature.map.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.feature.map.MapRepository
import javax.inject.Singleton

/**
 * Модуль Hilt для внедрения зависимостей в модуль карты
 */
@Module
@InstallIn(ViewModelComponent::class)
object MapModule {

    /**
     * Предоставление MapRepository
     */
    @Provides
    fun provideMapRepository(mapObjectDao: MapObjectDao): MapRepository {
        return MapRepository(mapObjectDao)
    }
}