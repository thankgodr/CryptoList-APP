package com.richard.cryptolist.domain.models

import com.richard.cryptolist.data.remote.dto.TeamMember

data class CoinDetail(
     val coinId :String,
     val name : String,
     val description : String,
     val symbol : String,
     val rank : Int,
     val isAvtive : Boolean,
     val tags : List<String>,
     val team : List<TeamMember>
)
