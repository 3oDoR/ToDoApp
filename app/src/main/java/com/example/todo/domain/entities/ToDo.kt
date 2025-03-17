package com.example.todo.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val note: String,
    @TypeConverters(Converter::class)
    val date: LocalDate,
    @TypeConverters(Converter::class)
    val notificationDate: LocalDate? = null,
    val isDone: Boolean = false
)
