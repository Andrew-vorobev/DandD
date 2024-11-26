package com.example.dandd.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDb(
    val name: String? = null,
    val description: String? = null,
    val photo: String? = null,
    val link: String? = null
)