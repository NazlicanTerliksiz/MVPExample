package com.example.mvpexample.di

import com.example.mvpexample.data.constant.Constants.BASE_URL_RICKANDMORTY
import com.example.mvpexample.data.constant.Constants.BASE_URL_SIMPSON
import com.example.mvpexample.data.services.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SIMPSON)
            .baseUrl(BASE_URL_RICKANDMORTY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSimpsonServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}