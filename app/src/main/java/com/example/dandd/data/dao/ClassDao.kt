package com.example.dandd.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dandd.data.model.ClassDb

/**
 * @author Andrew
 */
@Dao
interface ClassDao {
    @Query("SELECT * FROM classesTable")
    suspend fun getItems(): List<ClassDb>

    @Insert
    suspend fun insertItem(classDb: ClassDb)

    @Query("DELETE FROM classesTable WHERE `index` = :index")
    suspend fun deleteItem(index: String)
}