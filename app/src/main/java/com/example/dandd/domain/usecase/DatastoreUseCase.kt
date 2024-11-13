package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.DatastoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Andrew
 */

interface DatastoreUseCase{
    suspend fun save(sorted: Boolean)

    fun get(): Flow<Boolean>
}

class DatastoreUseCaseImpl(
    private val repository: DatastoreRepository
): DatastoreUseCase{
    override suspend fun save(sorted: Boolean) {
        repository.save(sorted)
    }

    override fun get(): Flow<Boolean> {
        return repository.get()
    }

}