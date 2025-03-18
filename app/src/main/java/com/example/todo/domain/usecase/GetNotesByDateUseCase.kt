package com.example.todo.domain.usecase

import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import jakarta.inject.Inject
import java.time.LocalDate


class GetNotesByDateUseCase @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun execute(date: LocalDate): List<ToDo> {
        return appDatabase.todoDao().loadNotesByDate(date)
    }
}