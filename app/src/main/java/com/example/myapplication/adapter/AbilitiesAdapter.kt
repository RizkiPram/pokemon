package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PokemonItemBinding
import com.example.myapplication.data.remote.response.Ability

class AbilitiesAdapter(private val list:ArrayList<Ability>):
    RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {
    inner class ViewHolder(private var binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root){
        fun itemBind(data: Ability){
            binding.apply {
                pokemonName.text=data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBind(list[position])
    }
}