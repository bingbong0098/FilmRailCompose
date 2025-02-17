package com.platform.mediacenter.di

import android.content.Context
import androidx.room.Room
import com.platform.mediacenter.data.db.DownloadDao
import com.platform.mediacenter.data.db.DownloadDatabase
import com.platform.mediacenter.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDownloadDao(
        database: DownloadDatabase
    ): DownloadDao = database.dao()


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DownloadDatabase::class.java,
        DATABASE_NAME
    ).build()
}