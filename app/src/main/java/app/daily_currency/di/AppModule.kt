package app.daily_currency.di

import app.daily_currency.data.api.CurrencyApi
import app.daily_currency.data.repo_impl.CurrencyRepositoryImpl
import app.daily_currency.domain.repo.CurrencyRepository
import app.daily_currency.domain.usecase.GetRandomCurrencyRatesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    fun provideCurrencyApi(client: OkHttpClient): CurrencyApi =
        Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)

    @Provides
    fun provideCurrencyRepository(api: CurrencyApi): CurrencyRepository =
        CurrencyRepositoryImpl(api)

    @Provides
    fun provideCurrencyUseCase(repo: CurrencyRepository): GetRandomCurrencyRatesUseCase =
        GetRandomCurrencyRatesUseCase(repo)
    

}