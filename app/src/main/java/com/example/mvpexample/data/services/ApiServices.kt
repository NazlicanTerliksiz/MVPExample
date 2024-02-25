package com.example.mvpexample.data.services

import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.data.model.SimpsonModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface SimpsonApiService {
    @GET("quotes?count=20")
    suspend fun getSimpsonCharacters(): Response<MutableList<SimpsonModel>>
}

interface RickAndMortyApiService {
    @GET("character/?page=19")
    fun getRickAndMortyCharacters(): Call<RickAndMortyModel>
}
