package ru.boronin.examples.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ru.boronin.examples.R
import ru.boronin.examples.viewpager2.MyViewPagerAdapter

class TestFragment1 : BaseFragment(R.layout.fragment_test1) {

  private lateinit var viewPager: ViewPager2

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewPager = view.findViewById(R.id.viewPager)
    viewPager.adapter = MyViewPagerAdapter(this)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    viewPager.adapter = null
  }
}
