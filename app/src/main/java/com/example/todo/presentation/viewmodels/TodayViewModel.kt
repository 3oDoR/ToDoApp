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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(application: Application, val appDatabase: AppDatabase) : AndroidViewModel(application) {

    var notesState by mutableStateOf(listOf<ToDo>())
    var showDialog by mutableStateOf(false)
    var titleState by mutableStateOf("")
    var noteState by mutableStateOf("")

    @Inject
    lateinit var getNotesByDateUseCase: GetNotesByDateUseCase
    @Inject
    lateinit var getNotesByDateAndSortByIsDone: GetNotesByDateAndSortByIsDone
    @Inject
    lateinit var  getAllNotesUseCase: GetAllNotesUseCase
    @Inject
    lateinit var  updateToDoUseCase: UpdateToDoUseCase
    @Inject
    lateinit var  addNoteUseCase: AddNoteUseCase
    @Inject
    lateinit var  deleteNoteUseCase: DeleteNoteUseCase

    init {
        updateNotesState()
    }

    private fun updateNotesState() {
        viewModelScope.launch(Dispatchers.IO) {
            notesState = getNotesByDateAndSortByIsDone.execute(LocalDate.now())
        }
    }

    fun addNewNote() {
        if (titleState.isNotEmpty() && noteState.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                addNoteUseCase.execute(
                    ToDo(
                        title = titleState,
                        note = noteState,
                        date = LocalDate.now()
                    )
                )
                titleState = ""
                noteState = ""
                updateNotesState()
            }
        }

    }

    fun clearNote() {
        titleState = ""
        noteState = ""
    }

    fun deleteNote(toDo: ToDo) {
        println(toDo)
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(toDo)
            updateNotesState()
        }

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