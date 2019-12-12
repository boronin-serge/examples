package ru.bia.tech.common.adapterdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

abstract class BaseAdapterDelegate<T , VH : RecyclerView.ViewHolder> : AdapterDelegate<List<T>>() {

  // region Override

  @Suppress("UNCHECKED_CAST")
  override fun onBindViewHolder(
    items: List<T>,
    position: Int,
    holder: RecyclerView.ViewHolder,
    payloads: MutableList<Any>
  ) {
    onBindViewHolder(items[position], position, holder as? VH)
  }

  override fun isForViewType(items: List<T>, position: Int) = true

  // endregion


  // region Api

  protected abstract fun onBindViewHolder(item: T, position: Int, holder: VH?)

  protected fun inflate(
    @LayoutRes resource: Int,
    root: ViewGroup?
  ): View = LayoutInflater.from(root?.context)
    .inflate(resource, root, false)

  // endregion
}