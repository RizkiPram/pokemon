package com.example.myapplication.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.entity.PokemonEntity

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon ORDER BY name DESC")
    fun getPokemon(): LiveData<List<PokemonEntity>>
    @Query("SELECT * FROM pokemon WHERE name LIKE :search")
    fun search(search:String):List<PokemonEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPokemon(pokemon: List<PokemonEntity>)
}