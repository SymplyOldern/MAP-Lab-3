package com.example.maplab3.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maplab3.databinding.PokemonCardBinding
import com.example.maplab3.domain.pokemon.model.Pokemon

class PokemonAdapter(
    val onClick: (model: Pokemon, position: Int) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private val list = ArrayList<Pokemon>()

    inner class PokemonViewHolder(val binding: PokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(pokemon: Pokemon, position: Int) {
            binding.tvName.text = pokemon.name

            if (pokemon.types.length > 37) {
                binding.tvTypes.text = pokemon.types.substring(0, 37) + "..."
            } else {
                binding.tvTypes.text = pokemon.types
            }

            Glide.with(binding.iv.context)
                .load(pokemon.artwork)
                .into(binding.iv)

            binding.btn.setOnClickListener {
                onClick(pokemon, position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = PokemonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = list[position]
        holder.bindView(pokemon, position)
    }

    fun setNewData(newList: List<Pokemon>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}