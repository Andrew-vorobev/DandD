package com.example.dandd.data.repo

import com.example.dandd.data.converter.ClassDbToClassItem
import com.example.dandd.data.converter.ItemDbToItem
import com.example.dandd.data.dao.ClassDao
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

    suspend fun getClassesDatabase(): List<ClassItem>

    suspend fun getItem(index: String): Item

    suspend fun insertClass(classItem: ClassItem)

    suspend fun deleteClass(classItem: ClassItem)
}

class ItemRepositoryImpl(
    private val classDao: ClassDao,
    private val itemDbToItem: ItemDbToItem,
    private val itemApi: ItemApi,
    private val itemNetworkToItemDb: ItemNetworkToItemDb,
    private val classConverterNetwork: ClassesNetworkToClassDb,
    private val classConverterDb: ClassDbToClassItem
) : ItemRepository {
    override suspend fun getClasses(): List<ClassItem> {
        return withContext(Dispatchers.IO) {
            try{
                val classesNetwork = itemApi.getClasses()
                val classesDb = classesNetwork.results?.map { classConverterNetwork.convert(it!!) }
                classesDb?.map { classConverterDb.convertToItem(it) } ?: emptyList()
            } catch(e: Exception) {
                emptyList()
            }
        }
    }

    override suspend fun getClassesDatabase(): List<ClassItem> {
        return withContext(Dispatchers.IO){
            val classesDb = classDao.getItems()
            classesDb.map { classConverterDb.convertToItem(it) }
        }
    }

    override suspend fun getItem(index: String): Item {
        return withContext(Dispatchers.IO){
            val itemNetwork: ItemNetwork = itemApi.getClass(index = index)
            val itemDb: ItemDb = itemNetworkToItemDb.convert(itemNetwork)
            itemDbToItem.convert(itemDb)
        }
    }



    override suspend fun insertClass(classItem: ClassItem) {
        classDao.insertItem(classConverterDb.convertToDb(classItem))
    }

    override suspend fun deleteClass(classItem: ClassItem) {
        classDao.deleteItem(classItem.index ?: "")
    }
}