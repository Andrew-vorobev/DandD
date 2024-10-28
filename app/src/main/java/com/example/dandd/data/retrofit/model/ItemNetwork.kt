package com.example.dandd.data.retrofit.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemNetwork(
    @SerialName("class_levels")
    val classLevels: String? = "",
    @SerialName("hit_die")
    val hitDie: Int? = 0,
    @SerialName("index")
    val index: String? = "",
    @SerialName("multi_classing")
    val multiClassing: MultiClassing? = MultiClassing(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("proficiencies")
    val proficiencies: List<ProficiencyX>? = listOf(),
    @SerialName("proficiency_choices")
    val proficiencyChoices: List<ProficiencyChoice>? = listOf(),
    @SerialName("saving_throws")
    val savingThrows: List<SavingThrow>? = listOf(),
    @SerialName("starting_equipment")
    val startingEquipment: List<StartingEquipment>? = listOf(),
    @SerialName("starting_equipment_options")
    val startingEquipmentOptions: List<StartingEquipmentOption>? = listOf(),
    @SerialName("subclasses")
    val subclasses: List<Subclasse>? = listOf(),
    @SerialName("url")
    val url: String? = ""
)