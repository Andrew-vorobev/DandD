package com.example.dandd.data.retrofit.converter

import android.net.Uri
import com.example.dandd.data.model.ItemDb
import com.example.dandd.data.retrofit.model.ItemNetwork
import com.example.dandd.presentation.ui.model.skill.Skill

interface ItemNetworkToItemDb {
    fun convert(item: ItemNetwork): ItemDb
}

class ItemNetworkToItemDbImpl() : ItemNetworkToItemDb {
    override fun convert(item: ItemNetwork): ItemDb {
        return ItemDb(
            index = item.index ?: "",
            name = item.name ?: "",
            fullName = item.subclasses?.joinToString(separator = " ") { it.name ?: "" } ?: "",
            desc = item.proficiencies?.map { it.name ?: "" } ?: listOf(),
            skills = item.proficiency_choices?.map {
                Skill(
                    choose = it.choose ?: 0,
                    desc = it.desc ?: ""
                )
            },
            url = Uri.parse(item.url)
        )
    }

}