package com.example.todo.data.entity

import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val note: String,
    @TypeConverters(DateConverter::class)
    val date: Date,
    val status: Status = Status.IN_PROGRESS
)
