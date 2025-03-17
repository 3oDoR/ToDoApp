package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo

class GetAllNotesUseCase(private val appDatabase: AppDatabase) {

    suspend fun execute(): List<ToDo> {
        return appDatabase.todoDao().getAllNotes()
    }
}