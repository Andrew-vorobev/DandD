package com.example.dungeonanddragonsapp.data.repo

import com.example.dungeonanddragonsapp.data.converter.ItemDbToItemView
import com.example.dungeonanddragonsapp.data.dao.ItemDao
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView

/**
 * @author Andrew
 */

interface ItemRepository {
    fun getItems(): List<ItemView>

    fun getItem(id: Int): ItemView
}

class ItemRepositoryImpl(
    private val itemDao: ItemDao,
    private val itemDbToItemView: ItemDbToItemView
) : ItemRepository {
    override fun getItems(): List<ItemView> {
        val listItemDb = itemDao.getItems()
        return listItemDb.map { itemDbToItemView.convert(it) }
    }

    override fun getItem(id: Int): ItemView = itemDbToItemView.convert(itemDb = itemDao.getItem(id))

}