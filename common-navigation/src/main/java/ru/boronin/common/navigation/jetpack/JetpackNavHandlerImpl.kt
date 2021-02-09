package ru.boronin.common.navigation.jetpack

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ru.boronin.core.api.navigator.JetpackNavigatorHandler

class JetpackNavHandlerImpl(
  private val navHostFragment: Fragment
) : JetpackNavigatorHandler {
  private val navController = navHostFragment.findNavController()

  override fun open(deepLink: String) {
    navController.navigate(Uri.parse(deepLink))
  }

  override fun open(obj: Any?, bundle: Bundle?) {
    navController.navigate(obj as Int, bundle)
  }

  override fun open(obj: Any?) {
    try {
      when (obj) {
        is NavDirections -> navController.navigate(obj)
        is Int -> navController.navigate(obj)
      }
    } catch (e: Exception) {
      Log.e("JetpackNavHandlerImpl", e.localizedMessage?.toString() ?: "open error")
    }
  }

  override fun parentStackSize() = navHostFragment.parentFragmentManager.backStackEntryCount

  override fun childStackSize() = navHostFragment.childFragmentManager.backStackEntryCount

  override fun back() {
    navController.navigateUp()
  }
}
