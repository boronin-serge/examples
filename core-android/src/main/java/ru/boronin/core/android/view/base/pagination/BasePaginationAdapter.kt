package ru.boronin.core.android.view.base.pagination

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.boronin.common.extension.core.collection.isNotOutToBounds
import ru.boronin.common.utils.DEFAULT_STRING
import ru.boronin.common.utils.delegate.weakReference

abstract class BasePaginationAdapter<V : RecyclerView.ViewHolder, D>(
  val loadMoreListener: LoadMoreListener
) : RecyclerView.Adapter<V>() {

  protected abstract val differ: AsyncListDiffer<D>
  protected var nextMaxId = DEFAULT_STRING
  protected var isLastPage = false
  protected var isLoading = false

  private var rv by weakReference<RecyclerView>()

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    rv = recyclerView
    val layoutManager = rv?.layoutManager as LinearLayoutManager
    rv?.addOnScrollListener(
      object : PaginationScrollListener(layoutManager) {
        override fun loadMoreItems() {
          isLoading = true
          if (nextMaxId.isNotBlank()) {
            loadMoreListener.loadMore(nextMaxId)
          }
        }

        override fun isLastPage() = isLastPage
        override fun isLoading() = isLoading
      }
    )
  }

  override fun getItemCount() = differ.currentList.size

  protected fun getItem(position: Int): D = differ.currentList[position]

  fun removeItem(position: Int) {
    if (differ.currentList.isNotOutToBounds(position)) {
      val list = differ.currentList.filterIndexed { index, d -> index != position }
      update(list)
    }
  }

  fun updateItem(model: D, position: Int) {
    if (differ.currentList.isNotOutToBounds(position)) {
      val list = arrayListOf<D>()
      list.addAll(differ.currentList)
      list[position] = model
      update(list)
    }
  }

  fun update(items: List<D>, nextMaxId: String = DEFAULT_STRING) {
    differ.submitList(items) {
      onPageLoaded(nextMaxId)
    }
  }

  fun append(items: List<D>, nextMaxId: String) {
    differ.submitList(differ.currentList + items) {
      onPageLoaded(nextMaxId)
    }
  }

  // region private

  private fun onPageLoaded(nextMaxId: String) {
    this.nextMaxId = nextMaxId
    isLoading = false
    isLastPage = nextMaxId == DEFAULT_STRING
  }

  // endregion
}
