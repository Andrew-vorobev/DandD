package com.example.dandd.data.retrofit.model.classes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Results(
    @SerialName("index")
    val index: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)