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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class MainFragment : Fragment() {
    lateinit var foods: ArrayList<DisplayFood>
    private lateinit var foodRv : RecyclerView
    private lateinit var adapter : FoodAdapter
    val ad = addFoodActivity()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main,container,false)

        foodRv = view.findViewById(R.id.foodRv)

        foods = getFood.getTheFood() as ArrayList<DisplayFood>
        //FoodAdapter(foods)
        //foodRv.adapter = adapte
        adapter = FoodAdapter(foods)
        foodRv.adapter = adapter

        foodRv.layoutManager = LinearLayoutManager(ad).also {
        val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
        foodRv.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
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

        return view
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
