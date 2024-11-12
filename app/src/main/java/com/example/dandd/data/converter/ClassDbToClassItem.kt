package com.example.dandd.data.converter

import com.example.dandd.data.model.ClassDb
import com.example.dandd.domain.model.ClassItem

/**
 * @author Andrew
 */
interface ClassDbToClassItem {
    fun convert(classDb: ClassDb) : ClassItem
}

class ClassDbToClassItemImpl() : ClassDbToClassItem{
    override fun convert(classDb: ClassDb): ClassItem {
        return ClassItem(
            name = classDb.name,
            index = classDb.index,
            url = classDb.url
        )
    }

}