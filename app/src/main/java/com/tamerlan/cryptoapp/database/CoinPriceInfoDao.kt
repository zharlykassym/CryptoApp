package com.tamerlan.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tamerlan.cryptoapp.pojo.CoinPriceInfo
import retrofit2.http.Query

@Dao
interface CoinPriceInfoDao {
    @androidx.room.Query("SELECT * FROM full_price_list ORDER BY price DESC")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @androidx.room.Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}