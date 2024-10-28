package com.example.dungeonanddragonsapp.data.mock

import android.net.Uri
import com.example.dandd.data.model.ItemDb
import com.example.dandd.presentation.ui.model.skill.Skill

/**
 * @author Andrew
 */
class MockData {
    var itemDb = ItemDb(
        index = "cha",
        name = "CHA",
        fullName = "Charisma",
        desc = listOf(
            "Charisma measures your ability to interact effectively with others. It includes such factors as confidence and eloquence, and it can represent a charming or commanding personality.",
            "A Charisma check might arise when you try to influence or entertain others, when you try to make an impression or tell a convincing lie, or when you are navigating a tricky social situation. The Deception, Intimidation, Performance, and Persuasion skills reflect aptitude in certain kinds of Charisma checks."
        ),
        skills = listOf(
            Skill(
                choose = 0,
                desc = "deception",
            ), Skill(
                0,
                desc = "intimidation",
            )
        ),
        url = Uri.parse("/api/ability-scores/cha")
    )

    val listItems = mutableListOf(itemDb, itemDb.copy(fullName = "bebe"), itemDb)
}