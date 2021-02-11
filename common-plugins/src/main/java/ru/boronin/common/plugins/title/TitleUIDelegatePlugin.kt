package ru.boronin.common.plugins.title

import androidx.annotation.StringRes

interface TitleUIDelegatePlugin {
  fun setTitle(@StringRes res: Int)
  fun setTitle(text: String)
  fun setTitleVisibility(visible: Boolean)
}
