package com.example.mybitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class InformationFragment : Fragment() {
    private lateinit var averageTextView: TextView
    private lateinit var maxTextView: TextView
    private lateinit var minTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_information, container, false)
        averageTextView = view.findViewById(R.id.avgTextView)
        maxTextView = view.findViewById(R.id.maxTextView)
        minTextView = view.findViewById(R.id.minTextView)

        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.cals
                    )
                }.also { mappedList ->
                    updateFood(mappedList)
                }
            }
        }

        return view
    }

    private fun updateFood(food: List<DisplayFood>){

        if (food.isEmpty()){
            averageTextView.text = "No Data"
            maxTextView.text = "No Data"
            maxTextView.text = "No Data"
            return
        }

        var min : Long = Long.MAX_VALUE
        var max : Long = Long.MIN_VALUE
        var sum : Long = 0
        for (food in food) {
            food.cals?.let { it ->
                sum += it.toLong()
                if (it < min.toString()) min = it.toLong()
                if (it > max.toString()) max = it.toLong()
            }
        }

        averageTextView.text = (sum / food.size).toString()
        minTextView.text = min.toString()
        maxTextView.text = max.toString()
        
    }

    companion object {
        @JvmStatic
        fun newInstance() : InformationFragment{
            return InformationFragment()
        }

    }
}