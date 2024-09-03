package com.seanof.todo.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.seanof.todo.data.TodoItem
import com.seanof.todo.ui.theme.LargeDp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import com.seanof.todo.R
import com.seanof.todo.ui.theme.InputEmptyFontSize

@Composable
fun TodoItemListComposable(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<TodoItem>> = flowOf(listOf()),
    onTodoItemClick: (TodoItem) -> Unit = {},
    onTodoItemUpdate: (TodoItem) -> Unit = {},
    onTodoItemDelete: (TodoItem) -> Unit = {}
) {
    val todoItems = todoItemsFlow.collectAsState(initial = listOf()).value
    LazyColumn (modifier = modifier) {
        items(todoItems) { todoItem ->
            TodoItemComposable(todoItem = todoItem,
                onItemClick = {
                    onTodoItemClick(todoItem)
                },
                onItemUpdate = {
                    todoItem.title = it
                    onTodoItemUpdate(todoItem)
                },
                onItemDelete = {
                    onTodoItemDelete(todoItem)
                }
            )
        }
    }
    if (todoItems.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LargeDp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                textAlign = TextAlign.Center,
                fontSize = InputEmptyFontSize,
                text = stringResource(R.string.get_started),
            )
        }
    }
}
