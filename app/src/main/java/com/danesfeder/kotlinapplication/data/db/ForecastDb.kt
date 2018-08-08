package com.danesfeder.kotlinapplication.data.db

import com.danesfeder.kotlinapplication.domain.ForecastList
import com.danesfeder.kotlinapplication.utils.clear
import com.danesfeder.kotlinapplication.utils.parseList
import com.danesfeder.kotlinapplication.utils.parseOpt
import com.danesfeder.kotlinapplication.utils.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: DbDataMapper) {

  fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
    val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
    val dailyForecast = select(DayForecastTable.NAME)
        .whereArgs(dailyRequest, "id" to zipCode, "date" to date)
        .parseList { DayForecast(HashMap(it)) }

    val city = select(CityForecastTable.NAME)
        .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
        .parseOpt { CityForecast(HashMap(it), dailyForecast) }

    if (city != null) dataMapper.convertToDomain(city) else null
  }

  fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
    clear(CityForecastTable.NAME)
    clear(DayForecastTable.NAME)

    with(dataMapper.convertFromDomain(forecast)) {
      insert(CityForecastTable.NAME, *map.toVarargArray())
      dailyForecast.forEach {
        insert(DayForecastTable.NAME, *it.map.toVarargArray())
      }
    }
  }
}