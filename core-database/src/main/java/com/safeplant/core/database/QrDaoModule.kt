package com.safeplant.core.database

import com.safeplant.core.database.dao.QrCodeDao
import com.safeplant.core.database.dao.QrCodeDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class QrDaoModule {
    @Binds
    @Singleton
    abstract fun bindQrCodeDao(impl: QrCodeDaoImpl): QrCodeDao
}
