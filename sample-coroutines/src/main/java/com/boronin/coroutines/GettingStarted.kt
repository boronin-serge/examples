package com.boronin.coroutines

import kotlinx.coroutines.*

/**
 * Created by Sergey Boronin on 06.09.2019.
 */
class GettingStarted {

  /**
   * The second coroutine invokes the join function on job1.
   * Job1 has CoroutineStart.LAZY. Therefore it's will run on demand
   */
  fun runDependentJobs() {
    val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
      delay(200)
      println("Pong")
      delay(200)
    }

    GlobalScope.launch {
      delay(200)
      println("Ping")
      job1.join()
      println("Ping")
      delay(200)
    }
  }

  /**
   * Parent - child relationship
   */
  fun jobsHierarchy() {
    with(GlobalScope) {
      val parentJob = launch {
        delay(200)
        println("I'm the parent")
        delay(200)
      }
      launch {
        delay(200)
        println("I'm a child")
        delay(200)
      }
      if (parentJob.children.iterator().hasNext()) {
        println("The Job has children ${parentJob.children}")
      } else {
        println("The Job has NO children")
      }
    }
  }

  /**
   * Repeatable coroutine
   */
  fun repeatableJob() {
    var isDoorOpen = false
    println("Unlocking the door... please wait.\n")
    GlobalScope.launch {
      delay(3000)
      isDoorOpen = true
    }
    GlobalScope.launch {
      repeat(4) {
        println("Trying to open the door...\n")
        delay(800)
        if (isDoorOpen) {
          println("Opened the door!\n")
        } else {
          println("The door is still locked\n")
        }
      }
    }
  }

  /**
   * Posting to UI
   */
  fun postToUI() {
    GlobalScope.launch {
      val bgThreadName = Thread.currentThread().name
      println("I’m Job 1 in thread $bgThreadName")
      delay(200)
      GlobalScope.launch(Dispatchers.Main) {
        val uiThreadName = Thread.currentThread().name
        println("I’m Job 2 in thread $uiThreadName")
      }
    }
  }
}
