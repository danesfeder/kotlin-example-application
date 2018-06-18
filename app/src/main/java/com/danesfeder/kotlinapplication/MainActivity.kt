package com.danesfeder.kotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danesfeder.kotlinapplication.command.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("20003").execute()
            uiThread { forecastList.adapter = ForecastListAdapter(result) }
        }

        val dan = Mapboxer(firstName = "Dan")
        toast("Mapoxer: ${dan.firstName} ${dan.lastName}")
    }

    class Mapboxer(val firstName: String, val lastName: String = "Nesfeder")
}

