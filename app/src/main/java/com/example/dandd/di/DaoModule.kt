package com.example.dandd.di

import android.content.Context
import androidx.room.Room
import com.example.dandd.data.dao.ClassDao
import com.example.dandd.data.dao.ClassDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Andrew
 */
const val databaseName = "classes.db"
val daoModule = module {
    single<ClassDatabase> { provideRoomDatabase(androidContext()) }
    single<ClassDao> { provideDao(get()) }
}


fun provideRoomDatabase(context: Context): ClassDatabase {
    val database: ClassDatabase?
    database = Room.databaseBuilder(context, ClassDatabase::class.java, databaseName)
        .fallbackToDestructiveMigration()
        .build()
    return database
}

private fun provideDao(database: ClassDatabase): ClassDao {
    return database.ClassDao()
}