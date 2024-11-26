package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StartingEquipment(
    @SerialName("equipment")
    val equipment: Equipment? = null,
    @SerialName("quantity")
    val quantity: Int? = null
)