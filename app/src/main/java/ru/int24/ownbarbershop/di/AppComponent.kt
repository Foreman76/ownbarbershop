package ru.int24.ownbarbershop.di

import dagger.Component
import ru.int24.ownbarbershop.fragments.ListServiceFragment
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import javax.inject.Singleton

@Component(modules = [AppModule::class,
                      NetworkModule::class,
                      RoomModule::class,
                      RemoteRepositoryModule::class,
                      ViewModelModule::class])
@Singleton
interface AppComponent {
    fun inject (viewModel: VMListService)
    fun inject (repositoryImpl: NetworkRepositoryImpl)
    fun inject (listServiceFragment: ListServiceFragment)
}