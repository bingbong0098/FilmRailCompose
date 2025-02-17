package com.platform.mediacenter.di

import android.content.Context
import com.platform.mediacenter.data.datastore.DataStoreRepository
import com.platform.mediacenter.data.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) : DataStoreRepository = DataStoreRepositoryImpl(context = context)
}