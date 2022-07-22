package com.example.codelikeapro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels

import com.example.codelikeapro.databinding.ActivityMainBinding
import com.example.codelikeapro.databinding.CardPostBinding




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val newPostContract = registerForActivityResult(NewPostActivityContract()) { text ->
            text?.let {
                viewModel.changeContent(it)
                viewModel.save()
            }
        }

        val editPostActivityContract =
            registerForActivityResult(EditPostActivityContract()) { text ->
                text?.let {
                    viewModel.changeContent(it)
                    viewModel.save()
                }
            }

        val adapter = PostAdapter(
            object : OnInteractionListeren {

                override fun onEdit(post: Post) {
                    viewModel.edit(post)

                    editPostActivityContract.launch(post.content)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)
                }
                override fun onVideo(post: Post) {
                    val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(intentVideo)
                }

            }
        )

        binding.container.adapter = adapter

        viewModel.data.observe(this)
        { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) binding.container.smoothScrollToPosition(0)
            }
        }

        binding.addPost.setOnClickListener {
            newPostContract.launch()
        }
    }
}



