package ru.boronin.core.api.cache.cache

import java.beans.PropertyChangeSupport

typealias NotifyFun<T> = (key: String, mapperKey: String, old: T?, new: T) -> Unit

fun <T> PropertyChangeSupport.toNotifier(): NotifyFun<T> = { key, _, old, new ->
    firePropertyChange(key, old, new)
}