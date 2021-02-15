package ru.boronin.common.rx.extension

import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

fun <T> rxSubj() = BehaviorSubject.create<T>()
fun <T> rxPublishSubj() = PublishSubject.create<T>()
fun <T> rxAsyncSubj() = AsyncSubject.create<T>()
