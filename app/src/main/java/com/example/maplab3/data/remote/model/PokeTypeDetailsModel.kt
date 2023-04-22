package com.example.maplab3.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokeTypeDetailsModel(
    @SerializedName("name") val typeName: String?
)
