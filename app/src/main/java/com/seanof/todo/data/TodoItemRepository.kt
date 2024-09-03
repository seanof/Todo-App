package com.seanof.todo.data

import kotlinx.coroutines.flow.Flow

class TodoItemRepository(private val todoItemDao: TodoItemDao) {
    val todoItems: Flow<List<TodoItem>> = todoItemDao.getTodoItems()

    suspend fun insert(todoItem: TodoItem) {
        todoItemDao.insert(todoItem)
    }

    suspend fun delete(todoItem: TodoItem) {
        todoItemDao.delete(todoItem)
    }
}