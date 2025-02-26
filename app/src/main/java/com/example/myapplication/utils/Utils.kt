package com.example.myapplication.utils

import java.text.SimpleDateFormat

fun formateDate(timestamp:Int): String{
    val sdf= SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

fun formateDataTime(timestamp: Int) : String{
    val sdf= SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)
    return sdf.format(date)


}
fun formatDecimals(item : Double) :String{
    return " %.0f".format(item)
}