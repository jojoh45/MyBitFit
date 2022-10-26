package com.example.mybitfit

//private const val TAG = "getFood"

class getFood {
    companion object{
        var name = ""
        var cals = ""

        var items: MutableList<DisplayFood> = ArrayList()
        fun getTheFood(): MutableList<DisplayFood> {

            val listItems = DisplayFood(name,cals)
            items.add(listItems)
            return items
        }
    }
}