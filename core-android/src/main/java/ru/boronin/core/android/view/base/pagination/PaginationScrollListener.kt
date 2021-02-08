package ru.boronin.core.android.view.base.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sergey Boronin on 10.07.2019.
 */
abstract class PaginationScrollListener(
  private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

  private val loadBeforeNPosts = 3
  private val treshold = 0.8 // 1 - load when the last item is visible

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)

    val visibleItemCount = layoutManager.childCount
    val totalItemCount = layoutManager.itemCount
    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

    if (!isLoading() && !isLastPage()) {
      if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount * treshold &&
        firstVisibleItemPosition >= 0
      ) {
        loadMoreItems()
      }
    }
  }

  protected abstract fun loadMoreItems()

  abstract fun isLastPage(): Boolean

  abstract fun isLoading(): Boolean
}
