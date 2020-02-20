package ru.boronin.examples

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import ru.boronin.core.android.view.base.BaseReyclerViewAdapter

/**
 * Created by Sergey Boronin on 19.02.2020.
 */
class RvAdapter : BaseReyclerViewAdapter<RvAdapter.ItemVH, String>() {
    override fun getItemLayout() = R.layout.list_item

    override fun initViewHolder(itemView: View) = ItemVH(itemView)

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(items[position])
    }

    class ItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val desc = itemView.tvDesc

        fun bind(model: String) {
            desc.text = model
        }
    }
}