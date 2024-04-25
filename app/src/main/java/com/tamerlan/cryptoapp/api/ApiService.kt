package com.tamerlan.cryptoapp.api

import com.tamerlan.cryptoapp.pojo.CoinInfoListOfData
import com.tamerlan.cryptoapp.pojo.CoinPriceInfoRawData

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "21c4f04c1e41d50e8562b6a5ed384e0b0d7d892600dfed4da71e87391b332424",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("data/pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "21c4f04c1e41d50e8562b6a5ed384e0b0d7d892600dfed4da71e87391b332424",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String = "BTC",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_API_KEY = "apikey"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"

        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}