package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SavingThrow(
    @SerialName("index")
    val index: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)