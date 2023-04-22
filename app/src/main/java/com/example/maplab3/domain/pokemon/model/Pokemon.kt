package com.example.maplab3.domain.pokemon.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int?,
    val name: String?,
    val base_experience: String?,
    val height: String?,
    val weight: String?,
    val artwork: String?,
    val types: String
) : Parcelable

