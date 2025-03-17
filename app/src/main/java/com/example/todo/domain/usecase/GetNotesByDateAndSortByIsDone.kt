package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import java.time.LocalDate

class GetNotesByDateAndSortByIsDone(private val appDatabase: AppDatabase) {

    suspend fun execute(date: LocalDate): List<ToDo> {
       return appDatabase.todoDao().getNotesByDateAndSortByIsDone(date)
    }
}