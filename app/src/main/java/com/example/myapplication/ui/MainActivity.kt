package com.example.myapplication.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.PokemonAdapter
import com.example.myapplication.data.local.entity.PokemonEntity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.Result
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: MainViewModel by viewModels {
            factory
        }
        viewModel.getPokemon().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val pokemonData = result.data
                        setupListPokemon(pokemonData)
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val search = binding.searchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.searchPokemon(p0).observe(this@MainActivity) {
                        setupListPokemon(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun setupListPokemon(data: List<PokemonEntity>) {
        val listPokemon = ArrayList<PokemonEntity>()
        data.forEach { listPokemon.add(it) }
        binding.rvPokemon.apply {
            val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val pokemonAdapter = PokemonAdapter(listPokemon)
            layoutManager = layout
            adapter = pokemonAdapter
            pokemonAdapter.setOnItemClickCallback(object : PokemonAdapter.OnItemClickCallback {
                override fun onItemClicked(data: PokemonEntity) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_POKEMON, data.name)
                    startActivity(intent)
                }
            })
        }
    }
}