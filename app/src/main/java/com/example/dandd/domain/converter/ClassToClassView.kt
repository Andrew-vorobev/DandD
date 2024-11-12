package com.example.dandd.domain.converter

import android.net.Uri
import com.example.dandd.domain.model.ClassItem
import com.example.dandd.presentation.ui.model.ClassView

/**
 * @author Andrew
 */
interface ClassToClassView {
    fun convert(classInfo: ClassItem) : ClassView
}

class ClassToClassViewImpl() : ClassToClassView{
    override fun convert(classInfo: ClassItem): ClassView {
        return ClassView(
            name = classInfo.name ?: "",
            index = classInfo.index ?: "",
            url = Uri.parse(classInfo.url) ?: Uri.EMPTY
        )
    }
}