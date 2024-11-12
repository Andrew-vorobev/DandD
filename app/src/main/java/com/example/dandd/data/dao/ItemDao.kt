package com.example.dandd.data.dao

import com.example.dandd.data.model.ItemDb
import com.example.dandd.data.mock.MockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Andrew
 */
interface ItemDao {
    suspend fun getItems(): Flow<List<ItemDb>>

    suspend fun getItem(id: Int): Flow<ItemDb>

    suspend fun insertItem(item: ItemDb)
}

class ItemDaoImpl(
    private val mockData: MockData = MockData()
) : ItemDao {
    override suspend fun getItems() = flow { emit(mockData.listItems) }

    override suspend fun getItem(id: Int) = flow { emit(mockData.listItems[id]) }
    override suspend fun insertItem(item: ItemDb) {
        mockData.listItems.add(item)
    }

}