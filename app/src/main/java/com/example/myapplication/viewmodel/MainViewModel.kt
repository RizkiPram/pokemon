package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ApiConfig
import com.example.myapplication.response.PokemonResponse
import com.example.myapplication.response.ResultsItem
import retrofit2.Call
import retrofit2.Response

class MainViewModel:ViewModel() {
    private val _pokemonList = MutableLiveData<List<ResultsItem>>()
    val pokemonList: LiveData<List<ResultsItem>> = _pokemonList

    fun getPokemon(){

        val client=ApiConfig.getApiService().getListPokemon()
        client.enqueue(object : retrofit2.Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                val responseBody=response.body()
                if (response.isSuccessful){
                    if (responseBody != null){
                        _pokemonList.value=responseBody.results
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message}")
            }
        })
    }

}