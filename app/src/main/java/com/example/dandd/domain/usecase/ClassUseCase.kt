package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.domain.model.ClassItem

/**
 * @author Andrew
 */
interface ClassUseCase {
    suspend fun getClasses(): List<ClassItem>

    suspend fun getClassesDatabase(): List<ClassItem>

    suspend fun save(classItem: ClassItem)

    suspend fun delete(classItem: ClassItem)
}

class ClassUseCaseImpl(private val repository: ItemRepository) : ClassUseCase{
    override suspend fun getClasses(): List<ClassItem> {
        return repository.getClasses()
    }

    override suspend fun getClassesDatabase(): List<ClassItem> {
        return repository.getClassesDatabase()
    }

    override suspend fun save(classItem: ClassItem) {
        repository.insertClass(classItem = classItem)
    }

    override suspend fun delete(classItem: ClassItem) {
        repository.deleteClass(classItem)
    }

}