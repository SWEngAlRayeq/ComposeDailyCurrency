package app.daily_currency.data.api

import app.daily_currency.data.model.CurrencyResponseDto
import retrofit2.http.GET

interface CurrencyApi {

    @GET("latest/USD")
    suspend fun getRates(): CurrencyResponseDto


}