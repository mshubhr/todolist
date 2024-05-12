package com.example.todolist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var item : EditText
    private lateinit var add : Button
    private lateinit var view : RecyclerView

    private val taskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.edit)
        add = findViewById(R.id.button)
        view = findViewById(R.id.list)

        sharedPreferences = getSharedPreferences("task_prefs", Context.MODE_PRIVATE)
        loadTasks()

        taskAdapter = TaskAdapter(taskList,
            onItemClick = { task -> showEdit(task) },
            onItemLongClick = { task -> deleteTask(task) }
        )
        view.adapter = taskAdapter
        view.layoutManager = LinearLayoutManager(this)


        add.setOnClickListener{
            val taskTitle = item.text.toString()
            if (taskTitle.isNotBlank()) {
                addTask(taskTitle)
                item.text.clear()
            }
        }
    }

    private fun deleteTask(task: Task) {
        val index = taskList.indexOf(task)
        taskList.removeAt(index)
        taskAdapter.notifyItemRemoved(index)
        saveTasks()
    }

    private fun showEdit(task: Task) {
        val editText = EditText(this)
        editText.setText(task.title)
        AlertDialog.Builder(this)
            .setTitle("Edit Task")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newTitle = editText.text.toString()
                if (newTitle.isNotBlank()) {
                    editTask(task, newTitle)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun editTask(task: Task, newTitle: String) {
        val index = taskList.indexOf(task)
        taskList[index] = task.copy(title = newTitle)
        taskAdapter.notifyItemChanged(index)
        saveTasks()
    }

    private fun addTask(title: String) {
        val taskId = taskList.size + 1
        val newTask = Task(taskId, title)
        taskList.add(newTask)
        taskAdapter.notifyItemInserted(taskList.size - 1)
        saveTasks()
    }

    private fun loadTasks() {
        val tasksJson = sharedPreferences.getString("tasks", null)
        tasksJson?.let {
            val savedTasks = Gson().fromJson(it, Array<Task>::class.java).toList()
            taskList.addAll(savedTasks)
        }
    }

    private fun saveTasks() {
        val tasksJson = Gson().toJson(taskList)
        sharedPreferences.edit().putString("tasks", tasksJson).apply()
    }
}