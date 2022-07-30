package com.example.codelikeapro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.codelikeapro.OnInteractionListeren
import com.example.codelikeapro.Post
import com.example.codelikeapro.PostDiffCallback
import com.example.codelikeapro.PostViewHolder
import com.example.codelikeapro.databinding.CardPostBinding


class PostAdapter(
    private val listener: OnInteractionListeren
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding,
            listener,
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}