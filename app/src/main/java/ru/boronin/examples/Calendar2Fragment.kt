package ru.boronin.examples

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class Calendar2Fragment : Fragment(R.layout.fragment_calendar_2) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("test", "Calendar2Fragment::onCreate()")
  }
}
