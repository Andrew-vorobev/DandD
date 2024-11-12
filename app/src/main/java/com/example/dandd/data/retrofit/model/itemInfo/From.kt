package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class From(
    @SerialName("option_set_type")
    val optionSetType: String? = null,
    @SerialName("options")
    val options: List<Option?>? = null
)