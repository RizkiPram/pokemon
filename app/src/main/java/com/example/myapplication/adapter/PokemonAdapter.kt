package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PokemonItemBinding
import com.example.myapplication.response.ResultsItem

class PokemonAdapter(private val list:ArrayList<ResultsItem>):
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    inner class ViewHolder(private var binding:PokemonItemBinding):RecyclerView.ViewHolder(binding.root){
        fun itemBind(data: ResultsItem){
            binding.apply {
                root.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
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
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultsItem)
    }

}