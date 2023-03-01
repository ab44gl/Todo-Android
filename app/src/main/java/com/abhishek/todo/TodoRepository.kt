package com.abhishek.todo

import androidx.annotation.WorkerThread
import androidx.room.Update

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos=todoDao.getAllTodos()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todoItem: TodoItem) {
        todoDao.insert(todoItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(item: TodoItem){
        todoDao.update(item)
    }
}