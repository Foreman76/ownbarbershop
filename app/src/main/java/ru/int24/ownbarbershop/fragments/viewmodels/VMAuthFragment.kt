package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.ViewModel
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import ru.int24.ownbarbershop.utilits.GetResource
import javax.inject.Inject

class VMAuthFragment @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                         private val usesCaseBaseRepositoryImpl: UsesCaseBaseRepositoryImpl,
                                         private val getResource: GetResource):ViewModel() {
}