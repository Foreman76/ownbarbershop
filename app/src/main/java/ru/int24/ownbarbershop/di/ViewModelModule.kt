package ru.int24.ownbarbershop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.int24.ownbarbershop.fragments.viewmodels.VMListService

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(VMListService::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindVMListService(vmListService: VMListService): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}