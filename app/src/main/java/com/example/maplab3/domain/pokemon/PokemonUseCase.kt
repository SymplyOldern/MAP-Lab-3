package com.example.maplab3.domain.pokemon

import com.example.maplab3.data.PokemonRepository
import com.example.maplab3.data.remote.model.PokemonModel
import com.example.maplab3.domain.pokemon.model.Pokemon

object PokemonUseCase {

    private val repo = PokemonRepository

    suspend fun getPokemonsData(): List<Pokemon> {
        val dataFromRepo = repo.getPokemonsData()

        val data = ArrayList<Pokemon>()
        dataFromRepo.forEach {
            data.add(mapPokemonToDomain(it))
        }
        return data
    }

    fun mapPokemonToDomain(data: PokemonModel): Pokemon {
        var typesStr: String = "Types: "
        data.types?.forEach {
            typesStr += it.type?.typeName + ", "
        }
        typesStr = typesStr.substring(0, typesStr.length - 2)

        return Pokemon(
            data.id,
            data.name?.substring(0, 1)?.uppercase() + data.name?.substring(1, data.name.length),
            "Base experience: " + data.base_experience.toString(),
            "Height: " + data.height.toString(),
            "Weight: " + data.weight.toString(),
            data.sprites?.otherSprites?.officialArtwork?.front_default,
            typesStr
        )
    }
}