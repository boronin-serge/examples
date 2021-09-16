package ru.boronin.protobuf.datastore

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.boronin.protobuf.SomeSettings

class ProtoDataStoreInitializer(private val context: Context) {

  private val scope = CoroutineScope(Dispatchers.Default)
  private val someSettingsRepository = SomeSettingsRepository(context.settingsDataStore)
  private val settingsFlow = someSettingsRepository.settingsFlow

  fun getSettings(callback: (String) -> Unit) {
    scope.launch {
      settingsFlow.collect {
        callback.invoke(it.difficulty.name)
      }
    }
  }

  fun setDifficulty(difficulty: Int) {
    scope.launch {
      someSettingsRepository.changeDifficulty(SomeSettings.ProtoDifficulty.forNumber(difficulty))
    }
  }
}
