package com.example.codelikeapro.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.codelikeapro.*
import com.example.codelikeapro.adapter.PostAdapter
import com.example.codelikeapro.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_post)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val newPostContract = registerForActivityResult(NewPostActivityContract()) { text ->
            text?:return@registerForActivityResult
            viewModel.run {
                editContent(text)
                save()
            }
        }


        val editPostActivityContract =
            registerForActivityResult(EditPostActivityContract()) { text ->
                text?:return@registerForActivityResult
                with(viewModel) {
                    editContent(text)
                    save()
                }
            }


        val adapter = PostAdapter(
            object : OnInteractionListeren {



                override fun onEdit(post: Post) {
                    Intent().apply {
                        action = Intent.ACTION_EDIT
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
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
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(
                        intent,
                        getString(R.string.chooser_share_post)
                    )
                    startActivity(shareIntent)
                    viewModel.shareById(post.id)
                }

                override fun onVideo(post: Post) {
                    val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(intentVideo)
                }

            }
        )
        binding.list.adapter = adapter

        viewModel.data.observe(this)
        { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) binding.list.smoothScrollToPosition(0)
            }
        }

        binding.addPost.setOnClickListener {
            newPostContract.launch()
        }
    }
}



