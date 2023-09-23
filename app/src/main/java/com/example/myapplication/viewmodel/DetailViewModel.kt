package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ApiConfig
import com.example.myapplication.response.AbilitiesItem
import com.example.myapplication.response.DetailResponse
import com.example.myapplication.response.ResultsItem
import com.example.myapplication.response.Sprites
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val _detailPokemonAbilities = MutableLiveData<List<AbilitiesItem>>()
    val detailPokemonAbilities: LiveData<List<AbilitiesItem>> = _detailPokemonAbilities
    private val _detailPokemonImage = MutableLiveData<Sprites>()
    val detailPokemonImages: LiveData<Sprites> = _detailPokemonImage
    fun getDetailPokemon(name:String){
        val client = ApiConfig.getApiService().getDetailPokemon(name)
        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val responseBody=response.body()
                if (response.isSuccessful){
                    if (responseBody != null){
                        _detailPokemonAbilities.value=responseBody.abilities
                        _detailPokemonImage.value=responseBody.sprites
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}