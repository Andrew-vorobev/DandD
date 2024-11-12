package com.example.dandd.data.retrofit.model.classes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassesNetwork(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("results")
    val results: List<Results?>? = null
)