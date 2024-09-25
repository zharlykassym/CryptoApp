package com.tamerlan.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.tamerlan.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.tamerlan.cryptoapp.domain.CoinInfoEntity
import com.tamerlan.cryptoapp.presentation.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfoEntity) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }

        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.coinInfoList.observe(this, Observer {
            adapter.submitList(it)
        })


    }
}