package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.domain.entities.Converter
import com.example.todo.domain.entities.ToDo

@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}