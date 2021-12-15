package com.richard.cryptolist.data.repository

import com.richard.cryptolist.data.remote.CoinParikaAPi
import com.richard.cryptolist.data.remote.dto.CoinDetailDto
import com.richard.cryptolist.data.remote.dto.CoinDto
import com.richard.cryptolist.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinParikaAPi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}