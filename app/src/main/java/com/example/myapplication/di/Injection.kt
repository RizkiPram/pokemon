package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.local.room.PokemonDatabases
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.repository.PokemonRepository

object Injection {
    fun provideRepository(context: Context): PokemonRepository {
        val apiService = ApiConfig.getApiService()
        val database = PokemonDatabases.getInstance(context)
        val dao = database.pokemonDao()
        val appExecutors = com.example.myapplication.utils.AppExecutors
        return PokemonRepository.getInstance(apiService, dao, appExecutors)
    }
}