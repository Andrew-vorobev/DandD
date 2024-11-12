package com.example.dandd.data.retrofit.converter

import com.example.dandd.data.model.ClassDb
import com.example.dandd.data.retrofit.model.classes.Results

interface ClassesNetworkToClassDb {
    fun convert(classInfo: Results): ClassDb
}

class ClassesNetworkToClassDbImpl : ClassesNetworkToClassDb {
    override fun convert(classInfo: Results): ClassDb {
        return ClassDb(
            index = classInfo.index,
            name = classInfo.name,
            url = classInfo.url
        )
    }
}