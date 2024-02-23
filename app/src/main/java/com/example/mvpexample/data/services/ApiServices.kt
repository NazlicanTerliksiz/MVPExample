package com.example.mvpexample.data.services

import com.example.mvpexample.data.model.RickAndMortyModel
import com.example.mvpexample.data.model.SimpsonModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("quotes?count=20")
    suspend fun getSimpsonCharacters(): Response<MutableList<SimpsonModel>>

    @GET("character/?page=19")
    fun getRickAndMortyCharacters(): Call<List<RickAndMortyModel>>
}