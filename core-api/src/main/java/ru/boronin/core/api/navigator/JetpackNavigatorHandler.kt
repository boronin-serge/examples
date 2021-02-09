package ru.boronin.core.api.navigator

import android.os.Bundle

interface JetpackNavigatorHandler {
  fun open(deepLink: String)
  fun open(obj: Any?)
  fun open(obj: Any?, bundle: Bundle? = null)
  fun back()
  fun parentStackSize(): Int
  fun childStackSize(): Int
}
