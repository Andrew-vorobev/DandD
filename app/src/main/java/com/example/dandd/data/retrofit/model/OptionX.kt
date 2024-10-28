package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OptionX(
    @SerialName("choice")
    val choice: Choice? = Choice(),
    @SerialName("count")
    val count: Int? = 0,
    @SerialName("of")
    val of: Of? = Of(),
    @SerialName("option_type")
    val optionType: String? = ""
)