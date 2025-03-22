package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import jakarta.inject.Inject

class GetNotesByTitleUseCase @Inject constructor(private val appDatabase: AppDatabase){

    suspend fun execute(title: String): List<ToDo> {
        if (title.isEmpty()) {
            return listOf()
        }
        val res = "%$title%"
        return appDatabase.todoDao().getNotesByTittleUseCase(res)
    }
}