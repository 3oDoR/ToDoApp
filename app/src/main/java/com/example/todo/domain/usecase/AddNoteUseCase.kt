package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AddNoteUseCase(private val appDatabase: AppDatabase) {


    suspend fun execute(toDo: ToDo) {
                appDatabase.todoDao().insert(toDo)
    }
}