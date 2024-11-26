package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FromXX(
    @SerialName("equipment_category")
    val equipmentCategory: EquipmentCategory? = null,
    @SerialName("option_set_type")
    val optionSetType: String? = null
)