package com.example.dandd.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dandd.data.converter.TypeConvert
import com.example.dandd.data.model.ClassDb

/**
 * @author Andrew
 */
@Database(
    entities = [
        ClassDb::class
    ],
    version = 1
)
@TypeConverters(TypeConvert::class)
abstract class ClassDatabase: RoomDatabase(){
    abstract fun ClassDao(): ClassDao
}