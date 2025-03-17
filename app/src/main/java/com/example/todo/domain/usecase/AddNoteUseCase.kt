package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AddNoteUseCase(private val appDatabase: AppDatabase) {


    fun execute(toDo: ToDo) {
        runBlocking {
            launch {
                appDatabase.todoDao().insert(toDo)
            }
        }

    }
}