package com.safeplant.core.database

import com.safeplant.core.database.dao.AccessPassDao
import com.safeplant.core.database.dao.AccessPassDaoInMemory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindAccessPassDao(impl: AccessPassDaoInMemory): AccessPassDao
}
