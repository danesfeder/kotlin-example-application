package com.danesfeder.kotlinapplication.command

import com.danesfeder.kotlinapplication.ForecastDataMapper
import com.danesfeder.kotlinapplication.ForecastRequest
import com.danesfeder.kotlinapplication.domain.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}