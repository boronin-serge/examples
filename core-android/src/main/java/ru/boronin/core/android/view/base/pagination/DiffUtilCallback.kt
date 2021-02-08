package ru.boronin.core.android.view.base.pagination

import androidx.recyclerview.widget.DiffUtil

abstract class DiffUtilCallback<T>(
  private val oldList: List<T>,
  private val newList: List<T>
) : DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
    val oldItem = oldList[oldIndex]
    val newItem = newList[newIndex]
    return oldItem == newItem
  }
}
