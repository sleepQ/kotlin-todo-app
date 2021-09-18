package com.example.todoapp.composables

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todoapp.models.TodoItem
import com.example.todoapp.models.TodoItemsList

@ExperimentalComposeUiApi
@Composable
fun InputField(todoItemsList: TodoItemsList) {
    var text by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "Todo") },
            placeholder = { Text("Enter a todo item..") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotEmpty()) {
                        todoItemsList.addItem(TodoItem(name = text))
                        text = ""
                    }
                }
            ),
            modifier = Modifier
                .onKeyEvent {
                    text = text.trim()
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER && text != "\n" && text.isNotEmpty()) {
                        todoItemsList.addItem(TodoItem(name = text))
                        text = ""
                    }
                    false
                }
                .padding(8.dp)
                .fillMaxWidth(),
            singleLine = true
        )
    }
}
