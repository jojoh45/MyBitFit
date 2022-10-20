package com.example.mybitfit

//private const val TAG = "getFood"

class getFood {
    companion object{
        var name = ""
        var cals = ""

        var items: MutableList<Food> = ArrayList()
        fun getTheFood(): MutableList<Food> {

            val listItems = Food(name,cals)
            items.add(listItems)
            return items
        }
    }
}