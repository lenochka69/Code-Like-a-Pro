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


private const val TAG = "MainActivity"
private const val MY_FILTER_TAG = "myfilter"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = findViewById<TextView>(R.id.myTextView)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                textLink.text = post.link

                val likeImage = if (post.likeByMe) {
                    R.drawable.ic_favorite_24
                } else {
                    R.drawable.favorite
                }
                favorite?.setImageResource(likeImage)
                like?.text = Utils.numToPostfix(post.likes)
                repost?.text = Utils.numToPostfix(post.reposted)
            }

        }

        if (savedInstanceState != null)
            textView.text = savedInstanceState.getString(KEY)

        binding.favorite?.setOnClickListener {
            viewModel.like()
            Toast.makeText(this, "И тебе Like", Toast.LENGTH_SHORT).show();
            textView.text="GOGOGO"

        }
        binding.share?.setOnClickListener {
            viewModel.repost()
        }

        Log.d(MY_FILTER_TAG, "onCreate()")

    }

}

