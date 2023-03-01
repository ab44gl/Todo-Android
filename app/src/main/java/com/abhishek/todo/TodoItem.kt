package com.abhishek.todo

import androidx.room.*
import kotlin.random.Random

@Entity(tableName = TodoItem.tableName)
class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    var text:String,
    var isComplete:Boolean=false
) {
    companion object {
        const val tableName="todo_table"
        fun random(): TodoItem {
            val num=Random.nextInt(1000, 9999)
            return TodoItem(text = num.toString())
        }
    }
    override fun toString(): String {
        return "$id $text $isComplete \n"
    }
}