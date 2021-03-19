package ru.int24.ownbarbershop.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.int24.ownbarbershop.utilits.GetResource
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext():Context{
        return this.context
    }

    @Provides
    @Singleton
    fun provideGetResource(context: Context):GetResource{
        return GetResource(context)
    }

}