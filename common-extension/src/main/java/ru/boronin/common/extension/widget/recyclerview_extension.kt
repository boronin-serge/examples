package ru.boronin.common.extension.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> RecyclerView.defaultInit(adapter: RecyclerView.Adapter<T>) {
  layoutManager = LinearLayoutManager(context)
  this.adapter = adapter
}
