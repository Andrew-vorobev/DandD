package com.example.dandd.data.repo

import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.dao.ItemDao
import com.example.dandd.data.model.ItemDb
import com.example.dandd.data.retrofit.api.ItemApi
import com.example.dandd.data.retrofit.converter.ClassesNetworkToClassDb
import com.example.dandd.data.retrofit.converter.ItemNetworkToItemDb
import com.example.dandd.data.retrofit.model.itemInfo.ItemNetwork
import com.example.dandd.domain.model.ClassItem
import com.example.dandd.domain.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Andrew
 */

interface ItemRepository {
    suspend fun getClasses(): List<ClassItem>

    suspend fun getItem(index: String): Item
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

    override suspend fun getItem(index: String): Item {
        return withContext(Dispatchers.IO){
            val itemNetwork: ItemNetwork = itemApi.getClass(index = index)
            val itemDb: ItemDb = itemNetworkToItemDb.convert(itemNetwork)
            itemDbToItem.convert(itemDb)
        }
    }
}