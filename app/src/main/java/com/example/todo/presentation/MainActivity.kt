package com.example.todo.presentation

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.todo.data.AppDatabase
import com.example.todo.domain.entities.ToDo
import com.example.todo.presentation.screens.MainScreen
import com.example.todo.presentation.screens.TodayScreen
import com.example.todo.presentation.ui.theme.ToDoTheme
import com.example.todo.presentation.viewmodels.TodayViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    private lateinit var todayViewModel: TodayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todayViewModel = ViewModelProvider(this)[TodayViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                MainScreen()
            }
        }
    }
}

