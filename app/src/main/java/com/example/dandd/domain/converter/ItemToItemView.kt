package com.example.dandd.domain.converter

import com.example.dungeonanddragonsapp.data.model.Item
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView

interface ItemToItemView{
    fun convert(item: Item) : ItemView
}

class ItemToItemViewImpl(
): ItemToItemView{
    override fun convert(item: Item): ItemView {
        return ItemView(
            index = item.index,
            name = item.name,
            fullName = item.fullName,
            desc = item.desc,
            skills = item.skills,
            url = item.url
        )
    }

}