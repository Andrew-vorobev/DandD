package com.example.dandd.data.repo

import com.example.dandd.data.dao.ItemDao
import com.example.dandd.data.retrofit.api.ItemApi
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dungeonanddragonsapp.data.converter.ItemDbToItem
import com.example.dungeonanddragonsapp.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

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
        var res: Flow<List<Item>> = flow { emit(emptyList()) }
        withContext(Dispatchers.IO) {
            if (getItemQuery("barbarian")) {
                val listItemDb = itemDao.getItems().first()
                res = flow { emit(listItemDb.map { itemDbToItem.convert(it) }) }
            }
        }
        return res
    }

    override suspend fun getItem(id: Int): Flow<Item> =
        flow { emit(itemDbToItem.convert(itemDb = itemDao.getItem(id).first())) }

    private suspend fun getItemQuery(query: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val titleApi = itemApi.getItems(query)
                val titleDb = itemNetworkToItemDb.convert(titleApi)
                itemDao.insertItem(titleDb)
                true
            } catch (e: Exception) {
                false
            }
        }
    }

}