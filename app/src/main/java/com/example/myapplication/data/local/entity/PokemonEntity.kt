package com.example.myapplication.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
class PokemonEntity (
    @field:ColumnInfo(name="name")
    @PrimaryKey
    val name: String,

    @field:ColumnInfo(name="url")
    val url: String
)
