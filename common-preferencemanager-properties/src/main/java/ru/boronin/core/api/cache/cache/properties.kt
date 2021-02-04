package ru.boronin.core.api.cache.cache

import ru.boronin.common.utils.DEFAULT_BOOLEAN
import ru.boronin.common.utils.DEFAULT_INT
import ru.boronin.common.utils.DEFAULT_LONG
import ru.boronin.common.utils.DEFAULT_STRING
import ru.boronin.core.api.cache.PreferenceManager


// region Base

private fun <T> PreferenceManager.value(
  getterFun: (key: String) -> T,
  setterFun: (key: String, value: T) -> Unit,
  key: String? = null,
  keyMapper: KeyMapperStrategies = KeyMapperStrategies.NO_MAPPING,
  notifier: NotifyFun<T>? = null
) = preference(
  getterFun = getterFun,
  setterFun = setterFun,
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region Boolean

fun PreferenceManager.boolean(
  key: String,
  defValue: Boolean = DEFAULT_BOOLEAN,
  notifier: NotifyFun<Boolean>? = null
) = boolean(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.booleanUpCaseKey(
  defValue: Boolean = DEFAULT_BOOLEAN,
  notifier: NotifyFun<Boolean>? = null
) = boolean(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.booleanLowCaseKey(
  defValue: Boolean = DEFAULT_BOOLEAN,
  notifier: NotifyFun<Boolean>? = null
) = boolean(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.boolean(
  key: String? = null,
  defValue: Boolean,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<Boolean>? = null
) = value(
  setterFun = { k, v -> putBoolean(k, v) },
  getterFun = { getBoolean(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region String

fun PreferenceManager.string(
  key: String,
  defValue: String = DEFAULT_STRING,
  notifier: NotifyFun<String>? = null
) = string(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.stringUpCaseKey(
  defValue: String = DEFAULT_STRING,
  notifier: NotifyFun<String>? = null
) = string(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.stringLowCaseKey(
  defValue: String = DEFAULT_STRING,
  notifier: NotifyFun<String>? = null
) = string(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.string(
  key: String? = null,
  defValue: String,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<String>? = null
) = value(
  setterFun = { k, v -> putString(k, v) },
  getterFun = { getString(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region Int

fun PreferenceManager.int(
  key: String,
  defValue: Int = DEFAULT_INT,
  notifier: NotifyFun<Int>? = null
) = int(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.intUpCaseKey(
  defValue: Int = DEFAULT_INT,
  notifier: NotifyFun<Int>? = null
) = int(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.intLowCaseKey(
  defValue: Int = DEFAULT_INT,
  notifier: NotifyFun<Int>? = null
) = int(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.int(
  key: String? = null,
  defValue: Int = DEFAULT_INT,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<Int>? = null
) = value(
  setterFun = { k, v -> putInt(k, v) },
  getterFun = { getInt(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region Long

fun PreferenceManager.long(
  key: String,
  defValue: Long = DEFAULT_LONG,
  notifier: NotifyFun<Long>? = null
) = long(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.longUpCaseKey(
  defValue: Long = DEFAULT_LONG,
  notifier: NotifyFun<Long>? = null
) = long(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.longLowCaseKey(
  defValue: Long = DEFAULT_LONG,
  notifier: NotifyFun<Long>? = null
) = long(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.long(
  key: String? = null,
  defValue: Long,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<Long>? = null
) = value(
  setterFun = { k, v -> putLong(k, v) },
  getterFun = { getLong(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region Set<String>

fun PreferenceManager.stringSet(
  key: String,
  defValue: Set<String> = emptySet(),
  notifier: NotifyFun<Set<String>>? = null
) = stringSet(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.stringSetUpCaseKey(
  defValue: Set<String> = emptySet(),
  notifier: NotifyFun<Set<String>>? = null
) = stringSet(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.stringSetLowCaseKey(
  defValue: Set<String> = emptySet(),
  notifier: NotifyFun<Set<String>>? = null
) = stringSet(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.stringSet(
  key: String? = null,
  defValue: Set<String>,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<Set<String>>? = null
) = value(
  setterFun = { k, v -> putStringSet(k, v) },
  getterFun = { getStringSet(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion


// region Float

fun PreferenceManager.float(
  key: String,
  defValue: Float,
  notifier: NotifyFun<Float>? = null
) = float(
  key = key,
  defValue = defValue,
  keyMapper = KeyMapperStrategies.NO_MAPPING,
  notifier = notifier
)

fun PreferenceManager.floatUpCaseKey(
  defValue: Float,
  notifier: NotifyFun<Float>? = null
) = float(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_UPPER_CASE_WITH_POSTFIX,
  notifier = notifier
)

fun PreferenceManager.floatLowCaseKey(
  defValue: Float,
  notifier: NotifyFun<Float>? = null
) = float(
  defValue = defValue,
  keyMapper = KeyMapperStrategies.TO_LOWER_CASE_WITH_POSTFIX,
  notifier = notifier
)

private fun PreferenceManager.float(
  key: String? = null,
  defValue: Float,
  keyMapper: KeyMapperStrategies,
  notifier: NotifyFun<Float>? = null
) = value(
  setterFun = { k, v -> putFloat(k, v) },
  getterFun = { getFloat(it, defValue) },
  key = key,
  keyMapper = keyMapper,
  notifier = notifier
)

// endregion