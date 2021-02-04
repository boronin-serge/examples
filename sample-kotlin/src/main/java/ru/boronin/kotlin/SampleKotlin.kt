package ru.boronin.kotlin

import ru.boronin.kotlin.java.MyJavaClass

class SampleKotlin {

    fun errorWithoutExplicitType() {
        val item = MyJavaClass().returnNullList()
        item.size // NPE - you need annotate Java method as @Nullable
    }

    fun errorWithExplicitType() {
        // Better Error:
        // ILLEGAL STATE EXCEPTION
        val item: List<String> = MyJavaClass().returnNullList()
        item.size
    }
}