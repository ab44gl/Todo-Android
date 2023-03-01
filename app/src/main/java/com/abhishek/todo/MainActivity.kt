package com.abhishek.todo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.todo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    //viewmodel
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodoApplication).repository)
    }
    //adapter
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //----------------
        todoAdapter= TodoAdapter()
        binding.recycleView.apply {
            adapter=todoAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
            addItemDecoration(MarginItemDecoration())
        }
        todoViewModel.allTodos.observe(this, Observer {words->
            words?.let {
                todoAdapter.submitList(it)
                println("new data")
            }
        })
        binding.fbuttonAdd.setOnClickListener {
            todoViewModel.insert(TodoItem.random())
        }
        todoAdapter.setCheckBoxListener { isCheck, pos ->
            //update date in table at pos
            todoViewModel.update(TodoItem(pos+1,todoAdapter.currentList[pos].text,isCheck))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete_all->{
                todoViewModel.deleteAll()
                return true
            }
            else->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
