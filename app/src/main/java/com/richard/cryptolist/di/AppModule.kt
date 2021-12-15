package com.richard.cryptolist.di

import com.richard.cryptolist.common.Constant
import com.richard.cryptolist.data.remote.CoinParikaAPi
import com.richard.cryptolist.data.repository.CoinRepositoryImpl
import com.richard.cryptolist.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaAPi() : CoinParikaAPi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinParikaAPi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinPapricaRepository(api : CoinParikaAPi): CoinRepository {
            return CoinRepositoryImpl(api)
    }
}