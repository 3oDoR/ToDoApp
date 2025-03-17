package com.example.todo.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import com.example.todo.domain.usecase.AddNoteUseCase
import com.example.todo.domain.usecase.DeleteNoteUseCase
import com.example.todo.domain.usecase.GetAllNotesUseCase
import com.example.todo.domain.usecase.GetNotesByDateAndSortByIsDone
import com.example.todo.domain.usecase.GetNotesByDateUseCase
import com.example.todo.domain.usecase.UpdateToDoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class TodayViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-todo"
    ).fallbackToDestructiveMigration().build()

    var notesState by mutableStateOf(listOf<ToDo>())
    var showDialog by mutableStateOf(false)
    var titleState by mutableStateOf("")
    var noteState by mutableStateOf("")

    private val getNotesByDateUseCase = GetNotesByDateUseCase(db)
    private val getNotesByDateAndSortByIsDone = GetNotesByDateAndSortByIsDone(db)
    private val getAllNotesUseCase = GetAllNotesUseCase(db)
    private val updateToDoUseCase = UpdateToDoUseCase(db)
    private val addNoteUseCase = AddNoteUseCase(db)
    private val deleteNoteUseCase = DeleteNoteUseCase(db)

    init {
        updateNotesState()
    }

    private fun updateNotesState() {
        viewModelScope.launch(Dispatchers.IO) {
            notesState = getNotesByDateAndSortByIsDone.execute(LocalDate.now())
        }
    }

    fun addNewNote() {
        if (titleState.isNotEmpty() && notesState.isNotEmpty()) {
            addNoteUseCase.execute(ToDo(title = titleState, note = noteState, date = LocalDate.now()))
            titleState = ""
            noteState = ""
        }
        updateNotesState()
    }

    fun clearNote() {
        titleState = ""
        noteState = ""
    }

    fun deleteNote(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(toDo)
        }
        updateNotesState()
    }

    fun getCurrentLocalDate(): String {
        return "${LocalDate.now().dayOfMonth} ${LocalDate.now().month} ${LocalDate.now().year}"
    }

    fun changeStatus(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoUseCase.execute(toDo.copy(isDone = !toDo.isDone))
            notesState = getNotesByDateAndSortByIsDone.execute(LocalDate.now())
        }
    }

}