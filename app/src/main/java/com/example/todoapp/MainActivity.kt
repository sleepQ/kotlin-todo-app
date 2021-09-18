package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.todoapp.models.TodoItemsList
import com.example.todoapp.composables.InputField
import com.example.todoapp.composables.TodoItems
import com.example.todoapp.models.TodoItem
import com.example.todoapp.models.TodoItemDao
import com.example.todoapp.ui.theme.TodoappTheme

const val TABLE_NAME: String = "todo-database"

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        applicationContext.deleteDatabase(TABLE_NAME)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, TABLE_NAME
        ).allowMainThreadQueries().build()

        val todoItemDao = db.todoItemDao()
        val todoItemsList = TodoItemsList(todoItemDao)

        setContent {
            TodoappTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Text(
                            "Todo App",
                            fontSize = 30.sp,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(20.dp)
                        )
                        InputField(todoItemsList)
                        TodoItems(todoItemsList)
                    }
                }
            }
        }
    }
}

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}