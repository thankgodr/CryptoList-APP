package com.richard.cryptolist.presentation

sealed class Screen(val route : String){
        object CoinListScreen : Screen("coin_list_screen")
        object CoinDetailsScreen : Screen("coin_detail_screen")
}
