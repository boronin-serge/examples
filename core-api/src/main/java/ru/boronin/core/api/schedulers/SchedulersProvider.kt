package ru.boronin.core.api.schedulers

import io.reactivex.Scheduler

/**
 * Created by Igor Vasilev on 20.11.17.
 */
interface SchedulersProvider {
  fun ui(): Scheduler
  fun computation(): Scheduler
  fun trampoline(): Scheduler
  fun newThread(): Scheduler
  fun io(): Scheduler
  fun single(): Scheduler
}