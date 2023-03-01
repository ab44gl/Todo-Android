package com.abhishek.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface TodoDao {
    @Query("SELECT * FROM ${TodoItem.tableName}")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:TodoItem)

    @Query("DELETE FROM ${TodoItem.tableName}")
    suspend fun deleteAll()
    @Update
    suspend fun update(item: TodoItem)
}