package ru.boronin.protobuf.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import ru.boronin.protobuf.SomeSettings
import java.io.InputStream
import java.io.OutputStream

internal val Context.settingsDataStore: DataStore<SomeSettings> by dataStore(
  fileName = "some_settings.pb",
  serializer = SomeSettingsSerializer
)

private object SomeSettingsSerializer : Serializer<SomeSettings> {
  override val defaultValue: SomeSettings
    get() = SomeSettings.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): SomeSettings {
    try {
      return SomeSettings.parseFrom(input)
    } catch (e: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read proto.", e)
    }
  }

  override suspend fun writeTo(t: SomeSettings, output: OutputStream) = t.writeTo(output)
}
