package com.seanof.todo.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.seanof.todo.R
import com.seanof.todo.ui.theme.InputBarColor
import com.seanof.todo.ui.theme.InputBarHeight
import com.seanof.todo.ui.theme.InputBarLabelColor
import com.seanof.todo.ui.theme.InputFabColor
import com.seanof.todo.ui.theme.InputFontSize
import com.seanof.todo.ui.theme.InputSpacerSize
import com.seanof.todo.ui.theme.MediumDp
import com.seanof.todo.ui.theme.SmallDp
import com.seanof.todo.ui.theme.TodoIconSize
import com.seanof.todo.ui.theme.TodoTheme

@Composable
fun TodoItemTextInputComposable(
    onValueChange: (String) -> Unit = {}) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column {
        Row(
            modifier = Modifier
                .background(InputBarColor)
                .padding(SmallDp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .height(InputBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(MediumDp)
                    .height(InputBarHeight),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = text,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = InputFontSize,
                    color = Color.White
                ),
                onValueChange = { text = it },
                label = { Text(text = stringResource(R.string.create_new_todo),
                    fontSize = InputFontSize,
                    color = InputBarLabelColor) },
            )
            FloatingActionButton(
                shape = CircleShape,
                containerColor = InputFabColor,
                modifier = Modifier
                    .padding(MediumDp),
                onClick = {
                    if (text != "") {
                        onValueChange(text)
                        text = ""
                        focusManager.clearFocus()
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add new todo",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(TodoIconSize)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Spacer(modifier = Modifier
                .height(InputSpacerSize))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemTextInputPreview() {
    TodoTheme {
        TodoItemTextInputComposable()
    }
}