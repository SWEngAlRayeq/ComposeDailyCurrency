package app.daily_currency.domain.usecase

import app.daily_currency.data.model.CurrencyRate
import app.daily_currency.domain.repo.CurrencyRepository
import javax.inject.Inject

class GetRandomCurrencyRatesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(): List<CurrencyRate> {
        return repository.getRandomRates()
    }
}