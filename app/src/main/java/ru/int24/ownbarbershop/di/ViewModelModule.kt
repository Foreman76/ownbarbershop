package ru.int24.ownbarbershop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.int24.ownbarbershop.fragments.viewmodels.VMDetailStaffFragment
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService
import ru.int24.ownbarbershop.fragments.viewmodels.VMStaffListFragment
import ru.int24.ownbarbershop.fragments.viewmodels.ViewModelOrderFragment

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(VMListService::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMListService(vmListService: VMListService): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelOrderFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindViewModelOrderFragment(viewModelOrderFragment: ViewModelOrderFragment): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMStaffListFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMStaffListFragment(vmStaffListFragment: VMStaffListFragment): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMDetailStaffFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMDetailStaffFragment(vmDetailStaffFragment: VMDetailStaffFragment): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}