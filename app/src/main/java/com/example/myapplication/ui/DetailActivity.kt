package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private var pokemonName:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonName= intent.getStringExtra(EXTRA_POKEMON).toString()
        binding.tvPokemonName.text=pokemonName
        setupViewModel()
    }
    private fun setupViewModel(){
        viewModel.getDetailPokemon(pokemonName)
        viewModel.detailPokemonImages.observe(this@DetailActivity){
            Glide.with(this)
                .load(it.frontDefault)
                .into(binding.imgPokemon)
        }
    }
    companion object{
        const val EXTRA_POKEMON = "extra_pokemon"
    }
}