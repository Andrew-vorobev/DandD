package com.example.dandd.data.dao

import com.example.dandd.data.mock.MockData
import com.example.dandd.data.model.ItemDb

/**
 * @author Andrew
 */
interface ItemDao {
    suspend fun getItems(): List<ItemDb>

    suspend fun getItem(id: Int): ItemDb

    suspend fun insertItem(item: ItemDb)
}

class ItemDaoImpl(
    private val mockData: MockData = MockData()
) : ItemDao {
    override suspend fun getItems() = mockData.listItems

    override suspend fun getItem(id: Int) = mockData.listItems[id]
    override suspend fun insertItem(item: ItemDb) {
        mockData.listItems.add(item)
    }

}