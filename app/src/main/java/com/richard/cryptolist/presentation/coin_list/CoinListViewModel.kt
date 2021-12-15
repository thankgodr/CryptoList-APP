package com.richard.cryptolist.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.cryptolist.common.Resource
import com.richard.cryptolist.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
   private  val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

   private  val _statte = mutableStateOf(CoinListState())
   val state : State<CoinListState> = _statte

   init {
       getCoinns()
   }

   private fun getCoinns(){
      getCoinsUseCase().onEach { reseult ->
         when(reseult ){
            is Resource.Success -> {
               _statte.value = CoinListState(coins = reseult.data ?: emptyList())
            }
            is Resource.Error -> {
               _statte.value = CoinListState(error = reseult.message ?:
               "Unexpected error occured")
            }
            is Resource.Loading -> {
               _statte.value  = CoinListState(isLoading = true)
            }
         }
      }.launchIn(viewModelScope)
   }
}