package com.example.dungeonanddragonsapp.presentation.ui.model.item

import android.net.Uri
import android.os.Parcelable
import com.example.dungeonanddragonsapp.presentation.ui.model.skill.Skill
import com.example.dungeonanddragonsapp.presentation.ui.util.Serializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Andrew
 */
@Serializable
@Parcelize
class ItemView(
    val index: String,
    val name: String,
    val fullName: String,
    val desc: List<String>,
    val skills: List<Skill>,
    @Serializable(with = Serializer::class)
    val url: Uri
) : Parcelable
