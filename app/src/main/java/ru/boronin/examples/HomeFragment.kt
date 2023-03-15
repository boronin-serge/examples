package ru.boronin.examples

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


class HomeFragment : Fragment(R.layout.fragment_home) {

  private val TAG = "HomeFragment"

  init {
    Log.d(TAG, "constructor - ${lifecycle.currentState.name}")
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(TAG, "onAttach - ${lifecycle.currentState.name}")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate - ${lifecycle.currentState.name}")
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d(TAG, "onCreateView - ${lifecycle.currentState.name}")
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d(TAG, "onViewCreated - ${lifecycle.currentState.name}")
  }


  override fun onViewStateRestored(@Nullable savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d(TAG, "onViewStateRestored - ${lifecycle.currentState.name}")
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    Log.d(TAG, "onSaveInstanceState - ${lifecycle.currentState.name}")
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart - ${lifecycle.currentState.name}")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume - ${lifecycle.currentState.name}")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause - ${lifecycle.currentState.name}")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop - ${lifecycle.currentState.name}")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Log.d(TAG, "onDestroyView - ${lifecycle.currentState.name}")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy - ${lifecycle.currentState.name}")
  }

  override fun onDetach() {
    super.onDetach()
    Log.d(TAG, "onDetach - ${lifecycle.currentState.name}")
  }
}
