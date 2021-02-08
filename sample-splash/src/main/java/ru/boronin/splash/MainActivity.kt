package ru.boronin.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme) // Update splash theme
    showAsFullscreen()
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
  }

  /**
   * Если нужно отобразить на сплэш-скрине анимацию, то нужно сделать следующее.
   *  - В activity_main сделать такой же background как в теме сплеша.
   *  - Добавить необходимую анимацию в разметку
   *  - Сделать MainActivity полноэкранной перед super.onCreate(...) (чтобы совпасть со сплэшем):
   *  [showAsFullscreen]
   *  - По окончании анимации очистить бэкграунд, вернуть из полноэкранного режима:
   *  [clearBackground]
   */

  private fun showAsFullscreen() {
    window.decorView.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
  }

  private fun clearBackground() {
    activityRootLayout.background = null
    window.decorView.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_VISIBLE
  }
}
