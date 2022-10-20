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
// For the main screen
class MainActivity : AppCompatActivity(){

    lateinit var foods: ArrayList<Food>
    var numOfItems = 0
    val ad = addFoodActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        confugureNextScreenButton()

        //val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        //foods = getFood.getTheFood() as ArrayList<Food>
        //val adapte = FoodAdapter(foods)
        //foodRv.adapter = adapte
        //adapte.notifyItemInserted(0)
        //foodRv.layoutManager = LinearLayoutManager(ad)
        //numOfItems ++

    }

    private fun confugureNextScreenButton(){
        val addFoodButton = findViewById<Button>(R.id.addFoodbutton)
        val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        foods = getFood.getTheFood() as ArrayList<Food>
        val adapte = FoodAdapter(foods)
        foodRv.adapter = adapte
        foodRv.layoutManager = LinearLayoutManager(ad)

        addFoodButton.setOnClickListener{
            startActivity(Intent(this@MainActivity,addFoodActivity::class.java))
            //numOfItems --
        }

    }
}