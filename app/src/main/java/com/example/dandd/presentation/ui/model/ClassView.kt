package com.example.dandd.presentation.ui.model

import android.net.Uri
import android.os.Parcelable
import com.example.dungeonanddragonsapp.presentation.ui.util.Serializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
class ClassView (
    val index: String,
    val name: String,
    @Serializable(with = Serializer::class)
    val url: Uri
) : Parcelable
