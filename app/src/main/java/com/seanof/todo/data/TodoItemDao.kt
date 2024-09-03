package com.seanof.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {

    @Query("SELECT * from TodoItem")
    fun getTodoItems(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)
}
