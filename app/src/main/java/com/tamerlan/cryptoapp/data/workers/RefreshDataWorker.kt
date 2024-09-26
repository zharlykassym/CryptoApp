package com.tamerlan.cryptoapp.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tamerlan.cryptoapp.data.database.AppDatabase
import com.tamerlan.cryptoapp.data.mapper.CoinMapper
import com.tamerlan.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService
    private val database = AppDatabase.getInstance(context)
    private val coinInfoDao = database.coinPriceInfoDao()

    override suspend fun doWork(): Result {
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

    companion object{
        const val NAME = "RefreshDataWorker"

        fun makeRequest():OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}