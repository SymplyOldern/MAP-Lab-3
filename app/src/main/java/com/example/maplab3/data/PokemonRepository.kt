package com.example.maplab3.data

import com.example.maplab3.data.remote.ApiFactory
import com.example.maplab3.data.remote.Result
import com.example.maplab3.data.remote.model.PokeNamesListModel
import com.example.maplab3.data.remote.model.PokemonModel
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object PokemonRepository {

    private val api = ApiFactory.pokemonApi

    suspend fun getPokeList(): PokeNamesListModel? {
        val resp = safetyCall { api.getPokeList() }

        return when (resp) {
            is Result.Success -> resp.data
            else -> null
        }
    }

    suspend fun getPokemon(pokemonName: String): PokemonModel? {
        val resp = safetyCall { api.getPokemonData(pokemonName) }

        return when (resp) {
            is Result.Success -> resp.data
            else -> null
        }
    }

    suspend fun getPokemonsData(): List<PokemonModel> {
        val pokeNamesListModel = getPokeList()
        val pokemonsData = ArrayList<PokemonModel>()

        if (pokeNamesListModel != null) {
            pokeNamesListModel.pokeNamesList?.forEach {
                val pokeData = getPokemon(it.name)
                pokeData?.let { pokemonsData.add(pokeData) }
            }
        }
        return pokemonsData
    }

    private suspend fun <T> safetyCall(call: suspend () -> Response<T>): Result<T> {
        val response: Result<T>
        try {
            val result = call.invoke()
            response = if (result.isSuccessful) {
                Result.Success(result.body()!!)
            } else {
                Result.Error(result.code(), result.toString())
            }
            return response
        } catch (e: Exception) {
            return when (e) {
                is SocketTimeoutException -> Result.ErrorTimeOut
                is UnknownHostException -> Result.ErrorUnknown

                else -> Result.ErrorUnknown
            }
        }
    }
}