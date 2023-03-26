package ru.boronin.examples.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

private const val TAG = "Fragment Lifecycle"

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
  private val className: String by ClassNameProvider()

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(TAG, "$className::onAttach")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "$className::onCreate")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d(TAG, "$className::onViewCreated")
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d(TAG, "$className::onViewStateRestored")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Log.d(TAG, "$className::onDestroyView")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "$className::onDestroy")
  }

  override fun onDetach() {
    super.onDetach()
    Log.d(TAG, "$className::onDetach")
  }

}

private class ClassNameProvider {
  operator fun getValue(baseFragment: BaseFragment, property: KProperty<*>): String {
    return baseFragment.javaClass.simpleName
  }
}
