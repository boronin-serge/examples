package ru.boronin.core.android.view.base.pagination

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.boronin.common.utils.DEFAULT_STRING
import ru.boronin.core.android.view.base.BaseReyclerViewAdapter

abstract class BasePaginationAdapter<V : RecyclerView.ViewHolder, D>(
  val loadMoreListener: LoadMoreListener
) : BaseReyclerViewAdapter<V, D>() {

  protected var nextMaxId = DEFAULT_STRING
  protected var isLastPage = false
  protected var isLoading = false

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
    recyclerView.addOnScrollListener(
      object : PaginationScrollListener(layoutManager) {
        override fun loadMoreItems() {
          isLoading = true
          if (nextMaxId.isNotBlank()) {
            loadMoreListener.loadMore(nextMaxId)
          }
        }

        override fun isLastPage(): Boolean {
          return isLastPage
        }

        override fun isLoading(): Boolean {
          return isLoading
        }
      }
    )
  }

  @SuppressLint("CheckResult")
  fun update(items: List<D>, nextMaxId: String = "") {
    Observable.just(calcDiff(this.items, items))
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        onPageLoaded(nextMaxId)
        this.items.clear()
        this.items.addAll(items)
        it.dispatchUpdatesTo(this)
      }
  }

  @SuppressLint("CheckResult")
  fun append(items: List<D>, nextMaxId: String) {
    val newList = arrayListOf<D>()
    newList.addAll(this.items)
    newList.addAll(items)

    Observable.just(calcDiff(this.items, newList))
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        onPageLoaded(nextMaxId)
        this.items.addAll(items)
        it.dispatchUpdatesTo(this)
      }
  }

  // region private

  private fun onPageLoaded(nextMaxId: String) {
    this.nextMaxId = nextMaxId
    isLoading = false
    isLastPage = nextMaxId == ""
  }

  private fun calcDiff(oldList: List<D>, newList: List<D>): DiffUtil.DiffResult {
    val callback = getDiffUtilCallback(oldList, newList)
    return DiffUtil.calculateDiff(callback)
  }

  // endregion

  abstract fun getDiffUtilCallback(oldList: List<D>, newList: List<D>): DiffUtilCallback<D>
}
