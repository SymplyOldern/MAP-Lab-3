package com.example.maplab3.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokeSpritesModel(
    @SerializedName("other") val otherSprites: PokeOtherSpritesModel?
)
