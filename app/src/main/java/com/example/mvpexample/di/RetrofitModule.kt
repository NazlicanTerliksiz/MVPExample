package com.example.mvpexample.di

import com.example.mvpexample.data.constant.Constants.BASE_URL_RICKANDMORTY
import com.example.mvpexample.data.constant.Constants.BASE_URL_SIMPSON
import com.example.mvpexample.data.services.RickAndMortyApiService
import com.example.mvpexample.data.services.SimpsonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofitSimpson(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SIMPSON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSimpsonServices(retrofit: Retrofit): SimpsonApiService {
        return retrofit.create(SimpsonApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("rickAndMortyRetrofit")
    fun providesRetrofitRickAndMorty(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_RICKANDMORTY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyServices(@Named("rickAndMortyRetrofit") retrofit: Retrofit): RickAndMortyApiService {
        return retrofit.create(RickAndMortyApiService::class.java)
    }
}