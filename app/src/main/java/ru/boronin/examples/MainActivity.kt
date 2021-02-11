package ru.boronin.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)
  }

  private fun test(n: Int): Int {
    return n.toString(2).toCharArray().sumBy { it.toInt() }
  }
}
