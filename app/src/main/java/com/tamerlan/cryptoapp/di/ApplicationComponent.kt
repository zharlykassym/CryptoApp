package com.tamerlan.cryptoapp.di

import android.app.Application
import com.tamerlan.cryptoapp.presentation.CoinApp
import com.tamerlan.cryptoapp.presentation.CoinDetailFragment
import com.tamerlan.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {
    fun inject(application: CoinApp)

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}