package com.richard.cryptolist.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.cryptolist.common.Constant
import com.richard.cryptolist.common.Resource
import com.richard.cryptolist.domain.use_case.get_coin.GetCoinUseCase
import com.richard.cryptolist.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
   private  val getCoinUseCase: GetCoinUseCase,
   savedStateHandle: SavedStateHandle
) : ViewModel() {

   private  val _statte = mutableStateOf(CoinDetailState())
   val state : State<CoinDetailState> = _statte

   init {
      print("Viewmodel started")
       savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let{
          getCoinns(it)
       }
   }

   private fun getCoinns(coinID : String){
      getCoinUseCase(coinID).onEach { reseult ->
         when(reseult ){
            is Resource.Success -> {
               println(reseult.data)
               _statte.value = CoinDetailState(coins = reseult.data)
            }
            is Resource.Error -> {
               _statte.value = CoinDetailState(error = reseult.message ?:
               "Unexpected error occured")
            }
            is Resource.Loading -> {
               _statte.value  = CoinDetailState(isLoading = true)
            }
         }
      }.launchIn(viewModelScope)
   }
}