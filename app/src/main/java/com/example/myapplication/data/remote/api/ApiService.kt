package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.response.DetailResponse
import com.example.myapplication.data.remote.response.PokemonResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    fun getListPokemon(): Call<PokemonResponse>

    @GET("pokemon/{name}")
    fun getDetailPokemon(
        @Path("name") name: String
    ): Call<DetailResponse>
}