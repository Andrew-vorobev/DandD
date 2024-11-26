package com.example.dandd.presentation.ui.fragment.profileEdit

import android.net.Uri

interface EditProfileState{
    val name: String
    val description: String
    val photoUri: Uri
}