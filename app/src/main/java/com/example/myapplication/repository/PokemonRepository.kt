package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.myapplication.data.local.entity.PokemonEntity
import com.example.myapplication.data.local.room.PokemonDAO
import com.example.myapplication.data.remote.api.ApiService
import com.example.myapplication.data.remote.response.PokemonResponse
import com.example.myapplication.utils.AppExecutors
import com.example.myapplication.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository private constructor(
    private val apiService: ApiService,
    private val pokemonDAO: PokemonDAO,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<PokemonEntity>>>()
    fun getListPokemon(): LiveData<Result<List<PokemonEntity>>> {
        result.value = Result.Loading
        val client = apiService.getListPokemon()
        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemon = response.body()?.results
                    val pokemonList = ArrayList<PokemonEntity>()
                    appExecutors.diskIO.execute {
                        pokemon?.forEach { pokemon ->
                            val pokemonItem = PokemonEntity(
                                pokemon.name,
                                pokemon.url
                            )
                            pokemonList.add(pokemonItem)
                        }
                        pokemonDAO.insertPokemon(pokemonList)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })
        val localData = pokemonDAO.getPokemon()
        result.addSource(localData) { newData: List<PokemonEntity> ->
            result.value = Result.Success(newData)
        }
        return result
    }

    fun searchPokemon(query: String): LiveData<List<PokemonEntity>> {
        return pokemonDAO.search(query)
    }

    companion object {
        @Volatile
        private var instance: PokemonRepository? = null
        fun getInstance(
            apiService: ApiService,
            pokemonDAO: PokemonDAO,
            appExecutors: AppExecutors
        ): PokemonRepository =
            instance ?: synchronized(this) {
                instance ?: PokemonRepository(apiService, pokemonDAO, appExecutors)
            }.also { instance = it }
    }
}