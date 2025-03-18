package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import jakarta.inject.Inject


class AddNoteUseCase @Inject constructor(private val appDatabase: AppDatabase) {


    suspend fun execute(toDo: ToDo) {
        return appDatabase.todoDao().insert(toDo)
    }
}