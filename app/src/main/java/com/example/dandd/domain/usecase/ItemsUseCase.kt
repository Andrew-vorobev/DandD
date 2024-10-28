package com.example.dandd.domain.usecase

import com.example.dungeonanddragonsapp.data.model.Item
import com.example.dandd.data.repo.ItemRepository
import kotlinx.coroutines.flow.Flow

interface ItemsUseCase{
    suspend fun getItems() : Flow<List<Item>>
}

class ItemsUseCaseImpl(
    private val repo: ItemRepository
) : ItemsUseCase{
    override suspend fun getItems() : Flow<List<Item>> {
        return repo.getItems()
    }

}