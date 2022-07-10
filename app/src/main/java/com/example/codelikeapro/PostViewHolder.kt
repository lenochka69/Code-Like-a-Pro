package com.example.codelikeapro

import androidx.recyclerview.widget.RecyclerView
import com.example.codelikeapro.databinding.CardPostBinding

class PostViewHolder (
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener

        ): RecyclerView.ViewHolder (binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            textLink.text = post.link
            like?.text = Utils.numToPostfix(post.likes)
            repost?.text = Utils.numToPostfix(post.reposted)

            favorite?.setImageResource(
                if (post.likeByMe) {
                    R.drawable.ic_favorite_24
                } else {
                    R.drawable.favorite
                }
            )

            favorite?.setOnClickListener {
                onLikeListener(post)

            }
            share?.setOnClickListener {
                onShareListener(post)
            }
        }
    }
}