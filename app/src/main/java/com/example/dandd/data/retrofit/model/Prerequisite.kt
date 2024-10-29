package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prerequisite(
    @SerialName("ability_score")
    val abilityScore: AbilityScore? = null,
    @SerialName("minimum_score")
    val minimumScore: Int? = null
)