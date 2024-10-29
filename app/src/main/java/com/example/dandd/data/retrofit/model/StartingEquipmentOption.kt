package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StartingEquipmentOption(
    @SerialName("choose")
    val choose: Int? = 0,
    @SerialName("desc")
    val desc: String? = "",
    @SerialName("from")
    val from: FromX? = FromX(),
    @SerialName("type")
    val type: String? = ""
)