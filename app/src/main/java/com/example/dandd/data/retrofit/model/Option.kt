package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @SerialName("item")
    val item: Item? = null,
    @SerialName("option_type")
    val optionType: String? = null
)