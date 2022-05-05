package ru.boronin.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.boronin.compose.ui.features.onboarding.Pages
import ru.boronin.compose.ui.theme.ExamplesTheme

@ExperimentalPagerApi
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ExamplesTheme {
        Box(
          modifier = Modifier.fillMaxSize()
        ) {
          Pages()
        }
      }
    }
  }
}

