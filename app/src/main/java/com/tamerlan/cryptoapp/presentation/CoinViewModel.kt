package com.tamerlan.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import com.tamerlan.cryptoapp.domain.GetCoinInfoListUseCase
import com.tamerlan.cryptoapp.domain.GetCoinInfoUseCase
import com.tamerlan.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel(){



    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}