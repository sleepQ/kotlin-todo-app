package com.example.todoapp.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todoapp.models.TodoItem
import com.example.todoapp.models.TodoItemsList

@ExperimentalFoundationApi
@Composable
fun TodoItems(todoItemsList: TodoItemsList) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        todoItemsList.items.forEach { item ->
            Item(item, todoItemsList)
        }
    }
}

@Composable
fun Item(item: TodoItem, todoItemsList: TodoItemsList) {
    val horizontalBtnPadding = 5.dp

    Row(modifier = Modifier
        .clickable { todoItemsList.editItem(item) }
        .padding(10.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

        Text(
            modifier = Modifier.fillMaxWidth(0.60f),
            text = AnnotatedString(item.name),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row {
            if (item.isComplete) {
                Button(
                    onClick = {
                        todoItemsList.editItem(item)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(horizontal = horizontalBtnPadding)
                ) {
                    Icon(
                        Icons.Rounded.Done,
                        contentDescription = "Complete Todo Item"
                    )
                }
            }
            Button(
                onClick = {
                    setShowDialog(true)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(horizontal = horizontalBtnPadding)
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Remove Todo Item"
                )
            }
        }

        DeleteItemDialog(showDialog, setShowDialog, item, todoItemsList)
    }
}