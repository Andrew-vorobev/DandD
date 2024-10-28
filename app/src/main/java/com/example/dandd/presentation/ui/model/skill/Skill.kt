package com.example.dungeonanddragonsapp.presentation.ui.model.skill

import android.net.Uri
import android.os.Parcelable
import com.example.dungeonanddragonsapp.presentation.ui.util.Serializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Andrew
 */
@Serializable
@Parcelize
class Skill(
    val name: String,
    val index: String,
    @Serializable(with = Serializer::class)
    val url: Uri
) : Parcelable

fun <T> List<T>.toStringBySeparator(selector: (T) -> String, separator: String = ", "): String {
    return this.joinToString(separator, transform = selector)
}