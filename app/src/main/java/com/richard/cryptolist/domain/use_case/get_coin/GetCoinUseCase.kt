package com.richard.cryptolist.domain.use_case.get_coin

import com.richard.cryptolist.common.Resource
import com.richard.cryptolist.data.remote.dto.toCoin
import com.richard.cryptolist.data.remote.dto.toCoinDeatail
import com.richard.cryptolist.domain.models.Coin
import com.richard.cryptolist.domain.models.CoinDetail
import com.richard.cryptolist.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId : String) : Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading<CoinDetail>())
            val coins = repository.getCoinById(coinId = coinId)
            emit(Resource.Success<CoinDetail>(coins.toCoinDeatail()))
        }catch (e : HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unknown error occured"))
        }catch (e : IOException){
            emit(Resource.Error<CoinDetail>("Could not reach server, Check you internet connection"))
        }
    }
}