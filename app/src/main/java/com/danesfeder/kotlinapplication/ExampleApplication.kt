package com.danesfeder.kotlinapplication

import android.app.Application

class ExampleApplication : Application() {

  companion object {
    lateinit var instance: ExampleApplication
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}