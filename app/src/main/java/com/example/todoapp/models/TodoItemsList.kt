package com.example.todoapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TodoItemsList(todoItemDao: TodoItemDao) {
    private val _todoItemDao = todoItemDao
    var items: List<TodoItem> by mutableStateOf(_todoItemDao.getAll())

    fun addItem(item: TodoItem) {
//        items = listOf(item) + items

        _todoItemDao.insertAll(item)
        items = _todoItemDao.getAll()
    }

    fun editItem(item: TodoItem) {
//        val index = items.indexOf(item)
//        items = items.toMutableList().also {
//            val editedItem = TodoItem(name = it[index].name, isComplete = !it[index].isComplete)
//            it[index] = editedItem
//        }

        val newItem = TodoItem(id = item.id, name = item.name, isComplete = !item.isComplete)
        _todoItemDao.update(newItem)
        items = _todoItemDao.getAll()
    }

    fun deleteItem(item: TodoItem) {
//        val index = items.indexOf(item)
//        items = items.filterIndexed { i, _ -> i != index }

        _todoItemDao.delete(item)
        items = _todoItemDao.getAll()
    }
}