package com.example.dandd.data.retrofit.model.itemInfo


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemNetwork(
    @SerialName("class_levels")
    val class_levels: String? = "",
    @SerialName("hit_die")
    val hit_die: Int? = 0,
    @SerialName("index")
    val index: String? = "",
    @SerialName("multi_classing")
    val multi_classing: MultiClassing? = MultiClassing(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("proficiencies")
    val proficiencies: List<ProficiencyX>? = listOf(),
    @SerialName("proficiency_choices")
    val proficiency_choices: List<ProficiencyChoice>? = listOf(),
    @SerialName("saving_throws")
    val saving_throws: List<SavingThrow>? = listOf(),
    @SerialName("starting_equipment")
    val starting_equipment: List<StartingEquipment>? = listOf(),
    @SerialName("starting_equipment_options")
    val starting_equipment_options: List<StartingEquipmentOption>? = listOf(),
    @SerialName("subclasses")
    val subclasses: List<Subclasse>? = listOf(),
    @SerialName("url")
    val url: String? = ""
)