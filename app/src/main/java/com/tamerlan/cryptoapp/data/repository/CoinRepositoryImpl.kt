package com.tamerlan.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.tamerlan.cryptoapp.data.database.AppDatabase
import com.tamerlan.cryptoapp.data.mapper.CoinMapper
import com.tamerlan.cryptoapp.data.network.ApiFactory
import com.tamerlan.cryptoapp.domain.CoinInfoEntity
import com.tamerlan.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService
    private val database = AppDatabase.getInstance(application)
    private val coinInfoDao = database.coinPriceInfoDao()

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return coinInfoDao.getPriceList().map { mapper.mapListDbModelToListEntity(it) }
    }


    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (_: Exception) {
            }
            delay(10_000)
        }

    }
}