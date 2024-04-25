package com.tamerlan.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tamerlan.cryptoapp.adapters.CoinInfoAdapter
import com.tamerlan.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.tamerlan.cryptoapp.pojo.CoinPriceInfo


class CoinPriceList : AppCompatActivity() {
    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceList,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }

        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })


    }
}