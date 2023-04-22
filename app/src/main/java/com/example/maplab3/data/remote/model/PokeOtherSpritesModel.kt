package com.example.maplab3.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokeOtherSpritesModel(
    @SerializedName("official-artwork") val officialArtwork: PokeOfficialArtworkModel?
)
