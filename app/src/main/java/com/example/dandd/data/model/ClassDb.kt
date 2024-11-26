package com.example.dandd.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Andrew
 */
@Entity(tableName = "classesTable")
data class ClassDb(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val index: String?,
    val name: String?,
    val url: String?
)