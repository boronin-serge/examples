package ru.boronin.common.extension.primitive

import java.util.*

fun Int.isNotOutToBounds(index: Number, start: Int = 0) = index in start until this
fun Double.round(decimals: Int = 2): Double = roundAsStr(decimals).toDouble()
fun Double.roundAsStr(decimals: Int = 2): String = "%.${decimals}f".format(Locale.US, this)