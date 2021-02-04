package ru.boronin.core.api.cache.cache

private const val REGEX = "([a-z])([A-Z]+)"

enum class KeyMapperStrategies(val mapper: (name: String) -> String) {

  // map somePropertyName --> SOME_PROPERTY_NAME_KEY
  TO_UPPER_CASE_WITH_POSTFIX(
    { name ->
      name.replace(
        regex = Regex(REGEX),
        replacement = "$1_$2"
      ).toUpperCase() + "_KEY" }
  ),

  // map somePropertyName --> some_property_name_key
  TO_LOWER_CASE_WITH_POSTFIX(
    { name ->
    name.replace(
      regex = Regex(REGEX),
      replacement = "$1_$2"
    ).toLowerCase() + "_key"
    }
  ),

  NO_MAPPING({ it });

  fun map(name: String) = mapper(name)
}