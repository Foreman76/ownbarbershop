package ru.int24.ownbarbershop.di

import dagger.Component
import ru.int24.ownbarbershop.MainActivity
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
    fun inject (mainActivity: MainActivity)
    fun inject (vmMainActivity: VMMainActivity)
    fun inject (vmAuthFragment: VMAuthFragment)
    fun inject (authFragment: AuthFragment)
    fun inject (vmProfileFragment: VMProfileFragment)
    fun inject (profileFragment: ProfileFragment)
    fun inject (cardsLoyaltyFragment: CardsLoyaltyFragment)
    fun inject (certificateFragment: CertificateFragment)
    fun inject (personFragment: PersonFragment)
    fun inject (vmCardsLoyaltyFragment: VMCardsLoyaltyFragment)
    fun inject (vmCertificateFragment: VMCertificateFragment)
    fun inject (vmPersonFragment: VMPersonFragment)
    fun inject (subscriptionFragment: SubscriptionFragment)
    fun inject (vmSubscriptionFragment: VMSubscriptionFragment)
    fun inject (createOrderFragment: CreateOrderFragment)
    fun inject (vmCreateOrderFragment: VMCreateOrderFragment)
    fun inject (recordsFragment: RecordsFragment)
    fun inject (vmRecordsFrsgment: VMRecordsFrsgment)
    fun inject (futureRecordsFragment: FutureRecordsFragment)
    fun inject (pastRecordsFragment: PastRecordsFragment)
    fun inject (vmFutureRecordsFragment: VMFutureRecordsFragment)
    fun inject (vmPastRecordsFragment: VMPastRecordsFragment)


}