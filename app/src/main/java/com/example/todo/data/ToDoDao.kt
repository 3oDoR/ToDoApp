package com.example.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.domain.entities.ToDo
import java.time.LocalDate

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDo WHERE date = :date")
    suspend fun loadNotesByDate(date: LocalDate): List<ToDo>

    @Query("SELECT * FROM ToDo WHERE date = :date ORDER BY isDone")
    suspend fun getNotesByDateAndSortByIsDone(date: LocalDate): List<ToDo>

    @Query("SELECT * FROM ToDo")
    suspend fun getAllNotes(): List<ToDo>

    @Insert
    suspend fun insert(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)

    @Update
    suspend fun update(toDo: ToDo)

}