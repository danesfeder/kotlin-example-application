package com.danesfeder.kotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        val person = Person()
        person.name = "Dan"
        niceToast(person.name)
        person.name = "Pablo"
        niceToast(person.name)
    }

    class Person {
        var name: String = ""
            get() = field.toUpperCase()
            set(value) {
                field = "Name: $value"
            }
    }

    fun niceToast(message: String,
                  tag: String = MainActivity::class.java.simpleName,
                  length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show()
    }

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
