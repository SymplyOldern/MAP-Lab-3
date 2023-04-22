package com.example.maplab3.data.remote.model

data class PokemonModel(
    val id: Int?,
    val name: String?,
    val base_experience: Int?,
    val height: Int?,
    val weight: Int?,
    val sprites: PokeSpritesModel?,
    val types: List<PokeTypeModel>?
)
