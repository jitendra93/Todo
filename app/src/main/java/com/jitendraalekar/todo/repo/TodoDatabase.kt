package com.jitendraalekar.todo.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DB_NAME = "stuff.db"


@Database(entities = [ToDoEntity::class], version = 1)
@TypeConverters(TypeTransMogrifier::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoStore(): ToDoEntity.Store

    companion object {
        fun newInstance(context: Context) {
            Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).build()
        }
    }
}