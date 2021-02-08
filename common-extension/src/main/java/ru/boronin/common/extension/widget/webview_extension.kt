package ru.boronin.common.extension.widget

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient

@SuppressLint("SetJavaScriptEnabled")
fun WebView.getHtmlResponse(callback: (String) -> Unit) {
  settings.javaScriptEnabled = true
  webViewClient = object : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
      super.onPageFinished(view, url)
      view?.evaluateJavascript(
        """(function() {
              | return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');
              |  })();""".trimMargin()
      ) { html ->
        callback.invoke(html)
      }
    }
  }
}
