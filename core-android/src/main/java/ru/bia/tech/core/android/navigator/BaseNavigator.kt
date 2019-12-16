package ru.bia.tech.core.android.navigator

import ru.bia.tech.core.api.navigator.Navigator
import ru.bia.tech.core.api.navigator.NavigatorHandler

open class BaseNavigator : Navigator {
  override var localHandler: NavigatorHandler? = null
  override var globalHandler: NavigatorHandler? = null
}