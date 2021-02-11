package ru.boronin.common.plugins.backbutton

import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.boronin.common.utils.delegate.weakReference
import ru.boronin.core.android.view.delegate.UIDelegatePlugin
import ru.boronin.core.android.view.delegate.UIDelegatePluginEvent
import ru.boronin.core.android.view.delegate.findViewById

class BackButtonUIDelegatePluginImpl(
  @IdRes private val buttonViewId: Int
) : UIDelegatePlugin<Fragment>(), BackButtonUIDelegatePlugin {

  private var button by weakReference<ImageView>()

  // region UIDelegatePlugin

  override fun onUIDelegatePluginEvent(event: UIDelegatePluginEvent) {
    super.onUIDelegatePluginEvent(event)

    when (event) {
      is UIDelegatePluginEvent.OnViewBound -> {
        button = event.findViewById(buttonViewId)
      }

      is UIDelegatePluginEvent.Release -> button = null
      else -> { }
    }
  }

  // endregion

  // region BackButtonUIDelegatePlugin

  override fun setBackButtonListener(backActionListener: () -> Unit) {
    button?.setOnClickListener { backActionListener.invoke() }
  }

  override fun setBackButtonVisibility(visible: Boolean) {
    button?.isVisible = visible
  }

  // endregion
}
