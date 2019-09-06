package ru.boronin.examples.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Sergey Boronin on 06.09.2019.
 */
class CoroutineManager {

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
        Thread.sleep(1000)
    }

    fun jibsHierarchy() {
        with(GlobalScope) {
            val parentJob = launch {
                delay(200)
                println("I'm the parent")
                delay(200)
            }
            launch(context = parentJob) {
                delay(200)
                println("I'm a child")
                delay(200)
            }
            if (parentJob.children.iterator().hasNext()) {
                println("The Job has children ${parentJob.children}")
            } else {
                println("The Job has NO children")
            }
            Thread.sleep(1000)
        }
    }
}