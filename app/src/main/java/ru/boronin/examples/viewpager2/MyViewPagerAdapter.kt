package ru.boronin.examples.viewpager2

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.boronin.examples.fragments.TestFragment1
import ru.boronin.examples.fragments.TestFragment2
import ru.boronin.examples.fragments.TestFragment3

private const val PAGES_COUNT = 2

private const val TAG = "ViewPager2 : FragmentStateAdapter adapter"

class MyViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  override fun getItemCount() = PAGES_COUNT

  override fun createFragment(position: Int): Fragment {
    Log.d(TAG, "createFragment: $position")
    return when(position) {
      0 -> TestFragment2()
      1 -> TestFragment3()
      else -> TestFragment1()
    }
  }
}
