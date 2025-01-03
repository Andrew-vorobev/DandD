package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultiClassing(
    @SerialName("prerequisites")
    val prerequisites: List<Prerequisite>? = listOf(),
    @SerialName("proficiencies")
    val proficiencies: List<ProficiencyX>? = listOf()
)