package com.example.maplab3.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maplab3.domain.pokemon.PokemonUseCase
import com.example.maplab3.domain.pokemon.model.Pokemon
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val pokemonUseCase = PokemonUseCase

    val pokemonListLd = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val data = pokemonUseCase.getPokemonsData()
            pokemonListLd.postValue(data)
            isLoading.postValue(false)
        }
    }
}