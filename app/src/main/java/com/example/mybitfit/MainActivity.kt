package com.example.mybitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.collections.ArrayList
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

// For the main screen
class MainActivity : AppCompatActivity() {

    private lateinit var foods: ArrayList<DisplayFood>
    private lateinit var adapter : FoodAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayFood()

        val addFoodButton = findViewById<Button>(R.id.addFoodbutton)


        replaceFragment(MainFragment())

        addFoodButton.setOnClickListener{
           startActivity(Intent(this@MainActivity,addFoodActivity::class.java))
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        val informationFragment: Fragment = InformationFragment()
        val mainFragment: Fragment = MainFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemReselectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_log -> fragment = mainFragment
                R.id.nav_dashboard -> fragment = informationFragment
            }
            replaceFragment(fragment)
            true
        }

        // The default selection
        bottomNavigationView.selectedItemId = R.id.nav_log

    }

    private fun replaceFragment(mainFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout,mainFragment)
        fragmentTransaction.commit()
        //displayFood()
    }


    private fun displayFood(){

        foods = getFood.getTheFood() as ArrayList<DisplayFood>
        adapter = FoodAdapter(foods)

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
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
