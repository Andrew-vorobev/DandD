package com.example.dandd.presentation.ui.model.skill

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author Andrew
 */
@Serializable
@Parcelize
class Skill(
    val choose: Int,
    val desc: String,
) : Parcelable

fun <T> List<T>.toStringBySeparator(selector: (T) -> String, separator: String = ", "): String {
    return this.joinToString(separator, transform = selector)
}