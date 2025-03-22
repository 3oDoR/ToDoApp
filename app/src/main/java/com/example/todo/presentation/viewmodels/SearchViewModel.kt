package com.example.todo.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.entities.ToDo
import com.example.todo.domain.usecase.DeleteNoteUseCase
import com.example.todo.domain.usecase.GetNotesByTitleUseCase
import com.example.todo.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    var searchState = mutableStateOf("")
    var resultSearchState = mutableStateOf(listOf<ToDo>())

    @Inject
    lateinit var getNotesByTitleUseCase: GetNotesByTitleUseCase

    @Inject
    lateinit var deleteNoteUseCase: DeleteNoteUseCase

    @Inject
    lateinit var updateToDoUseCase: UpdateToDoUseCase

    fun searchTodo(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resultSearchState.value = getNotesByTitleUseCase.execute(search)
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(toDo)
            searchTodo(searchState.value)
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoUseCase.execute(toDo.copy(isDone = !toDo.isDone))
            searchTodo(searchState.value)
        }
    }
}