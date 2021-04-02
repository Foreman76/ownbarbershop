package ru.int24.ownbarbershop.di

import dagger.Component
import ru.int24.ownbarbershop.fragments.*
import ru.int24.ownbarbershop.fragments.adapters.ServiceAdapter
import ru.int24.ownbarbershop.fragments.adapters.StaffAdapter
import ru.int24.ownbarbershop.fragments.viewmodels.*
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.GetResource
import javax.inject.Singleton

@Component(modules = [AppModule::class,
                      NetworkModule::class,
                      RoomModule::class,
                      RemoteRepositoryModule::class,
                      ViewModelModule::class,
                      AdapterModule::class])
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
    fun inject (staffAdapter: StaffAdapter)
    fun inject (serviceAdapter: ServiceAdapter)
    fun inject (vmDateTimeFragment: VMDateTimeFragment)
    fun inject (dateTimeFragment: DateTimeFragment)

}