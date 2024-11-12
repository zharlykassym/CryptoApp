package com.tamerlan.cryptoapp.di

import android.app.Application
import com.tamerlan.cryptoapp.data.database.AppDatabase
import com.tamerlan.cryptoapp.data.database.CoinInfoDao
import com.tamerlan.cryptoapp.data.repository.CoinRepositoryImpl
import com.tamerlan.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}