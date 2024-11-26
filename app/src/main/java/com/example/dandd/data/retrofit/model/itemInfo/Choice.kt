package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    @SerialName("choose")
    val choose: Int? = 0,
    @SerialName("desc")
    val desc: String? = "",
    @SerialName("from")
    val from: FromXX? = FromXX(),
    @SerialName("type")
    val type: String? = ""
)