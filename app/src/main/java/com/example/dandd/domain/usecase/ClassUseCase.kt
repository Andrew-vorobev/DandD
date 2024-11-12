package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.domain.model.ClassItem

/**
 * @author Andrew
 */
interface ClassUseCase {
    suspend fun getClasses(): List<ClassItem>
}

class ClassUseCaseImpl(private val repository: ItemRepository) : ClassUseCase{
    override suspend fun getClasses(): List<ClassItem> {
        return repository.getClasses()
    }

}