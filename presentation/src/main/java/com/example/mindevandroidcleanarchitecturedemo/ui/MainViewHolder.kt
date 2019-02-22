package com.example.mindevandroidcleanarchitecturedemo.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindevandroidcleanarchitecturedemo.R
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import kotlinx.android.synthetic.main.item_main.view.*

class MainViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)) {
    @SuppressLint("SetTextI18n")
    fun bind(item: PresentationEntity.HackerNews) {
        with(itemView) {
            tvNumber.text = "# $layoutPosition"
            tvTimeAgo.text = item.time_ago
            tvContent.text = item.title
            tvCommentCount.text = "comment ${item.comments_count.toString()} 개"
        }
    }
}