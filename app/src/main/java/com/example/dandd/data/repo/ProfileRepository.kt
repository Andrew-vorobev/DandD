package com.example.dandd.data.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.dandd.data.converter.ProfileDbToProfileItem
import com.example.dandd.data.model.ProfileDb
import com.example.dandd.data.util.ProfileSerializer
import com.example.dandd.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

interface ProfileRepository {
    fun getProfile(): Flow<Profile>

    suspend fun saveProfile(profile: Profile)
}

class ProfileRepositoryImpl(
    private val mapper: ProfileDbToProfileItem
) : ProfileRepository {
    private val dataStore : DataStore<ProfileDb> by inject(DataStore::class.java, named("profile"))

    override fun getProfile(): Flow<Profile> {
        return dataStore.data.map {
            mapper.convertToItem(it)
        }
    }

    override suspend fun saveProfile(profile: Profile) {
        dataStore.updateData {
            it.copy(
                name = profile.name,
                description = profile.description,
                photo = profile.photo,
                link = profile.link
            )
        }
    }
}

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileDb> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}