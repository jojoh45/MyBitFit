package com.example.mybitfit
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


const val FOOD_EXTRA = "FOOD_EXTRA"


class addFoodActivity : AppCompatActivity() {



    lateinit var foods: ArrayList<DisplayFood>
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
            val calsETtoString = calsET.text.toString() + "\nCalories"


            foods = getFood.getTheFood() as ArrayList<DisplayFood>
            //val ads = FoodAdapter(foods)
            foods.add(0, DisplayFood(nameETtoString,calsETtoString))
            //ads.notifyItemInserted(numOfItems)
            //numOfItems --
            lifecycleScope.launch(IO){
                (application as FoodApplication).db.foodDao().deleteAll()
                (application as FoodApplication).db.foodDao().insertAll(foods.map {
                    FoodEntity(
                        name = nameETtoString,
                        cals = calsETtoString
                        )
                    })
                }
            finishMethod()
        }
    }

    private fun finishMethod(){
        //val intent = Intent(this,MainActivity::class.java)
        //intent.putExtra("FOOD_EXTRA","Bacon")
        //startActivity(intent)
        finish()
    }
}
