package com.safeplant.feature.qr.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.safeplant.core.database.dao.MapObjectDao
import com.safeplant.core.security.QRValidator
import com.safeplant.feature.qr.QRScannerRepository
import javax.inject.Singleton

/**
 * Модуль Hilt для внедрения зависимостей в модуль QR-сканера
 */
@Module
@InstallIn(ViewModelComponent::class)
object QRModule {

    /**
     * Предоставление QRScannerRepository
     */
    @Provides
    fun provideQRScannerRepository(mapObjectDao: MapObjectDao): QRScannerRepository {
        return QRScannerRepository(mapObjectDao)
    }

    /**
     * Предоставление QRValidator
     */
    @Provides
    fun provideQRValidator(): QRValidator {
        return QRValidator()
    }
}