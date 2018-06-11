package com.danesfeder.kotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.util.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }

        val f1 = Forecast(Date(), 27.5f, "Sunny day")
        val f2 = f1.copy(temperature = 30f)

        Log.d(javaClass.simpleName, f2.temperature.toString())
    }

    data class Forecast(val date: Date, val temperature: Float, val details: String)

    val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    private val items = listOf(
            "Mon 6/24 - Sunny - 31/17",
            "Tues 6/25 - Foggy - 34/12",
            "Wed 6/26 - Cloudy - 29/16",
            "Thurs 6/27 - Sunny - 30/18",
            "Fri 6/28 - Rainy - 33/15",
            "Sat 6/29 - Sunny - 37/12",
            "Sun 6/30 - Snowy - 30/17"
    )
}

