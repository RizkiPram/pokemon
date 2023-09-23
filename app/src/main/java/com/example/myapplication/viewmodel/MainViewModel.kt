package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.data.remote.response.PokemonResponse
import com.example.myapplication.data.remote.response.ResultsItem
import com.example.myapplication.repository.PokemonRepository
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val pokemonRepository: PokemonRepository):ViewModel() {
    fun getPokemon() = pokemonRepository.getListPokemon()
//    private val _pokemonList = MutableLiveData<List<ResultsItem>>()
//    val pokemonList: LiveData<List<ResultsItem>> = _pokemonList
//
//    fun getPokemon(){
//
//        val client= ApiConfig.getApiService().getListPokemon()
//        client.enqueue(object : retrofit2.Callback<PokemonResponse>{
//            override fun onResponse(
//                call: Call<PokemonResponse>,
//                response: Response<PokemonResponse>
//            ) {
//                val responseBody=response.body()
//                if (response.isSuccessful){
//                    if (responseBody != null){
//                        _pokemonList.value=responseBody.results
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
//                Log.e("MainViewModel", "onFailure: ${t.message}")
//            }
//        })
//    }

}