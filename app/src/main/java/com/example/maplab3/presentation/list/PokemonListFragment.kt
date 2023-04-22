package com.example.maplab3.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.maplab3.R
import com.example.maplab3.databinding.FragmentPokemonListBinding


class PokemonListFragment : Fragment() {

    lateinit var binding: FragmentPokemonListBinding

    lateinit var viewModel: PokemonListViewModel

    lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        binding = FragmentPokemonListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        swipe.setOnRefreshListener {
            viewModel.getPokemonList()
        }



        viewModel.pokemonListLd.observe(viewLifecycleOwner) {

            adapter = PokemonAdapter { pokemon, position ->
                findNavController().navigate(
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        pokemon
                    )
                )
            }

            adapter.setNewData(it)

            binding.rv.adapter = adapter
        }



        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (!it) {
                swipe.isRefreshing = false
            }
        }
    }
}