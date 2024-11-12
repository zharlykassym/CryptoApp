package com.tamerlan.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.tamerlan.cryptoapp.data.database.AppDatabase
import com.tamerlan.cryptoapp.data.database.CoinInfoDao
import com.tamerlan.cryptoapp.data.mapper.CoinMapper
import com.tamerlan.cryptoapp.data.workers.RefreshDataWorker
import com.tamerlan.cryptoapp.domain.CoinInfoEntity
import com.tamerlan.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper : CoinMapper
) : CoinRepository {



    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return coinInfoDao.getPriceList().map { mapper.mapListDbModelToListEntity(it) }
    }


    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map { mapper.mapDbModelToEntity(it) }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}