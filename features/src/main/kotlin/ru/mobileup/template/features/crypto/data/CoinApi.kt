package ru.mobileup.template.features.crypto.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mobileup.template.features.crypto.data.dto.CoinResponse
import ru.mobileup.template.features.crypto.data.dto.DetailedCoinResponse

interface CoinApi {

    @GET("https://api.coingecko.com/api/v3/coins/markets")
    suspend fun getCoinsByCurrency(
        @Query("vs_currency") currency: String
    ): List<CoinResponse>

    @GET("https://api.coingecko.com/api/v3/coins/{coinId}")
    suspend fun getCoinDetails(
        @Path("coinId") coinId: String
    ): DetailedCoinResponse
}