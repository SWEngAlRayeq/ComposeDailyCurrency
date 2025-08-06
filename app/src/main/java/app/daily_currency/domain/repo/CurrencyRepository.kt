package app.daily_currency.domain.repo

import app.daily_currency.data.model.CurrencyRate

interface CurrencyRepository {
    suspend fun getRandomRates(count: Int = 3): List<CurrencyRate>
}