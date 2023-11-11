package com.jp.mercadolibrechallenge.di

import android.content.Context
import com.jp.mercadolibrechallenge.data.datastore.DataStoreRepository
import com.jp.mercadolibrechallenge.data.datastore.DataStoreRepositoryHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryHandler(app)
}