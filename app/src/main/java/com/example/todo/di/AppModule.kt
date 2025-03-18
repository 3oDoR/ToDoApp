package com.example.todo.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todo.data.AppDatabase
import com.example.todo.data.ToDoDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,  "database-todo"
        ).fallbackToDestructiveMigration().build()
    }

}