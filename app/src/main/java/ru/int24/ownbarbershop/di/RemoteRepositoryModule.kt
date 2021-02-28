package ru.int24.ownbarbershop.di

import dagger.Module
import dagger.Provides
import ru.int24.ownbarbershop.network.ApiYclients
import ru.int24.ownbarbershop.repositories.NetworkRepository
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl


@Module
class RemoteRepositoryModule {

    @Provides
    fun provideRemoteRepository(api: ApiYclients): NetworkRepository{
        return NetworkRepositoryImpl(api)

    }

}