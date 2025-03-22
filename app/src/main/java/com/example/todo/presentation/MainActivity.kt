package com.example.todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.todo.presentation.screens.MainScreen
import com.example.todo.presentation.ui.theme.ToDoTheme
import com.example.todo.presentation.viewmodels.SearchViewModel
import com.example.todo.presentation.viewmodels.TodayViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var todayViewModel: TodayViewModel
    lateinit var searchViewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todayViewModel = ViewModelProvider(this)[TodayViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                MainScreen()
            }
        }
    }
}

