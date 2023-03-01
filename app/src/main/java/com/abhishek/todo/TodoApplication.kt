package com.abhishek.todo

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodoApplication: Application() {
    //no need to cancel this scope as it'll be torn down with process
    val applicationScope=CoroutineScope(SupervisorJob())
    val database by lazy { TodoDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TodoRepository(database.todoDao()) }
}