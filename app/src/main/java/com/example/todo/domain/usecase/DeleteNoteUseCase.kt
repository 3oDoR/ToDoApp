package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo

class DeleteNoteUseCase(private val appDatabase: AppDatabase) {

    suspend fun execute(toDo: ToDo) {
        appDatabase.todoDao().delete(toDo)
    }
}