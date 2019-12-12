package ru.boronin.common.extension.text

import android.os.Build
import android.text.Html

fun String.fromHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
  Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
  Html.fromHtml(this)
}