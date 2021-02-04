package ru.boronin.core.api.cache.cache

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> preference(
  getterFun: (key: String) -> T,
  setterFun: (key: String, value: T) -> Unit,
  key: String? = null,
  keyMapper: KeyMapperStrategies = KeyMapperStrategies.NO_MAPPING,
  notifier:  NotifyFun<T>? = null
) = object : ReadWriteProperty<Any?, T> {
  private var oldValue: T? = null
  private var hasChanges: Boolean = false

  override fun getValue(thisRef: Any?, property: KProperty<*>): T {
    if (hasChanges || oldValue == null) {
      oldValue = getterFun(property.actualKey())
    }

    return oldValue!!
  }

  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    hasChanges = oldValue != value

    if (hasChanges) {
      val key = property.actualKey()
      setterFun(key, value)
      notifier?.invoke(property.name, key, oldValue, value)
      oldValue = value
    }
  }

  private fun KProperty<*>.actualKey() = key ?: keyMapper.map(name)
}