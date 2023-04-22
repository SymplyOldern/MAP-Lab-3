package com.example.maplab3.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.maplab3.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsFragment : Fragment() {

    lateinit var binding: FragmentPokemonDetailsBinding

    lateinit var viewModel: PokemonDetailsViewModel

    val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PokemonDetailsViewModel::class.java)
        binding = FragmentPokemonDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.pokemonDetails.let {
            binding.tvName.text = args.pokemonDetails.name
            binding.tvTypes.text = args.pokemonDetails.types
            binding.tvHeight.text = args.pokemonDetails.height
            binding.tvWeight.text = args.pokemonDetails.weight
            binding.tvBaseExp.text = args.pokemonDetails.base_experience

            Glide.with(binding.iv.context)
                .load(args.pokemonDetails.artwork)
                .into(binding.iv)

            binding.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}