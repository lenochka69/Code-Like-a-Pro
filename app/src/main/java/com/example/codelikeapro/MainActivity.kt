package com.example.codelikeapro

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
import androidx.activity.viewModels

import com.example.codelikeapro.databinding.ActivityMainBinding
import com.example.codelikeapro.databinding.CardPostBinding



private const val TAG = "MainActivity"
private const val MY_FILTER_TAG = "myfilter"



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()

        binding.group.visibility = View.GONE

        val adapter = PostAdapter(
            object : OnInteractionListeren {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                    binding.group.visibility = View.VISIBLE
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
            }
        )
        binding.container.adapter = adapter


        viewModel.data.observe(this) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) binding.container.smoothScrollToPosition(0)
            }
        }

        viewModel.edited.observe(this) {
            with (binding.contentCheck) {
                text = it.content
            }
            binding.content.setText(it.content)
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.edit_message),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                Utils.hideKeyboard(this)
                binding.group.visibility = View.GONE
            }
        }

        binding.cancel.setOnClickListener {
            with(binding.content) {
                setText("")
                clearFocus()
                Utils.hideKeyboard(this)
                binding.group.visibility = View.GONE
            }
        }
    }

}



