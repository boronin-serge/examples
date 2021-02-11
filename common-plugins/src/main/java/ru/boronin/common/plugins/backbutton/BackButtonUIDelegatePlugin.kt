package ru.boronin.common.plugins.backbutton

interface BackButtonUIDelegatePlugin {
  fun setBackButtonListener(backActionListener: () -> Unit)
  fun setBackButtonVisibility(visible: Boolean)
}
