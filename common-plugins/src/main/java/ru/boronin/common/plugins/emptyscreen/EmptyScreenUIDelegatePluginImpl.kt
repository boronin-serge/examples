package ru.boronin.common.plugins.emptyscreen

import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.boronin.common.utils.delegate.weakReference
import ru.boronin.core.android.view.delegate.UIDelegatePlugin
import ru.boronin.core.android.view.delegate.UIDelegatePluginEvent
import ru.boronin.core.android.view.delegate.findViewById

/**
 * Created by Sergey Boronin on 24.10.2019.
 */
class EmptyScreenUIDelegatePluginImpl(
  @IdRes private val hintViewId: Int
) : UIDelegatePlugin<Fragment>(), EmptyScreenUIDelegatePlugin {
  private var hintView by weakReference<View>()

  // region UIDelegatePlugin

  override fun onUIDelegatePluginEvent(event: UIDelegatePluginEvent) {
    super.onUIDelegatePluginEvent(event)

    when (event) {
      is UIDelegatePluginEvent.OnViewBound -> hintView = event.findViewById(hintViewId)
      is UIDelegatePluginEvent.Release -> hintView = null
      else -> { }
    }
  }

  // endregion

  // region EmptyScreenUIDelegatePlugin

  override fun setVisibleEmptyHint(visible: Boolean) {
    hintView?.isVisible = visible
  }

  // endregion
}
