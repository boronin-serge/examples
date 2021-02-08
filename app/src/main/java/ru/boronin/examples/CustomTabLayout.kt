package ru.boronin.examples

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

/**
 * Created by Sergey Boronin on 19.02.2020.
 */
class CustomTabLayout @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr)
