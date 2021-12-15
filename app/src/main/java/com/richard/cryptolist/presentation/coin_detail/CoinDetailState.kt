package com.richard.cryptolist.presentation.coin_detail

import com.richard.cryptolist.domain.models.Coin
import com.richard.cryptolist.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coins : CoinDetail? = null,
    val error : String = ""
)
