package ru.boronin.core.api.cache

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences.Editor

const val DEFAULT_NAME_PREFERENCE = "prefs"

@SuppressLint("CommitPrefEdits")
class AndroidPreferenceManager(
  context: Context,
  name: String = DEFAULT_NAME_PREFERENCE
) : PreferenceManager {
  private val sharedPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)
  private val edit = sharedPrefs.edit()


  // region Boolean

  override fun putBoolean(key: String, value: Boolean) {
    with(edit) {
      putBoolean(key, value)
      apply()
    }
  }

  override fun getBoolean(key: String, defValue: Boolean) = sharedPrefs.getBoolean(key, defValue)

  // endregion


  // region Int

  override fun putInt(key: String, value: Int) {
    with(edit) {
      putInt(key, value)
      apply()
    }
  }

  override fun getInt(key: String, defValue: Int) = sharedPrefs.getInt(key, defValue)

  // endregion


  // region Long

  override fun putLong(key: String, value: Long) {
    with(edit) {
      putLong(key, value)
      apply()
    }
  }

  override fun getLong(key: String, defValue: Long) = sharedPrefs.getLong(key, defValue)

  // endregion


  // region String

  override fun putString(key: String, value: String) {
    with(edit) {
      putString(key, value)
      apply()
    }
  }

  override fun getString(
    key: String,
    defValue: String
  ) = sharedPrefs.getString(key, defValue) ?: defValue

  // endregion


  // region Set<String>

  override fun putStringSet(key: String, value: Set<String>) {
    with(edit) {
      putStringSet(key, value)
      apply()
    }
  }

  override fun getStringSet(
    key: String,
    defValue: Set<String>
  ): Set<String> = sharedPrefs.getStringSet(key, defValue) ?: defValue

  // endregion


  // region Float

  override fun putFloat(key: String, value: Float) {
    with(edit) {
      putFloat(key, value)
      apply()
    }
  }

  override fun getFloat(key: String, defValue: Float) = sharedPrefs.getFloat(key, defValue)

  // endregion


  override fun hasValue(key: String) = sharedPrefs.contains(key)
  override fun remove(key: String) = edit.remove(key).apply()
  override fun removeAll(): Editor = edit.clear()
}