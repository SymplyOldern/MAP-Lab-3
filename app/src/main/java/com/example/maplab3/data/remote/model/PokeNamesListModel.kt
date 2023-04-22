package com.example.maplab3.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokeNamesListModel(
    @SerializedName("results") val pokeNamesList: List<PokeNameModel>?
)
