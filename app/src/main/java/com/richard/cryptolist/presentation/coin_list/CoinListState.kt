package com.richard.cryptolist.presentation.coin_list

import com.richard.cryptolist.domain.models.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error : String = ""
)
