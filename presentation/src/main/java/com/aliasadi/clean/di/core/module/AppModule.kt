package com.aliasadi.clean.di.core.module

import android.content.Context
import com.aliasadi.clean.util.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ali Asadi on 15/05/2020
 **/

@Module
class AppModule constructor(context: Context) {

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    fun provideDiskExecutor(): com.aliasadi.data.util.DiskExecutor {
        return com.aliasadi.data.util.DiskExecutor()
    }

    @Provides
    fun provideDispatchersProvider(): com.aliasadi.data.util.DispatchersProvider {
        return com.aliasadi.data.util.DispatchersProviderImpl
    }

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }
}