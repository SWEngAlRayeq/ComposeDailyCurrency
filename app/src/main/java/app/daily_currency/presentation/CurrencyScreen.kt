package app.daily_currency.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.daily_currency.presentation.viewmodel.CurrencyViewModel

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = hiltViewModel()) {

    var rates = viewModel.rates
    var isLoading = viewModel.isLoading
    var error = viewModel.error


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1E3C72), Color(0xFF2A5298))
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🪙 Daily Currency Trivia",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3C72)
                )
                Spacer(modifier = Modifier.height(16.dp))

                when {
                    isLoading -> CircularProgressIndicator(color = Color(0xFF1E3C72))
                    error != null -> Text("❌ Error: $error")
                    else -> {
                        rates.forEach { rate ->
                            Text(
                                text = "1 USD = %.2f %s".format(rate.rate, rate.currency),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF2A5298),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.fetchRates() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("🔁 Refresh")
            }
        }
    }

}