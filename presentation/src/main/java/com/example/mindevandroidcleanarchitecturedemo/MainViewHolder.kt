package com.example.mindevandroidcleanarchitecturedemo

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Entity
import kotlinx.android.synthetic.main.item_main.view.*

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("SetTextI18n")
    fun bind(item: Entity.HackerNews) {
        itemView.tvNumber.text = "# $layoutPosition"
        itemView.tvTimeAgo.text = item.time_ago
        itemView.tvContent.text = item.title
        itemView.tvCommentCount.text = "comment ${item.comments_count.toString()} 개"
    }
}