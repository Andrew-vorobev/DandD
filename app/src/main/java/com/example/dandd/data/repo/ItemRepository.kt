package com.example.dandd.data.repo

import com.example.dandd.data.dao.ItemDao
import com.example.dandd.data.retrofit.api.ItemApi
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dungeonanddragonsapp.data.converter.ItemDbToItem
import com.example.dungeonanddragonsapp.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * @author Andrew
 */

interface ItemRepository {
    suspend fun getItems(): Flow<List<Item>>

    suspend fun getItem(id: Int): Flow<Item>
}

class ItemRepositoryImpl(
    private val itemDao: ItemDao,
    private val itemDbToItem: ItemDbToItem,
    private val itemApi: ItemApi,
    private val itemNetworkToItemDb: ItemNetworkToItemDb
) : ItemRepository {
    override suspend fun getItems(): Flow<List<Item>> {
        val itemNetwork = itemApi.getItems("barbarian")
        itemDao.insertItem(itemNetworkToItemDb.convert(itemNetwork))
        val listItemDb = itemDao.getItems().first()
        return flow { emit(listItemDb.map { itemDbToItem.convert(it) }) }
    }

    override suspend fun getItem(id: Int): Flow<Item> =
        flow { emit(itemDbToItem.convert(itemDb = itemDao.getItem(id).first())) }

}