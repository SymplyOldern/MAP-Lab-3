package com.example.maplab3.data.remote

import com.example.maplab3.data.remote.model.PokeNamesListModel
import com.example.maplab3.data.remote.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiInterface {

    @GET("pokemon")
    suspend fun getPokeList(): Response<PokeNamesListModel>

    @GET("pokemon/{pokemon_name}")
    suspend fun getPokemonData(
        @Path("pokemon_name") pokemonName: String
    ): Response<PokemonModel>
}