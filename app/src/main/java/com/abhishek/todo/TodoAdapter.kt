package com.abhishek.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.todo.databinding.ListItemBinding

class TodoAdapter:
ListAdapter<TodoItem,RecyclerView.ViewHolder>(DIFF_CALLBACK){
    private var checkBoxListener:((isCheck:Boolean,pos:Int)->Unit)?=null
    fun setCheckBoxListener(f:(isCheck:Boolean,pos:Int)->Unit) {
        checkBoxListener=f
    }
    inner class ViewHolder(val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.checkBox.setOnClickListener{
               if(adapterPosition!=RecyclerView.NO_POSITION){
                   checkBoxListener?.invoke(binding.checkBox.isChecked,adapterPosition)
               }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        currentList[position]?.let { info ->
            holder.binding.let {
                holder.binding.textView.apply {
                    paintFlags= if(info.isComplete){
                        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }else{
                        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
                holder.binding.textView.text=info.text
                holder.binding.checkBox.isChecked=info.isComplete
            }
            println("updated item at $position")
        }
    }
}

object DIFF_CALLBACK : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.text == newItem.text &&
                oldItem.isComplete==newItem.isComplete
    }

}