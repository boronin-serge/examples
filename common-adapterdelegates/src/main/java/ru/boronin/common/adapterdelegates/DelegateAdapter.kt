package ru.boronin.common.adapterdelegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import ru.boronin.common.extension.core.collection.isNotOutToBounds

class DelegateAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private var items = mutableListOf<T>()
  private var delegatesManager: AdapterDelegatesManager<List<T>> = AdapterDelegatesManager()

  // Override

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = delegatesManager.onCreateViewHolder(parent, viewType)

  override fun getItemCount() = items.size
  override fun getItemViewType(position: Int) = delegatesManager.getItemViewType(items, position)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    delegatesManager.onBindViewHolder(items, position, holder)
  }

  override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = false

  // endregion

  // Api

  fun addDelegate(delegate: AdapterDelegate<List<T>>) {
    delegatesManager.addDelegate(delegate)
  }

  fun addDelegate(viewType: Int, delegate: AdapterDelegate<List<T>>?) {
    delegatesManager.addDelegate(viewType, delegate as AdapterDelegate<List<T>>)
  }

  fun getItem(position: Int): T? {
    var item: T? = null
    if (items.isNotOutToBounds(position)) {
      item = items[position]
    }

    return item
  }

  fun addAll(data: List<T>) {
    items.addAll(data)
    notifyDataSetChanged()
  }

  fun add(item: T) {
    items.add(item)
    notifyItemInserted(items.size)
  }

  fun add(index: Int, item: T) {
    items.add(index, item)
    notifyItemInserted(index)
  }

  fun remove(index: Int) {
    items.removeAt(index)
    notifyItemRemoved(index)
  }

  fun updateItem(item: T) {
    val index = items.indexOf(item)
    if (items.isNotOutToBounds(index)) {
      items[index] = item
      notifyItemChanged(index)
    }
  }

  fun setList(data: MutableList<T>) {
    items = data
    notifyDataSetChanged()
  }

  fun clear() {
    items.clear()
    notifyDataSetChanged()
  }

  // endregion
}
