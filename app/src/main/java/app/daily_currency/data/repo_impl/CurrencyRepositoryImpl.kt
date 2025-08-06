package app.daily_currency.data.repo_impl

import app.daily_currency.data.api.CurrencyApi
import app.daily_currency.data.model.CurrencyRate
import app.daily_currency.domain.repo.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi
) : CurrencyRepository {
    override suspend fun getRandomRates(count: Int): List<CurrencyRate> {
        val allRates = api.getRates().rates
        return allRates.entries.shuffled().take(count).map {
            CurrencyRate(it.key, it.value)
        }
    }
}