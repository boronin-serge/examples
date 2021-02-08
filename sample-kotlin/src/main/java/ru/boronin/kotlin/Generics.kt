package ru.boronin.kotlin

class Generics {
  fun <T> defaultUpper() { }
  fun <T : Any?> explicitUpper() { }

  // Multiple constraints
  interface Animal
  interface Fuzzy
  fun <T> getFuzzyAnimal(): T where T : Animal, T : Fuzzy {
    TODO()
  }
}
