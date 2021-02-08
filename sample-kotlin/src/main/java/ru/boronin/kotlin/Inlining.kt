package ru.boronin.kotlin

class Inlining {

  inline fun sayHelloWorld() {
    println("Hello, world")
  }

  fun foo() {
    sayHelloWorld()
  }
}
