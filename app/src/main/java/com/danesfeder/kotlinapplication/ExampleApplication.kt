package com.danesfeder.kotlinapplication

import android.app.Application
import com.danesfeder.kotlinapplication.utils.DelegatesExt

class ExampleApplication : Application() {

  companion object {
    var instance: ExampleApplication by DelegatesExt.notNullSingleValue()
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}