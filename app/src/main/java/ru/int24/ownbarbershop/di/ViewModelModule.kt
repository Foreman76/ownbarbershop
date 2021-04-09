package ru.int24.ownbarbershop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.int24.ownbarbershop.fragments.viewmodels.*

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
    @IntoMap
    @ViewModelKey(VMDateTimeFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMDateTimeFragment(vmDateTimeFragment: VMDateTimeFragment): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMMainActivity::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMMainActivity(vmMainActivity: VMMainActivity): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMProfileFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMProfileFragment(vmProfileFragment: VMProfileFragment): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMAuthFragment::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMAuthFragment(vmAuthFragment: VMAuthFragment): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}