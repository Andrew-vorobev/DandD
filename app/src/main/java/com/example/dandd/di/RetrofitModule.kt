package com.example.dandd.di

import com.example.dandd.data.retrofit.api.ItemApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory<Retrofit> { provideRetrofit() }
    single<ItemApi> { provideNetworkApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

}

private const val BASE_URL = "https://www.dnd5eapi.co"

fun provideNetworkApi(retrofit: Retrofit): ItemApi =
    retrofit.create(ItemApi::class.java)