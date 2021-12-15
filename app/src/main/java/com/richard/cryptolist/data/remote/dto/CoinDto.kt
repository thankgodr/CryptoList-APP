package com.richard.cryptolist.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.richard.cryptolist.domain.models.Coin

data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)

fun CoinDto.toCoin() : Coin{
    return Coin(
        isActive = isActive,
        id = id,
        name = name,
        rank = rank,
        symbol = symbol
    )
}