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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// For the main screen
class MainActivity : AppCompatActivity(){

    lateinit var foods: ArrayList<DisplayFood>
    var counter = 0
    val ad = addFoodActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addFoodButton = findViewById<Button>(R.id.addFoodbutton)

        displayFood()





        //val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        //foods = getFood.getTheFood() as ArrayList<Food>
        //val adapte = FoodAdapter(foods)
        //foodRv.adapter = adapte
        //adapte.notifyItemInserted(0)
        //foodRv.layoutManager = LinearLayoutManager(ad)
        //numOfItems ++

        addFoodButton.setOnClickListener{
            startActivity(Intent(this@MainActivity,addFoodActivity::class.java))
        }

    }

    private fun displayFood(){

        val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        foods = getFood.getTheFood() as ArrayList<DisplayFood>
        val adapte = FoodAdapter(foods)
        foodRv.adapter = adapte

        foodRv.layoutManager = LinearLayoutManager(ad).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRv.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect{ databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.cals
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    adapte.notifyDataSetChanged()
                }
            }
        }
    }
}