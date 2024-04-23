package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var item : EditText
    lateinit var add : Button
    lateinit var view : ListView

    var itemList = ArrayList<String>()
    var save = ForSave()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.edit)
        add = findViewById(R.id.button)
        view = findViewById(R.id.list)

        itemList = save.readData(this)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)
        view.adapter = arrayAdapter

        add.setOnClickListener{
            var itemName : String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            save.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

    }
}