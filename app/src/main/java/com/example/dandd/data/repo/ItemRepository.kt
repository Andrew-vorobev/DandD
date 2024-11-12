package com.example.dandd.data.repo

import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.dao.ItemDao
import com.example.dandd.data.retrofit.api.ItemApi
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDb
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dandd.domain.model.ClassItem
import com.example.dandd.domain.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Andrew
 */

interface ItemRepository {
    suspend fun getClasses(): List<ClassItem>

    suspend fun getItem(id: Int): Item
}

class ItemRepositoryImpl(
    private val itemDao: ItemDao,
    private val itemDbToItem: ItemDbToItem,
    private val itemApi: ItemApi,
    private val itemNetworkToItemDb: ItemNetworkToItemDb,
    private val classesNetworkToClassDb: ClassesNetworkToClassDb,
    private val classDbToClassItem: ClassDbToClassItem
) : ItemRepository {
    override suspend fun getClasses(): List<ClassItem> {
        return withContext(Dispatchers.IO) {
            val classesNetwork = itemApi.getClasses()
            val classesDb = classesNetwork.results?.map { classesNetworkToClassDb.convert(it!!) }
            classesDb?.map { classDbToClassItem.convert(it) } ?: emptyList()
        }
    }

    override suspend fun getItem(id: Int): Item =
        itemDbToItem.convert(itemDb = itemDao.getItem(id))

    private suspend fun getItemQuery(query: String): List<Item> {
        return emptyList()
    }

}