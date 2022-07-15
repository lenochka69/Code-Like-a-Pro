package com.example.codelikeapro

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.codelikeapro.databinding.CardPostBinding

class PostViewHolder (
    private val binding: CardPostBinding,
    private val listener: OnInteractionListeren

        ): RecyclerView.ViewHolder (binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            textLink.text = post.link
            like.text = Utils.numToPostfix(post.likes)
            repost.text = Utils.numToPostfix(post.reposted)

            favorite.setImageResource(
                if (post.likeByMe) {
                    R.drawable.ic_favorite_24
                } else {
                    R.drawable.favorite
                }
            )

            favorite.setOnClickListener {
                listener.onLike(post)

            }
            share.setOnClickListener {
                listener.onShare(post)
            }

            more.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.option_post)

                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
        }
