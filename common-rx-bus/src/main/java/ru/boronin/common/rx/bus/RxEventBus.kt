package ru.boronin.common.rx.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxEventBus<T> : EventBus<T> {

  companion object {
    fun <T> create() = RxEventBus<T>()
  }

  private val subject = PublishSubject.create<T>()

  override fun sendEvent(event: T) {
    subject.onNext(event)
  }

  override fun getEvents(filterFun: (T) -> Boolean): Observable<T> = subject.filter(filterFun)
}
