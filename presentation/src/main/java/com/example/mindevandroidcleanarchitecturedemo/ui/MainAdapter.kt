package com.example.mindevandroidcleanarchitecturedemo.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity

class MainAdapter(private var items: List<PresentationEntity.HackerNews>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(parent)
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(items[position])
}