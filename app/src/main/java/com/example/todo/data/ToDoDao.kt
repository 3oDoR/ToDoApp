package com.example.todo.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.entity.ToDo
import java.util.Date

interface ToDo {

    @Query("SELECT note, status FROM ToDo WHERE date = :date")
    fun getNotesByDate(date: Date): List<ToDo>

    @Insert
    fun insert(toDo: ToDo)

    @Delete
    fun delete(toDo: ToDo)
}