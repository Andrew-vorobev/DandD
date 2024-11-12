package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FromX(
    @SerialName("option_set_type")
    val optionSetType: String? = "",
    @SerialName("options")
    val options: List<OptionX>? = listOf()
)