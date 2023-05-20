package com.blackstoneeit.ncgrtask.common.di

import android.content.Context
import com.blackstoneeit.ncgrtask.common.utils.IResourceProvider
import com.blackstoneeit.ncgrtask.common.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {

    @Singleton
    @Provides
    fun resourceProvider(
        @ApplicationContext context: Context,
    ): IResourceProvider = ResourceProvider(context)

}