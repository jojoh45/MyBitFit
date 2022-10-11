package com.example.mybitfit
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class addFoodActivity : AppCompatActivity() {

    lateinit var foods: ArrayList<Food>
    var counter = 0
    var numOfItems = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_food)
        //configureBackButton()

        val recordButton = findViewById<Button>(R.id.recordButton)
        val nameET = findViewById<TextView>(R.id.itemNameEditText)
        val calsET = findViewById<TextView>(R.id.itemCalorieEditText)


        recordButton.setOnClickListener {
            val nameETtoString = nameET.text.toString()
            val calsETtoString = calsET.text.toString()

            foods = getFood.getTheFood() as ArrayList<Food>
            foods.add(0, Food(nameETtoString,calsETtoString))

            finishMethod()
        }
    }

    private fun finishMethod(){
        finish()
    }
}
