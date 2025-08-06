package app.daily_currency.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.daily_currency.data.model.CurrencyRate
import app.daily_currency.domain.usecase.GetRandomCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getRandomCurrencyRatesUseCase: GetRandomCurrencyRatesUseCase
) : ViewModel() {

    var rates by mutableStateOf<List<CurrencyRate>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
    var error by mutableStateOf<String?>(null)

    init {
        fetchRates()
    }

    fun fetchRates() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                rates = getRandomCurrencyRatesUseCase()
            } catch (e: Exception) {
                error = e.localizedMessage
            }
            isLoading = false
        }
    }

}