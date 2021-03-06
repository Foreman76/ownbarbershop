package ru.int24.ownbarbershop.fragments.viewmodels

import androidx.lifecycle.ViewModel
import ru.int24.ownbarbershop.repositories.NetworkRepositoryImpl
import ru.int24.ownbarbershop.utilits.ErrorType
import ru.int24.ownbarbershop.utilits.GetResource
import ru.int24.ownbarbershop.utilits.RemoteErrorEmitter
import javax.inject.Inject

class VMRecordsFrsgment @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl,
                                            getResource: GetResource) : ViewModel(), RemoteErrorEmitter {




    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }
}