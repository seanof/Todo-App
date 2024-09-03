package com.seanof.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.room.Room
import com.seanof.todo.data.TodoAppDatabase
import com.seanof.todo.data.TodoItemRepository
import com.seanof.todo.ui.theme.MediumDp
import com.seanof.todo.ui.theme.PrimaryGreen
import com.seanof.todo.ui.theme.SmallDp
import com.seanof.todo.ui.theme.TitleTextFontSize
import com.seanof.todo.ui.theme.TodoIconSize
import com.seanof.todo.ui.theme.TodoTheme
import com.seanof.todo.uicomponents.TodoItemListComposable
import com.seanof.todo.uicomponents.TodoItemTextInputComposable

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appDatabase = Room
            .databaseBuilder(applicationContext, TodoAppDatabase::class.java, "todoItemsDb")
            .build()
        val viewModel = TodoViewModel(TodoItemRepository(appDatabase.todoItemDao()))
        setContent {
            TodoTheme {
                Scaffold (
                    Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = PrimaryGreen
                            ),
                            title = {
                                Row {
                                    Card(
                                        modifier = Modifier.size(TodoIconSize),
                                        shape = CircleShape,
                                        elevation = CardDefaults.cardElevation(defaultElevation = SmallDp)
                                    ) {
                                        Image(
                                            painterResource(R.mipmap.ic_launcher),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Text(
                                        "Todo List",
                                        modifier = Modifier.padding(start = MediumDp),
                                        fontSize = TitleTextFontSize,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        TodoItemTextInputComposable(
                            onValueChange = {
                                viewModel.addTodoItem(it)
                            }
                        )
                    },
                ) { innerPadding ->
                    TodoItemListComposable(Modifier
                        .padding(innerPadding),
                        viewModel.todoItems,
                        viewModel::setItemDone,
                        viewModel::updateTodoItem,
                        viewModel::removeTodoItem)
                }
            }
        }
    }
}
