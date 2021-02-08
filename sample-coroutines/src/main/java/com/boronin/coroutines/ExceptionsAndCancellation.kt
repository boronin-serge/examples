package com.boronin.coroutines

import kotlinx.coroutines.*

/**
 * Created by Sergey Boronin on 06.09.2019.
 */
class ExceptionsAndCancellation {

  fun makeException() = runBlocking {
    val asyncJob = GlobalScope.launch {
      println("1. Exception created via launch coroutine")
      // Will be printed to the console by
      // Thread.defaultUncaughtExceptionHandler
      throw IndexOutOfBoundsException()
    }
    asyncJob.join()
    println("2. Joined failed job")
    val deferred = GlobalScope.async {
      println("3. Exception created via async coroutine")
      // Nothing is printed, relying on user to call await
      throw ArithmeticException()
    }
    try {
      deferred.await()
      println("4. Unreachable, this statement is never executed")
    } catch (e: Exception) {
      println("5. Caught ${e.javaClass.simpleName}")
    }
  }

  /**
   * When using the launch builder, the exception will be stored in a Job object.
   * To retrieve it, you can use the invokeOnCompletion helper function
   */
  fun getExceptionFromJob() {
    runBlocking {
      val job = GlobalScope.launch {
        println("1. Exception created via launch coroutine")
        // Will NOT be handled by
        // Thread.defaultUncaughtExceptionHandler
        // since it is being handled later by `invokeOnCompletion`
        throw IndexOutOfBoundsException()
      }
      // Handle the exception thrown from `launch` coroutine builder
      job.invokeOnCompletion { exception ->
        println("2. Caught $exception")
      }
      // This suspends coroutine until this job is complete.
      job.join()
    }
  }

  /**
   * If an exception is thrown during an async block,
   * the exception is not actually thrown immediately.
   * Instead, it will be thrown at the time you call
   * await on the Deferred object that is returned
   */
  fun tryCatchToTheRescue() {
    runBlocking {
      // Set this to ’true’ to call await on the deferred variable
      val callAwaitOnDeferred = false
      val deferred = GlobalScope.async {
        // This statement will be printed with or without
        // a call to await()
        println("Throwing exception from async")
        throw ArithmeticException("Something Crashed")
        // Nothing is printed, relying on a call to await()
      }
      if (callAwaitOnDeferred) {
        try {
          deferred.await()
        } catch (e: ArithmeticException) {
          println("Caught ArithmeticException")
        }
      }
    }
  }

  /**
   * Cancellation of the job
   */
  fun cancellation() = runBlocking {
    val job = launch {
      repeat(1000) { i ->
        println("$i. Crunching numbers [Beep.Boop.Beep]...")
        delay(500L)
      }
    }
    delay(1300L) // delay a bit
    println("main: I am tired of waiting!")
    // cancels the job and waits for job’s completion
    job.cancelAndJoin()
    println("main: Now I can quit.")
  }

  /**
   * Cancel all childs
   */
  fun cancelAllChilds() = runBlocking {
    val parentJob = launch {
      val childOne = launch {
        repeat(1000) { i ->
          println(
            "Child Coroutine 1: " +
              "$i. Crunching numbers [Beep.Boop.Beep]..."
          )
          delay(500L)
        }
      }
      // Handle the exception thrown from `launch`
      // coroutine builder
      childOne.invokeOnCompletion { exception ->
        println("Child One: ${exception?.message}")
      }
      val childTwo = launch {
        repeat(1000) { i ->
          println(
            "Child Coroutine 2: " +
              "$i. Crunching numbers [Beep.Boop.Beep]..."
          )
          delay(500L)
        }
      }
      // Handle the exception thrown from `launch`
      // coroutine builder
      childTwo.invokeOnCompletion { exception ->
        println("Child Two: ${exception?.message}")
      }
    }
    delay(1200L)
    println("Calling cancelChildren() on the parentJob")
    parentJob.cancelChildren()
    println("parentJob isActive: ${parentJob.isActive}")
  }

  /**
   * Cancel in timeout
   */
  fun cancelWithTimeout() {
    GlobalScope.launch {
      withTimeout(1500L) {
        repeat(1000) { i ->
          println("$i. Crunching numbers [Beep.Boop.Beep]...")
          delay(500L)
        }
      }
    }
  }
}
