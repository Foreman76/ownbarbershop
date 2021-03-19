package ru.int24.ownbarbershop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.int24.ownbarbershop.dao.UseDataBase
import ru.int24.ownbarbershop.repositories.BaseRepository
import ru.int24.ownbarbershop.repositories.DataBaseRepositoryImpl
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepository
import ru.int24.ownbarbershop.repositories.UsesCaseBaseRepositoryImpl
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class RoomModule {

    @Provides
    @Singleton
    fun provideDtaBase(context: Context): UseDataBase {
        return Room.databaseBuilder(context, UseDataBase::class.java, "database").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDataBaseRepository(useDataBase: UseDataBase): BaseRepository {
        return DataBaseRepositoryImpl(useDataBase)
    }

    @Provides
    fun provideUsesCaseBaseRepository(baseRepository: DataBaseRepositoryImpl): UsesCaseBaseRepository {
        return UsesCaseBaseRepositoryImpl(baseRepository)
    }
}