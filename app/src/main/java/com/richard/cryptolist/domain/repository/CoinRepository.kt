package com.richard.cryptolist.domain.repository

import com.richard.cryptolist.data.remote.dto.CoinDetailDto
import com.richard.cryptolist.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinById(coinId : String) : CoinDetailDto
}