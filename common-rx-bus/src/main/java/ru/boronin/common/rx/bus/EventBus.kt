package ru.boronin.common.rx.bus

import io.reactivex.Observable

interface EventBus<T> {
  fun sendEvent(event: T)
  fun getEvents(filterFun: (T) -> Boolean = { true }): Observable<T>
}
