package com.example.todoapp.models

import androidx.room.*

@Entity(tableName = "todo_item")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "isComplete") var isComplete: Boolean = false
)

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_item ORDER BY id DESC")
    fun getAll(): List<TodoItem>

    @Insert
    fun insertAll(vararg todoItem: TodoItem)

    @Update
    fun update(todoItem: TodoItem)

    @Delete
    fun delete(todoItem: TodoItem)
}