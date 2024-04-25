package com.tamerlan.cryptoapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso
import com.tamerlan.cryptoapp.R
import com.tamerlan.cryptoapp.databinding.ItemCoinInfoBinding
import com.tamerlan.cryptoapp.pojo.CoinPriceInfo

class CoinInfoAdapter(private val context: Context): Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    lateinit var bindind: ItemCoinInfoBinding

    var coinInfoList:List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
     val coin = coinInfoList[position]
        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = coin.price.toString()
            tvUpdates.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)
            itemView.setOnClickListener{
                onCoinClickListener?.onCoinClick(coin)
            }
        }

    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(binding: ItemCoinInfoBinding): RecyclerView.ViewHolder(binding.root) {
        val ivLogoCoin = binding.ivLogoCoin
        val tvPrice = binding.tvPrice
        val tvSymbols = binding.tvSymbols
        val tvUpdates = binding.tvUpdates
    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}