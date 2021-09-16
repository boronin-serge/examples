/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.boronin.protobuf.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import ru.boronin.protobuf.SomeSettings
import java.io.IOException

/**
 * Class that handles saving and retrieving user preferences
 */
class SomeSettingsRepository(private val someSettingsStore: DataStore<SomeSettings>) {

  private val TAG: String = "SomeSettingsRepo"

  val settingsFlow: Flow<SomeSettings> = someSettingsStore.data
    .catch { exception ->
      // dataStore.data throws an IOException when an error is encountered when reading data
      if (exception is IOException) {
        Log.e(TAG, "Error reading sort order preferences.", exception)
        emit(SomeSettings.getDefaultInstance())
      } else {
        throw exception
      }
    }

  suspend fun changeDifficulty(difficulty: SomeSettings.ProtoDifficulty) {
    someSettingsStore.updateData { currentPreferences ->
      currentPreferences.toBuilder().setDifficulty(difficulty).build()
    }
  }
}
