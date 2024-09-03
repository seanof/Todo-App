package com.seanof.todo.uicomponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.seanof.todo.data.TodoItem
import com.seanof.todo.ui.theme.LargeDp
import com.seanof.todo.ui.theme.MediumDp
import com.seanof.todo.ui.theme.SmallDp
import com.seanof.todo.ui.theme.TodoItemIconColorDone
import com.seanof.todo.ui.theme.TodoItemTextSize
import com.seanof.todo.ui.theme.TodoTheme

@Composable
fun TodoItemComposable(todoItem: TodoItem,
                       onItemClick: () -> Unit = {},
                       onItemUpdate: (String) -> Unit = {},
                       onItemDelete: () -> Unit = {}) {

    val doneTextStrikethrough = if (todoItem.isDone) TextDecoration.LineThrough else null
    var value by remember { mutableStateOf(todoItem.title) }
    Card(
        modifier = Modifier
            .padding(MediumDp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .padding(SmallDp),
                onClick = onItemClick
            ) {
                Icon(Icons.Filled.Check,
                    contentDescription = "Set Todo Item done",
                    tint = if (todoItem.isDone) TodoItemIconColorDone else MaterialTheme.colorScheme.tertiary)
            }
            BasicTextField(
                enabled = !todoItem.isDone,
                modifier = Modifier
                    .weight(1f)
                    .padding(SmallDp),
                value = value,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = TodoItemTextSize,
                    textDecoration = doneTextStrikethrough),
                onValueChange = {
                    value = it
                    onItemUpdate.invoke(it)
                },
            )
            IconButton(
                modifier = Modifier
                    .padding(SmallDp),
                onClick = onItemDelete
            ) {
                Icon(Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = "Delete Todo Item")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemPreview() {
    TodoTheme {
        TodoItemComposable(TodoItem(title ="Buy some milk"))
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemPreviewDone() {
    TodoTheme {
        TodoItemComposable(TodoItem(title ="Buy some bread", isDone = true))
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodoItemPreviewDark() {
    TodoTheme {
        TodoItemComposable(TodoItem(title ="Buy some bread"))
    }
}