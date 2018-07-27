package com.danesfeder.kotlinapplication

import android.app.Application
import com.danesfeder.kotlinapplication.utils.DelegatesExt

class ExampleApplication : Application() {

  companion object {
    val instance: ExampleApplication by DelegatesExt.notNullSingleValue()
  }
}