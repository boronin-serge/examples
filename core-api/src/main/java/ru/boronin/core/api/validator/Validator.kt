package ru.bia.tech.core.api.validator

interface Validator<T> {
  fun valid(value: T): Boolean
}