package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.PokemonAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.response.ResultsItem
import com.example.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }
    private fun setupViewModel(){
        viewModel.getPokemon()
        viewModel.pokemonList.observe(this@MainActivity){
            setupListPokemon(it)
        }
    }
    private fun setupListPokemon(data:List<ResultsItem>){
        val listPokemon = ArrayList<ResultsItem>()
        data.forEach { listPokemon.add(it) }
        binding.rvPokemon.apply {
            val layout=LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            val pokemonAdapter = PokemonAdapter(listPokemon)
            layoutManager=layout
            adapter=pokemonAdapter
            pokemonAdapter.setOnItemClickCallback(object : PokemonAdapter.OnItemClickCallback{
                override fun onItemClicked(data: ResultsItem) {
                    val intent = Intent(this@MainActivity,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_POKEMON,data.name)
                    startActivity(intent)
                }
            })
        }
    }
}