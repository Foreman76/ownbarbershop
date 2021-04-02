package ru.int24.ownbarbershop.di

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import ru.int24.ownbarbershop.fragments.adapters.ServiceAdapter
import ru.int24.ownbarbershop.fragments.adapters.ServiceChoseAdapter
import ru.int24.ownbarbershop.fragments.adapters.StaffAdapter
import javax.inject.Singleton

@Module
class AdapterModule {

    @Provides
    @Singleton
    fun provideStaffAdapter(picasso:Picasso): StaffAdapter = StaffAdapter((picasso))

    @Provides
    @Singleton
    fun provideServiceAdapter(picasso: Picasso): ServiceAdapter = ServiceAdapter(picasso)

    @Provides
    @Singleton
    fun provideServiceChoseAdapter(): ServiceChoseAdapter = ServiceChoseAdapter()
}