package com.example.mybitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.View

class MainActivity : AppCompatActivity(){

    lateinit var foods: ArrayList<Food>
    var counter = 0
    var numOfItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        confugureNextScreenButton()
        foods = getFood.getTheFood() as ArrayList<Food>
        val adapte = FoodAdapter(foods)
        val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        foodRv.adapter = adapte
        //adapte.notifyItemInserted(numOfItems)
        foodRv.layoutManager = LinearLayoutManager(this)
        //counter ++


    }

    private fun confugureNextScreenButton(){
        val addFoodButton = findViewById<Button>(R.id.addFoodbutton)
        addFoodButton.setOnClickListener{
            startActivity(Intent(this@MainActivity,addFoodActivity::class.java))
        }

    }
}