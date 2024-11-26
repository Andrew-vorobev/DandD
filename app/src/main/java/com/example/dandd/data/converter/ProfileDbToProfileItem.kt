package com.example.dandd.data.converter

import com.example.dandd.data.model.ProfileDb
import com.example.dandd.domain.model.Profile

interface ProfileDbToProfileItem {
    fun convertToDb(profile: Profile) : ProfileDb

    fun convertToItem(profileDb: ProfileDb) : Profile
}

class ProfileDbToProfileItemImpl : ProfileDbToProfileItem {
    override fun convertToDb(profile: Profile): ProfileDb {
        return ProfileDb(
            name = profile.name,
            description = profile.description,
            photo = profile.photo,
            link = profile.link
        )
    }

    override fun convertToItem(profileDb: ProfileDb): Profile {
        return Profile(
            name = profileDb.name ?: "",
            description = profileDb.description ?: "",
            photo = profileDb.photo ?: "",
            link = profileDb.link ?: ""
        )
    }
}