package com.example.mybitfit
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


const val FOOD_EXTRA = "FOOD_EXTRA"


class addFoodActivity : AppCompatActivity() {



    lateinit var foods: ArrayList<DisplayFood>

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_food)

        val recordButton = findViewById<Button>(R.id.recordButton)
        val nameET = findViewById<TextView>(R.id.itemNameEditText)
        val calsET = findViewById<TextView>(R.id.itemCalorieEditText)


        recordButton.setOnClickListener {
            val nameETtoString = nameET.text.toString()
            val calsETtoString = calsET.text.toString()


            foods = getFood.getTheFood() as ArrayList<DisplayFood>


            lifecycleScope.launch(IO){
                //(application as FoodApplication).db.foodDao().deleteAll()
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(
                        name = nameETtoString,
                        cals = calsETtoString
                        )
                    )
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
