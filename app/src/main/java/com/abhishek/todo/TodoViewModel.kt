package com.abhishek.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
):ViewModel() {
    val  allTodos=repository.allTodos.asLiveData()
    //using coroutine to handle blocking call
    fun insert(todoItem: TodoItem)=viewModelScope.launch {
        repository.insert(todoItem)
    }
    fun update(todoItem: TodoItem)=viewModelScope.launch {
        repository.update(todoItem)
    }
    fun deleteAll()=viewModelScope.launch {
        repository.deleteAll()
    }
}
//since our ViewModel use arguments so we must create a factory
class TodoViewModelFactory(private val repository: TodoRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}