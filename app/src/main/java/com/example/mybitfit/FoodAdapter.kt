package com.example.mybitfit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FoodAdapter(private val foods: List<DisplayFood>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //TextViews that are in the food_list xml
        val foodMainTextView: TextView
        val calMainTextView: TextView


        init {
            foodMainTextView = itemView.findViewById(R.id.foodMaintextView)
            calMainTextView = itemView.findViewById(R.id.calMaintextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.food_list, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val items: DisplayFood = foods.get(position)
        holder.foodMainTextView.text = items.name
        holder.calMainTextView.text = items.cals
    }

    override fun getItemCount(): Int {
        return foods.size
    }

}