package ru.int24.ownbarbershop.di

import dagger.Component
import ru.int24.ownbarbershop.fragments.DetailStaffFragment
import ru.int24.ownbarbershop.fragments.ListServiceFragment
import ru.int24.ownbarbershop.fragments.OrderFragment
import ru.int24.ownbarbershop.fragments.StaffListFragment
import ru.int24.ownbarbershop.fragments.viewmodels.VMDetailStaffFragment
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService
import ru.int24.ownbarbershop.fragments.viewmodels.VMStaffListFragment
import ru.int24.ownbarbershop.fragments.viewmodels.ViewModelOrderFragment
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.GetResource
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
    fun inject (usecaseImpl: UsesCaseBaseRepositoryImpl)
    fun inject (listServiceFragment: ListServiceFragment)
    fun inject (orderFragment: OrderFragment)
    fun inject (viewModelOrderFragment: ViewModelOrderFragment)
    fun inject (getResource: GetResource)
    fun inject (vmStaffListFragment: VMStaffListFragment)
    fun inject (staffListFragment: StaffListFragment)
    fun inject (detailStaffFragment: DetailStaffFragment)
    fun inject (vmDetailStaffFragment: VMDetailStaffFragment)
}