package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProficiencyChoice(
    @SerialName("choose")
    val choose: Int? = null,
    @SerialName("desc")
    val desc: String? = null,
    @SerialName("from")
    val from: From? = null,
    @SerialName("type")
    val type: String? = null
)