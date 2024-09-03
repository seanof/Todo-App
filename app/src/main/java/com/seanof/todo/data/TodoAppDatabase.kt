package com.seanof.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoAppDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}