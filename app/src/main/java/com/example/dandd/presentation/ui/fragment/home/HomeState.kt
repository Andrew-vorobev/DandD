package com.example.dandd.presentation.ui.fragment.home

import android.net.Uri

data class HomeState(
    val name: String = "",
    val description: String = "",
    val photo: Uri = Uri.EMPTY,
    val link: String = ""
)