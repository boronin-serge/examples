package ru.boronin.examples

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("test", "CalendarFragment(${hashCode()})::onCreate()")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("test", "CalendarFragment(${hashCode()})::onDestroy()")
  }
}
