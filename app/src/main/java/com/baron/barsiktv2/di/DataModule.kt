package com.baron.barsiktv2.di

import com.baron.data.network.BarsikRetrofit
import com.baron.data.network.BarsikRetrofit.Companion.BASE_URL
import com.baron.data.network.BarsikRetrofit.Companion.TIMEOUT
import com.baron.data.repository.BarsikRetrofitRepositoryImpl
import com.baron.domain.repository.BarsikRetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideBarsikRetrofit(retrofit: Retrofit): BarsikRetrofit =
        retrofit.create(BarsikRetrofit::class.java)

    @Provides
    @Singleton
    fun provideBarsikRetrofitRepository(retrofit: BarsikRetrofit): BarsikRetrofitRepository =
        BarsikRetrofitRepositoryImpl(retrofit)

}