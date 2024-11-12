package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.domain.model.ClassItem

interface ItemsUseCase{
    suspend fun getItems() : List<ClassItem>
}

class ItemsUseCaseImpl(
    private val repo: ItemRepository
) : ItemsUseCase{
    override suspend fun getItems() : List<ClassItem> {
        return repo.getClasses()
    }

}