package com.tamerlan.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CoinInfoDao {
    @androidx.room.Query("SELECT * FROM full_price_list ORDER BY price DESC")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    @androidx.room.Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}