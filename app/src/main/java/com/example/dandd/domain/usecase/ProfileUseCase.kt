package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.ProfileRepository
import com.example.dandd.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {
    fun getProfile() : Flow<Profile>

    suspend fun saveProfile(profile: Profile)
}

class ProfileUseCaseImpl(
    private val repo: ProfileRepository
) : ProfileUseCase{
    override fun getProfile(): Flow<Profile> {
        return repo.getProfile()
    }

    override suspend fun saveProfile(profile: Profile) {
        repo.saveProfile(profile)
    }
}