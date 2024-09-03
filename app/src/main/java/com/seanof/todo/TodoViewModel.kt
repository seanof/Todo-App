package com.seanof.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seanof.todo.data.TodoItem
import com.seanof.todo.data.TodoItemRepository
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoItemRepo: TodoItemRepository
) :ViewModel() {

    val todoItems = todoItemRepo.todoItems

    fun addTodoItem(itemTitle: String) {
        viewModelScope.launch { todoItemRepo.insert(TodoItem(title = itemTitle)) }
    }

    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch { todoItemRepo.insert(todoItem.copy()) }
    }

    fun setItemDone(todoItem: TodoItem) {
        viewModelScope.launch { todoItemRepo.insert(todoItem.copy(isDone = !todoItem.isDone)) }
    }

    fun removeTodoItem(todoItem: TodoItem) {
        viewModelScope.launch { todoItemRepo.delete(todoItem) }
    }
}