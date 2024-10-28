package com.example.dungeonanddragonsapp.data.model

import android.net.Uri
import com.example.dungeonanddragonsapp.presentation.ui.model.skill.Skill

/**
 * @author Andrew
 */
data class Item(
    val index: String,
    val name: String,
    val fullName: String,
    val desc: List<String>,
    val skills: List<Skill>,
    val url: Uri
)