package com.example.todoapp.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.todoapp.models.TodoItem
import com.example.todoapp.models.TodoItemsList

@Composable
fun DeleteItemDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    item: TodoItem,
    todoItemsList: TodoItemsList
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Delete Item")
            },
            confirmButton = {
                Button(
                    onClick = {
                        todoItemsList.deleteItem(item)
                        setShowDialog(false)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray, contentColor = Color.White),
                ) {
                    Text("Cancel")
                }
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this item '${item.name}'",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            },
        )
    }
}
