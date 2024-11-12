package com.example.dandd.data.converter

import com.example.dandd.data.model.ClassDb
import com.example.dandd.domain.model.ClassItem

/**
 * @author Andrew
 */
interface ClassDbToClassItem {
    fun convertToItem(classDb: ClassDb) : ClassItem

    fun convertToDb(classItem: ClassItem) : ClassDb
}

class ClassDbToClassItemImpl() : ClassDbToClassItem{
    override fun convertToItem(classDb: ClassDb): ClassItem {
        return ClassItem(
            name = classDb.name,
            index = classDb.index,
            url = classDb.url
        )
    }

    override fun convertToDb(classItem: ClassItem): ClassDb {
        return ClassDb(
            id = null,
            name = classItem.name,
            index = classItem.index,
            url = classItem.url
        )
    }


}