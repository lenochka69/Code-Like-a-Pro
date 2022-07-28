package com.example.codelikeapro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels

import com.example.codelikeapro.databinding.ActivityMainBinding
import com.example.codelikeapro.databinding.CardPostBinding


private const val TAG = "MainActivity"
private const val MY_FILTER_TAG = "myfilter"

private const val TAG = "MainActivity"
private const val MY_FILTER_TAG = "myfilter"



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeListener = { viewModel.likeById(it.id) },
            onShareListener = { viewModel.repostById(it.id) }
        )
        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        Log.d(MY_FILTER_TAG, "onCreate()")
    }
}



