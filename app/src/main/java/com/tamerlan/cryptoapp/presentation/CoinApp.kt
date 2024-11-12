package com.tamerlan.cryptoapp.presentation

import android.app.Application
import com.tamerlan.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}