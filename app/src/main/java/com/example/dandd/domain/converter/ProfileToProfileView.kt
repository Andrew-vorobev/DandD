package com.example.dandd.domain.converter

import android.net.Uri
import com.example.dandd.domain.model.Profile
import com.example.dandd.presentation.ui.model.profile.ProfileView

interface ProfileToProfileView {
    fun convertToView(profile: Profile): ProfileView

    fun convertToItem(profileView: ProfileView): Profile
}

class ProfileToProfileViewImpl : ProfileToProfileView {
    override fun convertToView(profile: Profile): ProfileView {
        return ProfileView(
            name = profile.name.ifEmpty { EMPTY_TAG },
            description = profile.description.ifEmpty { EMPTY_TAG },
            photo = if (profile.photo.isEmpty()) Uri.EMPTY else Uri.parse(profile.photo),
            link = profile.link.ifEmpty { EMPTY_TAG }
        )
    }

    override fun convertToItem(profileView: ProfileView): Profile {
        return Profile(
            name = if(profileView.name == EMPTY_TAG) "" else profileView.name,
            description = if(profileView.description == EMPTY_TAG) "" else profileView.description,
            photo = if(profileView.photo == Uri.EMPTY) "" else profileView.photo.toString(),
            link = if(profileView.link == EMPTY_TAG) "" else profileView.link
        )
    }
    companion object{
        const val EMPTY_TAG = "Пусто"
    }
}