package com.example.codelikeapro

import android.view.View
import android.widget.PopupMenu
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.codelikeapro.databinding.CardPostBinding

class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: OnInteractionListeren

        ): RecyclerView.ViewHolder (binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.text = Utils.numToPostfix(post.likes)
            share.text = Utils.numToPostfix(post.reposted)
            like.isChecked = post.likeByMe

            if (post.video == null) {
                binding.playVideoGroup.visibility = View.GONE
            } else {
                binding.playVideoGroup.visibility = View.VISIBLE
            }

            like.setOnClickListener { listener.onLike(post) }
            share.setOnClickListener { listener.onShare(post) }
            play.setOnClickListener { listener.onVideo(post) }
            backgroundVideo.setOnClickListener { listener.onVideo(post) }

            menu.setOnClickListener {
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
