package com.example.dandd.data.dao

import com.example.dungeonanddragonsapp.data.mock.MockData
import com.example.dungeonanddragonsapp.data.model.ItemDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Andrew
 */
interface ItemDao {
    fun getItems(): Flow<List<ItemDb>>

    fun getItem(id: Int): Flow<ItemDb>
}

class ItemDaoImpl(
    private val mockData: MockData = MockData()
) : ItemDao {
    override fun getItems() = flow { emit(mockData.listItems) }

    override fun getItem(id: Int) = flow { emit(mockData.listItems[id]) }

}