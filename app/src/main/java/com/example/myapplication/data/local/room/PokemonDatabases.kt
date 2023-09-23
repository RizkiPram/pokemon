package com.example.myapplication.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabases : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDAO

    companion object {
        @Volatile
        private var instance: PokemonDatabases? = null
        fun getInstance(context: Context): PokemonDatabases =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabases::class.java, "Pokemon.db"
                ).build()
            }
    }
}