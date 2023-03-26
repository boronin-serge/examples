package ru.boronin.examples.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.boronin.examples.R

class TestFragment3 : BaseFragment(R.layout.fragment_test3) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val button = view.findViewById<Button>(R.id.button)
    button.setOnClickListener {
      activity?.supportFragmentManager?.beginTransaction()
        ?.replace(R.id.container, TestFragment4())
        ?.addToBackStack("Test4")
        ?.commit()
    }
  }
}
