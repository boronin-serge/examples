package ru.boronin.examples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.boronin.protobuf.datastore.ProtoDataStoreInitializer

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val protoInitializer = ProtoDataStoreInitializer(applicationContext)
    with(protoInitializer) {
      getSettings {
        Log.d("difficulty", it)
      }
      setDifficulty(0)
      setDifficulty(1)
      setDifficulty(2)
      setDifficulty(3)
    }
  }
}
