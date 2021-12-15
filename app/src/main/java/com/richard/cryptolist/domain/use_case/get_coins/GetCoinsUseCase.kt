package com.richard.cryptolist.domain.use_case.get_coins

import com.richard.cryptolist.common.Resource
import com.richard.cryptolist.data.remote.dto.toCoin
import com.richard.cryptolist.domain.models.Coin
import com.richard.cryptolist.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow{
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins()
            emit(Resource.Success<List<Coin>>(coins.map{it.toCoin()}))
        }catch (e : HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unknown error occured"))
        }catch (e : IOException){
            emit(Resource.Error<List<Coin>>("Could not reach server, Check you internet connection"))
        }
    }
}