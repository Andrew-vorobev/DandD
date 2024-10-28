package com.example.dungeonanddragonsapp.data.dao

import com.example.dungeonanddragonsapp.data.mock.MockData
import com.example.dungeonanddragonsapp.data.model.ItemDb

/**
 * @author Andrew
 */
interface ItemDao {
    fun getItems() : List<ItemDb>

    fun getItem(id: Int) : ItemDb
}

class ItemDaoImpl(
    private val mockData: MockData = MockData()
): ItemDao {
    override fun getItems() = mockData.listItems

    override fun getItem(id: Int) = mockData.listItems[id]

}