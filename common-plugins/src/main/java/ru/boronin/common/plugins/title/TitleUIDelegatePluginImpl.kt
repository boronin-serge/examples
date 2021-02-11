package ru.boronin.common.plugins.title

import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.boronin.common.plugins.getString
import ru.boronin.common.utils.DEFAULT_STRING
import ru.boronin.common.utils.delegate.weakReference
import ru.boronin.core.android.view.delegate.UIDelegatePlugin
import ru.boronin.core.android.view.delegate.UIDelegatePluginEvent
import ru.boronin.core.android.view.delegate.findViewById

class TitleUIDelegatePluginImpl(
  @IdRes private val titleViewId: Int
) : UIDelegatePlugin<Fragment>(), TitleUIDelegatePlugin {

  private var title by weakReference<TextView>()

  // region UIDelegatePlugin

  override fun onUIDelegatePluginEvent(event: UIDelegatePluginEvent) {
    super.onUIDelegatePluginEvent(event)

    when (event) {
      is UIDelegatePluginEvent.OnViewBound -> {
        title = event.findViewById(titleViewId)
      }

      is UIDelegatePluginEvent.Release -> title = null
      else -> { }
    }
  }

  // endregion

  // region TitleUIDelegatePlugin

  override fun setTitle(@StringRes res: Int) {
    val title = if (res != 0) getString(res) else null
    setTitle(title ?: DEFAULT_STRING)
  }

  override fun setTitle(text: String) {
    title?.text = text
  }

  override fun setTitleVisibility(visible: Boolean) {
    title?.isVisible = visible
  }

  // endregion
}
