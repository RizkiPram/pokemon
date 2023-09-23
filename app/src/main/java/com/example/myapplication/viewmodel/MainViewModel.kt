package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.local.entity.PokemonEntity
import com.example.myapplication.repository.PokemonRepository

class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    fun getPokemon() = pokemonRepository.getListPokemon()
    fun searchPokemon(query: String): LiveData<List<PokemonEntity>> {
        return pokemonRepository.searchPokemon(query)
    }
}