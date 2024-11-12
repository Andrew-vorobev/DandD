package com.example.dandd.data.model

import android.net.Uri
import com.example.dandd.presentation.ui.model.skill.Skill

/**
 * @author Andrew
 */
data class ItemDb(
    val index: String?,
    val name: String?,
    val fullName: String?,
    val desc: List<String>?,
    val skills: List<Skill>?,
    val url: Uri?
)