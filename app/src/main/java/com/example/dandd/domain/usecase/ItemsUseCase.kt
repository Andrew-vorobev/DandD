package com.example.dandd.domain.usecase

import com.example.dandd.data.repo.ItemRepository
import com.example.dandd.domain.model.Item

interface ItemsUseCase{
    suspend fun getItem(index: String) : Item
}

class ItemsUseCaseImpl(
    private val repo: ItemRepository
) : ItemsUseCase{
    override suspend fun getItem(index: String) : Item {
        return repo.getItem(index = index)
    }

}