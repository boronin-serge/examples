package ru.boronin.common.plugins.alert

import ru.boronin.common.utils.DEFAULT_STRING

interface AlertUIDelegatePlugin {
  fun showAlert(
    title: String = DEFAULT_STRING,
    desc: String,
    isCancelable: Boolean = true,
    okTitle: String = "Ок",
    cancelTitle: String = "Отмена",
    ok: () -> Unit = { },
    cancel: () -> Unit = { }
  )
}
