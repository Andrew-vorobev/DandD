package com.example.dandd.domain.converter

import android.net.Uri
import com.example.dandd.domain.model.ClassItem
import com.example.dandd.presentation.ui.model.ClassView

/**
 * @author Andrew
 */
interface ClassToClassView {
    fun convertToView(classItem: ClassItem) : ClassView

    fun convertToItem(classView: ClassView) : ClassItem
}

class ClassToClassViewImpl : ClassToClassView{
    override fun convertToView(classItem: ClassItem): ClassView {
        return ClassView(
            name = classItem.name ?: "",
            index = classItem.index ?: "",
            url = Uri.parse(classItem.url) ?: Uri.EMPTY
        )
    }

    override fun convertToItem(classView: ClassView): ClassItem {
        return ClassItem(
            name = classView.name,
            index = classView.index,
            url = classView.url.toString()
        )
    }
}