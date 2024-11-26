package com.example.dandd.presentation.ui.fragment.profileEdit

import android.net.Uri
import org.threeten.bp.LocalTime

data class EditProfileState(
    var name: String = "",
    var description: String = "",
    var photoUri: Uri = Uri.EMPTY,
    var time: LocalTime = LocalTime.now(),
    var timeString: String = time.toString(),
    var timeError: String? = null,
    var isNeedToShowTimePicker:Boolean = false,
)