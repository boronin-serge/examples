package ru.boronin.compose.ui.features.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun Pages() {
  HorizontalPager(count = 10) { page ->
    // Our page content
    Text(
      text = "Page: $page",
      color = Color.White,
      modifier = Modifier.fillMaxWidth()
    )
  }
}
